package economyie.controllers;

import gate.*;
import gate.corpora.RepositioningInfo;
import gate.creole.ExecutionException;
import gate.creole.ResourceInstantiationException;
import gate.persist.PersistenceException;
import gate.util.GateException;
import gate.util.Out;
import gate.util.persistence.PersistenceManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by Duong on 19/02/2016.
 */
public class GateController {

    private CorpusController corpusController;
    private Corpus corpus;

    public GateController(String gateHome) {
        initGate(gateHome);
        initAnnie();
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

    public void initCorpus(String[] links) {
        try {
            corpus = Factory.newCorpus("Economy");
            for (String link : links) {
                URL url = new URL(link);
                FeatureMap params = Factory.newFeatureMap();
                params.put("sourceUrl", url);
                params.put("preserveOriginalContent", new Boolean(true));
                params.put("collectRepositioningInfo", new Boolean(true));
                params.put("markupAware", new Boolean(false));
                Out.prln("Creating doc for " + url);
                Document document = (Document) Factory.createResource("gate.corpora.DocumentImpl", params);
                Out.prln(document.toString());
                corpus.add(document);
            }
            corpusController.setCorpus(corpus);
        } catch (ResourceInstantiationException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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
        int count = 0;
        String startTagPart_1 = "<span GateID=\"";
        String startTagPart_2 = "\" title=\"";
        String startTagPart_3 = "\" style=\"background:Red;\">";
        String endTag = "</span>";
        while (iterator.hasNext()) {
            Document doc = (Document) iterator.next();
            AnnotationSet defaultAnnotationSet = doc.getAnnotations();
            Set annotationTypesRequired = new HashSet();
            annotationTypesRequired.add("Person");
            annotationTypesRequired.add("Location");
            annotationTypesRequired.add("Percent");
            Set<Annotation> annotationRequired =
                    new HashSet<Annotation>(defaultAnnotationSet.get(annotationTypesRequired));
            FeatureMap features = doc.getFeatures();
            String originalContent = (String)
                    features.get(GateConstants.ORIGINAL_DOCUMENT_CONTENT_FEATURE_NAME);
            RepositioningInfo info = (RepositioningInfo)
                    features.get(GateConstants.DOCUMENT_REPOSITIONING_INFO_FEATURE_NAME);
            ++count;
            File file = new File("original_content_" + count + ".txt");
            Out.prln("File name: '" + file.getAbsolutePath() + "'");
            if (originalContent != null && info != null) {
                Out.prln("OrigContent and reposInfo existing. Generate file...");
                Iterator it = annotationRequired.iterator();
                Annotation currAnnot;
                SortedAnnotationList sortedAnnotations = new SortedAnnotationList();
                while (it.hasNext()) {
                    currAnnot = (Annotation) it.next();
                    sortedAnnotations.addSortedExclusive(currAnnot);
                }
                StringBuffer editableContent = new StringBuffer(originalContent);
                long insertPositionEnd;
                long insertPositionStart;
                Out.prln("Unsorted annotations count: " + annotationRequired.size());
                Out.prln("Sorted annotations count: " + sortedAnnotations.size());
                for (int i = sortedAnnotations.size() - 1; i >= 0; --i) {
                    currAnnot = (Annotation) sortedAnnotations.get(i);
                    insertPositionStart =
                            currAnnot.getStartNode().getOffset().longValue();
                    insertPositionStart = info.getOriginalPos(insertPositionStart);
                    insertPositionEnd = currAnnot.getEndNode().getOffset().longValue();
                    insertPositionEnd = info.getOriginalPos(insertPositionEnd, true);
                    if (insertPositionEnd != -1 && insertPositionStart != -1) {
                        editableContent.insert((int) insertPositionEnd, endTag);
                        editableContent.insert((int) insertPositionStart, startTagPart_3);
                        editableContent.insert((int) insertPositionStart,
                                currAnnot.getType());
                        editableContent.insert((int) insertPositionStart, startTagPart_2);
                        editableContent.insert((int) insertPositionStart,
                                currAnnot.getId().toString());
                        editableContent.insert((int) insertPositionStart, startTagPart_1);
                    }
                }
                try {
                    FileWriter writer = new FileWriter(file);
                    writer.write(editableContent.toString());
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (originalContent != null) {
                Out.prln("OrigContent existing. Generate file...");
                Iterator it = annotationRequired.iterator();
                Annotation currAnnot;
                SortedAnnotationList sortedAnnotations = new SortedAnnotationList();
                while (it.hasNext()) {
                    currAnnot = (Annotation) it.next();
                    sortedAnnotations.addSortedExclusive(currAnnot);
                }
                StringBuffer editableContent = new StringBuffer(originalContent);
                long insertPositionEnd;
                long insertPositionStart;
                Out.prln("Unsorted annotations count: " + annotationRequired.size());
                Out.prln("Sorted annotations count: " + sortedAnnotations.size());
                for (int i = sortedAnnotations.size() - 1; i >= 0; --i) {
                    currAnnot = (Annotation) sortedAnnotations.get(i);
                    insertPositionStart =
                            currAnnot.getStartNode().getOffset().longValue();
                    insertPositionEnd = currAnnot.getEndNode().getOffset().longValue();
                    if (insertPositionEnd != -1 && insertPositionStart != -1) {
                        editableContent.insert((int) insertPositionEnd, endTag);
                        editableContent.insert((int) insertPositionStart, startTagPart_3);
                        editableContent.insert((int) insertPositionStart,
                                currAnnot.getType());
                        editableContent.insert((int) insertPositionStart, startTagPart_2);
                        editableContent.insert((int) insertPositionStart,
                                currAnnot.getId().toString());
                        editableContent.insert((int) insertPositionStart, startTagPart_1);
                    }
                }
                FileWriter writer = null;
                try {
                    writer = new FileWriter(file);
                    writer.write(editableContent.toString());
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Out.prln("Content : " + originalContent);
                Out.prln("Repositioning: " + info);
            }
            String xmlDocument = doc.toXml(annotationRequired, false);
            try {
                FileWriter writer = new FileWriter("tagged_content_" + count + ".xml");
                writer.write(xmlDocument);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
