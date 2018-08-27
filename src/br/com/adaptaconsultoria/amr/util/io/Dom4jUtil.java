package br.com.adaptaconsultoria.amr.util.io;

import javax.servlet.ServletInputStream;

import org.apache.tools.ant.filters.StringInputStream;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.dom.DOMCDATA;

/**
 * @author Will Zaniol
 */
public class Dom4jUtil {

	public static Document createDocument(String rootElementTagName) throws DocumentException {
		SAXReader reader = new SAXReader();
		StringInputStream inputStream = new StringInputStream("<" + rootElementTagName + "/>");
		return reader.read(inputStream);
	}

	public static Document loadFromFile(String fileName) throws DocumentException {
		DocumentFactory factory = DocumentFactory.getInstance();
		SAXReader reader = new SAXReader(factory);
		Document doc = reader.read(fileName);
		return doc;
	}

	public static Document loadFromStream(ServletInputStream inputStream) throws DocumentException {
		SAXReader reader = new SAXReader();
		return reader.read(inputStream);
	}

	public static Document loadFromString(String xml) throws DocumentException {
		SAXReader reader = new SAXReader();
		StringInputStream inputStream = new StringInputStream(xml);
		return reader.read(inputStream);
	}

	public static Element createElement(String leafName) {
		return createElement(leafName, null);
	}

	public static Element createElement(String leafName, String leafText) {
		Element leaf = DocumentFactory.getInstance().createElement(leafName);
		if (leafText != null) {
			leafText = leafText.replace("&", "");
			leaf.setText(leafText);
		}
		return leaf;
	}

	public static Element createElement(String leafName, String leafText, int tamanhoCampo) throws Exception {
		if (leafText != null && leafText.length() > tamanhoCampo)
			throw new Exception("Campo: " + leafName + ":" + leafText + " é maior do que o máximo permitido: " + tamanhoCampo);
		return createElement(leafName, leafText);
	}

	public static Attribute createAttribute(String id, String value) {
		Attribute att = DocumentFactory.getInstance().createAttribute(null, id, value);
		return att;
	}

	public static String getTextFromChildElement(Element element, String tagName, int i) {
		try {
			org.dom4j.Element e = (org.dom4j.Element) element.selectNodes("//" + tagName).get(i);
			return e.getText();
		} catch (Exception e) {
			return "";
		}
	}

	public static String getTextFromChildElement(Element element, String tagName) {
		try {
			org.dom4j.Element e = (org.dom4j.Element) element.selectNodes("//" + tagName).get(0);
			return e.getText();
		} catch (Exception e) {
			return "";
		}
	}

	public static Element createElementCData(String name, String value) {
		Element e = Dom4jUtil.createElement(name);
		e.add(new DOMCDATA(value == null ? "" : value.trim()));
		return e;
	}

}
