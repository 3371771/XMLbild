import javafx.scene.control.Alert;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;



class Contrller {

//    private static String name;
//    private static String number;
//    private static String date;
//    private static String osmuName;
//    private static String cadastr;
//    private static String area;
//    private static String unit;
//    private static String id1;
//    private static String point1x;
//    private static String point1y;
//    private static String adres;
//    private static String util;
//    private static String category;
//    private static String img;

//    static void bildForm1(String strings []) {
//        name = strings[0];
//        number = stringNumber;
//        date = stringDate;
//        osmuName = stringOSMUname;
//        cadastr = stringCadastr;
//        area = stringArea;
//        unit = stringUnit;
//        id1 = stringID1;
//        point1x = stringPoint1X;
//        point1y = stringPoint1Y;
//        adres = stringAdres;
//        util = stringUtil;
//        category = stringCategory;
//        img = stringImg;
//    }

    static void rewriteForm1(String strings [],ArrayList <String> imgs, ArrayList <String> arrayX, ArrayList <String> arrayY) {
        String filePath = "form1.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // обновляем значения
            updateElementValue(doc, "Document", "Name", strings[0]);
            updateElementValue(doc, "Document", "Number", strings[1]);
            updateElementValue(doc, "Document", "Date", strings[2]);
            updateElementValue(doc, "Document", "IssueOrgan", strings[3]);

            updateElementValue(doc, "NewParcels", "CadastralBlock", strings[4]);
            updateElementValueIntros(doc, "Area" ,"Area", strings[5]);
            updateElementValue(doc, "Area", "Unit", strings[6]);

            updateElementValueIntro(doc, "Entity_Spatial", "Ent_Sys", strings[7]);


            updateElementValueIntro(doc, "NewOrdinate" ,"X", strings[8]);
            updateElementValueIntro(doc, "NewOrdinate" ,"Y", strings[9]);
            updateElementValueIntro(doc,"NewOrdinate", "Num_Geopoint", "1");


            updateElementValue(doc, "NewParcels" ,"Note", strings[10]);

            updateElementValueIntro(doc, "Utilization", "ByDoc", strings[11]);


            updateElementValueIntro(doc, "Category", "Category", strings[12]);
            updateElementValueIntro(doc, "Coord_System", "Cs_Id", strings[7]);
            updateElementValueIntro(doc, "Coord_System", "Name", strings[13]);


            //определение длины массива имгс и в зависимость от этого добавление картинок
            updateElementValueIntro(doc, "AppliedFile", "Name", strings[14]);
            if (imgs.size() > 0) {
                for (int i = 0; i <= imgs.size()-1; i++)
                {    //добавить нужное количество новых
                    addElement(doc,"ParcelSchema_In_Block","AppliedFile", "Name",imgs.get(i));
                }
            }

            if (arrayX.size() > 0 && arrayY.size() > 0 ) {
                for (int i = 0; i <= arrayX.size()-1; i++)
                {    //добавить нужное количество новых
                    int d = i+2;
                    String d1 = String.valueOf(d);
                    Node nodeName = doc.getElementsByTagName("Spatial_Element").item(0);
                    Element newElement = doc.createElement("Spelement_Unit");
                    nodeName.appendChild(newElement);
                    newElement.setAttribute("Type_Unit", "Точка" );


                    Element newElement1 = doc.createElement("NewOrdinate");
                    newElement.appendChild(newElement1);
                    newElement1.setAttribute("Num_Geopoint", d1 );
                    newElement1.setAttribute("Y", arrayY.get(i) );
                    newElement1.setAttribute("X", arrayX.get(i) );
                }
            }


            //запишем отредактированный элемент в файл
            //или выведем в консоль

            String guid = "GUID";
            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(System.getProperty("user.dir"),"SchemaParcels_" + guid+ ".xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Готово");
            alert.setHeaderText("");
            alert.setContentText("Файл сохранен" );
            alert.show();

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

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
    private static void addElement (Document doc,String parentTag, String childTag,String attrName, String content) {
        Node nodeName = doc.getElementsByTagName(parentTag).item(0);
        Element newElement = doc.createElement(childTag);
        nodeName.appendChild(newElement);
        newElement.setAttribute(attrName, content);
    }
}


