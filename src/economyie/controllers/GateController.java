package economyie.controllers;

import economyie.MainApplication;
import gate.*;
import gate.creole.ExecutionException;
import gate.creole.ResourceInstantiationException;
import gate.persist.PersistenceException;
import gate.util.GateException;
import gate.util.Out;
import gate.util.persistence.PersistenceManager;

import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * Created by Duong on 19/02/2016.
 */
public class GateController {

    public static String gateMain = MainApplication.gateHome + "\\plugins\\ANNIE\\resources\\NE\\main.jape";
    private static GateController instance = null;
    private CorpusController corpusController;
    private Corpus corpus;

    private GateController(String gateHome) {
        initGate(gateHome);
        initAnnie();
    }

    public static GateController getInstance(String gateHome) {
        if (instance == null) {
            instance = new GateController(gateHome);
        }
        return instance;
    }

    public static String getMainContent() {
        String newLine = System.getProperty("line.separator");
        StringBuffer buffer = new StringBuffer();
        File file = new File(gateMain);
        if (file != null && file.exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                for (String line; (line = bufferedReader.readLine()) != null; ) {
                    buffer.append(line);
                    buffer.append(newLine);
                }
                bufferedReader.close();
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }
        }
        return buffer.toString();
    }

    public static String setMainContent(String content) {
        StringBuffer buffer = new StringBuffer();
        File file = new File(gateMain);
        if (file != null && file.exists()) {
            PrintWriter printWriter = null;
            try {
                printWriter = new PrintWriter(new FileWriter(file));
                printWriter.println(content);
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }

    public void initGate(String gateHome) {
        Out.prln("Initialising GATE...");
        try {
            Gate.setGateHome(new File(gateHome));
            Gate.init();
        } catch (GateException e) {
            e.printStackTrace();
        }
        Out.prln("GATE initialised!");
    }

    public void initAnnie() {
        Out.prln("Initialising ANNIE...");
        File pluginsHome = Gate.getPluginsHome();
        File anniePlugin = new File(pluginsHome, "ANNIE");
        File annieGapp = new File(anniePlugin, "ANNIE_with_defaults.gapp");
        try {
            corpusController = (CorpusController) PersistenceManager.loadObjectFromFile(annieGapp);
            Out.prln("ANNIE loaded!");
        } catch (PersistenceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ResourceInstantiationException e) {
            e.printStackTrace();
        }
    }

    public Document createDocumentFromUrl(URL url) {
        Document document = null;
        try {
            corpus = Factory.newCorpus("Economy");
            FeatureMap params = Factory.newFeatureMap();
            params.put("sourceUrl", url);
            params.put("preserveOriginalContent", new Boolean(true));
            params.put("collectRepositioningInfo", new Boolean(true));
            params.put("markupAware", new Boolean(false));
            document = (Document) Factory.createResource("gate.corpora.DocumentImpl", params);
            corpus.add(document);
            corpusController.setCorpus(corpus);
        } catch (ResourceInstantiationException e) {
            e.printStackTrace();
        }
        return document;
    }

    public Document createDocumentFromString(String content) {
        Document document = null;
        try {
            corpus = Factory.newCorpus("Economy");
            FeatureMap params = Factory.newFeatureMap();
            params.put("stringContent", content);
            params.put("preserveOriginalContent", new Boolean(true));
            params.put("collectRepositioningInfo", new Boolean(true));
            params.put("markupAware", new Boolean(false));
            document = (Document) Factory.createResource("gate.corpora.DocumentImpl", params);
            corpus.add(document);
            corpusController.setCorpus(corpus);
        } catch (ResourceInstantiationException e) {
            e.printStackTrace();
        }
        return document;
    }

    public void executeAnnie() {
        Out.prln("Running ANNIE...");
        try {
            corpusController.execute();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Out.prln("ANNIE completed!");
    }

    public void getResult(ArrayList<String> results) {
        Iterator iterator = corpus.iterator();
        while (iterator.hasNext()) {
            Document doc = (Document) iterator.next();
            AnnotationSet defaultAnnotationSet = doc.getAnnotations();
            Set annotationTypesRequired = new HashSet();
            annotationTypesRequired.add("Sentence");
            annotationTypesRequired.add("EnterpriseName");
            annotationTypesRequired.add("EnterpriseOwner");
            annotationTypesRequired.add("EnterpriseAddress");
            annotationTypesRequired.add("EnterpriseProduct");
            annotationTypesRequired.add("EnterpriseProfit");
            annotationTypesRequired.add("EnterpriseExport");
            annotationTypesRequired.add("EnterpriseImport");
            Set<Annotation> annotationRequired =
                    new HashSet<Annotation>(defaultAnnotationSet.get(annotationTypesRequired));
            String xmlDocument = doc.toXml(annotationRequired, false);
            StringBuffer buffer = new StringBuffer();
            buffer.append("<Enterprise>");
            buffer.append(xmlDocument);
            buffer.append("</Enterprise>");
            results.add(buffer.toString());
        }
    }

    public void getResultTraining(ArrayList<String> results) {
        Iterator iterator = corpus.iterator();
        while (iterator.hasNext()) {
            Document doc = (Document) iterator.next();
            AnnotationSet defaultAnnotationSet = doc.getAnnotations();
            Set annotationTypesRequired = new HashSet();
            annotationTypesRequired.add("Sentence");
            annotationTypesRequired.add("EnterpriseName");
            annotationTypesRequired.add("EnterpriseOwner");
            annotationTypesRequired.add("EnterpriseAddress");
            annotationTypesRequired.add("EnterpriseProduct");
            Set<Annotation> annotationRequired =
                    new HashSet<Annotation>(defaultAnnotationSet.get(annotationTypesRequired));
            String xmlDocument = doc.toXml(annotationRequired, false);
            StringBuffer buffer = new StringBuffer();
            buffer.append("<Enterprise>");
            buffer.append(xmlDocument);
            buffer.append("</Enterprise>");
            results.add(buffer.toString());
        }
    }

    class SortedAnnotationList extends Vector {

        public SortedAnnotationList() {
            super();
        }

        public boolean addSortedExclusive(Annotation annot) {
            Annotation currAnot = null;
            for (int i = 0; i < size(); ++i) {
                currAnot = (Annotation) get(i);
                if (annot.overlaps(currAnot)) {
                    return false;
                }
            }
            long annotStart = annot.getStartNode().getOffset().longValue();
            long currStart;
            for (int i = 0; i < size(); ++i) {
                currAnot = (Annotation) get(i);
                currStart = currAnot.getStartNode().getOffset().longValue();
                if (annotStart < currStart) {
                    insertElementAt(annot, i);
                    return true;
                }
            }
            int size = size();
            insertElementAt(annot, size);
            return true;
        }
    }
}
