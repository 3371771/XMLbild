import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;


class Contrller {

    private static String name;
    private static String number;
    private static String date;
    private static String osmuName;
    private static String cadastr;
    private static String area;
    private static String unit;
    private static String id1;
    private static String point1x;
    private static String point1y;
    private static String adres;
    private static String util;
    private static String category;
    private static String img;

    static void bildForm1(String stringName, String stringNumber, String stringDate, String stringOSMUname, String stringCadastr, String stringArea, String stringUnit,
                          String stringID1, String stringPoint1X, String stringPoint1Y, String stringAdres, String stringUtil, String stringCategory, String stringImg) {
        name = stringName;
        number = stringNumber;
        date = stringDate;
        osmuName = stringOSMUname;
        cadastr = stringCadastr;
        area = stringArea;
        unit = stringUnit;
        id1 = stringID1;
        point1x = stringPoint1X;
        point1y = stringPoint1Y;
        adres = stringAdres;
        util = stringUtil;
        category = stringCategory;
        img = stringImg;
    }

    static void rewriteForm1() {
        String filePath = "form1.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // обновляем значения
            updateElementValue(doc, "Document", "Name", name);
            updateElementValue(doc, "Document", "Number", number);
            updateElementValue(doc, "Document", "Date", date);
            updateElementValue(doc, "Document", "IssueOrgan", osmuName);

            updateElementValue(doc, "NewParcels", "CadastralBlock", cadastr);
            updateElementValueIntros(doc, "Area" ,"Area", area);
            updateElementValue(doc, "Area", "Unit", unit);


            updateElementValueIntro(doc, "Entity_Spatial", "Ent_Sys", id1);

             updateElementValueIntro(doc, "NewOrdinate" ,"X", point1x);
             updateElementValueIntro(doc, "NewOrdinate" ,"Y", point1y);

               updateElementValue(doc, "NewParcels" ,"Note", adres);

            updateElementValueIntro(doc, "Utilization", "ByDoc", util);


            updateElementValueIntro(doc, "Category", "Category", category);
            updateElementValueIntro(doc, "Coord_System", "Cs_Id", id1);
            updateElementValueIntro(doc, "AppliedFile", "Name", img);

            // добавляем новый элемент
            // addElement(doc);

            // запишем отредактированный элемент в файл
            // или выведем в консоль
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("form1Done.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
            System.out.println("XML успешно изменен!");

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

//    // добавили элемент paradigm
//    private static void addElement(Document doc) {
//        NodeList languages = doc.getElementsByTagName("Document");
//        Element lang = null;
//
//        //проходим по каждому элементу Language
//        for(int i=0; i<languages.getLength(); i++){
//            lang = (Element) languages.item(i);
//            Element paradigmElement = doc.createElement("paradigm");
//            paradigmElement.appendChild(doc.createTextNode("oop"));
//            lang.appendChild(paradigmElement);
//        }
    // }

    // изменяем значение существующего элемента
    private static void updateElementValue(Document doc, String tagName, String elementName, String newText) {
        Node nodeName = doc.getElementsByTagName(elementName).item(0);
        nodeName.setTextContent(newText);
    }

    // изменяем значение существующего элемента атрибут
    private static void updateElementValueIntro(Document doc, String elementName, String attrName, String newText) {
        Node nodeName = doc.getElementsByTagName(elementName).item(0);
        NamedNodeMap attributes = nodeName.getAttributes();
        Node atr = attributes.getNamedItem(attrName);
        atr.setTextContent(newText);
        //nodeName.setTextContent(newText);
    }

    // изменяем значение существующего вложенные тэги
    private static void updateElementValueIntros(Document doc, String tagName, String elementName, String newText) {
        Node nodeName = doc.getElementsByTagName(tagName).item(0);
        NodeList nodeList = nodeName.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nextNode = nodeList.item(i);

            if (nextNode.getNodeName().equals(elementName)) {
                nextNode.setTextContent(newText);
            }


        }
    }
}


