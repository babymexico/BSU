package com.example.coursework;

import com.example.coursework.test_json.Expressions;
import com.example.coursework.test_json.Root;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class XmlParser {

    private static final String ELEMENT_NODE_NAME = "element";
    private static final String EXPRESSION_NODE_NAME = "expression";
    private static final String NAME_NODE_NAME = "name";
    private static final String EXPRESSIONS_NODE_NAME = "expressions";

    public List<String> parse(String fileName) {
        Root root = new Root();

        File file = new File(fileName);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            doc = dbf.newDocumentBuilder().parse(file);
        } catch (Exception e) {
            System.out.println("Error while parsing XML: " + e.toString());
            return null;
        }

        Node rootNode = doc.getFirstChild();
        NodeList rootChilds = rootNode.getChildNodes();

        String mainName = null;
        Node expressionsNode = null;
        for (int i = 0; i < rootChilds.getLength(); i++) {
            if (rootChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            switch (rootChilds.item(i).getNodeName()) {
                case NAME_NODE_NAME: {
                    mainName = rootChilds.item(i).getTextContent();
                    break;
                }
                case EXPRESSIONS_NODE_NAME: {
                    expressionsNode = rootChilds.item(i);
                    break;
                }
            }
        }

        if (expressionsNode == null) {
            return null;
        }

        List<Expressions> expressionsList = new ArrayList<>();
        NodeList expressionsChilds = expressionsNode.getChildNodes();
        List<String> expressions = new ArrayList<>();
        for (int i = 0; i < expressionsChilds.getLength(); i++) {
            if (expressionsChilds.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if (!expressionsChilds.item(i).getNodeName().equals(ELEMENT_NODE_NAME)) {
                continue;
            }

            String exp = "";
            NodeList elementChilds = expressionsChilds.item(i).getChildNodes();
            for (int j = 0; j < elementChilds.getLength(); j++) {
                if (elementChilds.item(j).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                switch (elementChilds.item(j).getNodeName()) {
                    case EXPRESSION_NODE_NAME: {
                        exp = elementChilds.item(j).getTextContent();
                        expressions.add(exp);
                        break;
                    }
                }
            }
            Expressions expression = new Expressions(exp);
            expressionsList.add(expression);
        }

        root.setName(mainName);
        root.setExpressions(expressionsList);

        return expressions;
    }

    public void writeInXml(String filename, Vector<Double> results) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element rootElement = document.createElement("Results");
            document.appendChild(rootElement);

            for (Double result : results) {
                Element resultElement = document.createElement("Result");
                resultElement.appendChild(document.createTextNode(result.toString()));
                rootElement.appendChild(resultElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(filename.endsWith(".xml") ? filename : filename + ".xml");
            transformer.transform(source, result);

        } catch (ParserConfigurationException | javax.xml.transform.TransformerException e) {
            e.printStackTrace();
        }
    }
}
