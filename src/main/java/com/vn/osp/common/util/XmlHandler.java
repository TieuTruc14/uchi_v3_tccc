package com.vn.osp.common.util;

import com.vn.osp.controller.SystemController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class XmlHandler {

    public static String getValueNode(String node) {
        String valueNode = "";
        File dir = new File(SystemProperties.getProperty("system_backup_folder"));
        File file = new File(dir, "backup-config.xml");
        //InputStream inputStream = XmlHandler.class.getClassLoader().getResourceAsStream("backup-config.xml");
        InputStream inputStream = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            inputStream = new FileInputStream(file);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputStream);

            Element element = doc.getDocumentElement();
            NodeList nodes = element.getChildNodes();
            /*for (int i = 0; i < nodes.getLength(); i++) {
                //System.out.println("" + nodes.item(i).getTextContent());
            }*/
            valueNode = getString(node, element);
            return valueNode;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return valueNode;
    }

    public static void setValueNode(String node, String value) {
        InputStream inputStream = XmlHandler.class.getClassLoader().getResourceAsStream("backup-config.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputStream);
            Element element = doc.getDocumentElement();
            NodeList nodes = element.getChildNodes();
            setString(node, element, value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected static void setString(String tagName, Element element, String value) {
        NodeList list = element.getElementsByTagName(tagName);
        if (list != null && list.getLength() > 0) {
            NodeList subList = list.item(0).getChildNodes();
            if (subList != null && subList.getLength() > 0) {
                subList.item(0).setNodeValue(value);
            }
        }
    }

    protected static String getString(String tagName, Element element) {
        NodeList list = element.getElementsByTagName(tagName);
        if (list != null && list.getLength() > 0) {
            NodeList subList = list.item(0).getChildNodes();
            if (subList != null && subList.getLength() > 0) {
                return subList.item(0).getNodeValue();
            }
        }
        return null;
    }
    public static void checkExitsAndWriteFile(String content){
        /*File dir = new File(System.getProperty("user.dir")+"\\src\\main\\resources");*/
        File dir = new File(SystemProperties.getProperty("system_backup_folder"));
        dir.mkdirs();
        FileOutputStream fop = null;
        File file = new File(dir, "backup-config.xml");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fop = new FileOutputStream(file);
            byte[] contentInBytes = content.getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static Document createDoc() {
        InputStream inputStream = XmlHandler.class.getClassLoader().getResourceAsStream("backup-config.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputStream);
            return doc;
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
