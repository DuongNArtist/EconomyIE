package economyie.utils;

//import com.ids.gate.entities.PageFB;
//import com.ids.gate.entities.Product;
//import com.ids.gate.utils.Helper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class DOMParser {

    private Document document;
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;

    public DOMParser() {
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {

        }
    }

    public void importXMLDocument(String xmlContent) {
        try {
            document = builder.parse(new InputSource(new StringReader(xmlContent)));
            document.getDocumentElement().normalize();
        } catch (SAXException | IOException ex) {

        }
    }

    public String getNodeValueFirstByTagName(String tagName) {
        NodeList nodeList = document.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }

    public String getNodeValueFirstByTagName(Element e, String tagName) {
        NodeList nodeList = e.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }

    public List<String> getNodeValuesByTagName(Element e, String tagName) {
        List<String> values = new ArrayList<>();
        NodeList nodeList = e.getElementsByTagName(tagName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            values.add(nodeList.item(i).getTextContent());
        }
        return values;
    }

    public List<String> getNodeValuesByTagName(String tagName) {
        List<String> values = new ArrayList<>();
        NodeList nodeList = document.getElementsByTagName(tagName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            values.add(nodeList.item(i).getTextContent());
        }
        return values;
    }

    public List<String> getNodeValuesChildTag(String parentTag, String childTag) {
        List<String> values = new ArrayList<>();
        NodeList parentNode = document.getElementsByTagName(parentTag);
        for (int i = 0; i < parentNode.getLength(); i++) {
            Node node = parentNode.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) node;
                NodeList childNode = e.getElementsByTagName(childTag);
                for (int j = 0; j < childNode.getLength(); j++) {
                    values.add(childNode.item(j).getTextContent());
                }
            }
        }
        return values;
    }

    public String listToString(List<String> strs, String splitChar) {
        StringBuilder result = new StringBuilder();
        int size = strs.size();
        for (int i = 0; i < size; i++) {
            result.append(strs.get(i));
            if (i < size - 1) {
                result.append(splitChar);
            }
        }
        return result.toString();
    }
}
