import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


class Controller {
    private static File file;
    public static boolean answere;
    public static ArrayList<String> imgs = new ArrayList<String>();
    public static ArrayList<String> arrayX = new ArrayList<String>();
    public static ArrayList<String> arrayY = new ArrayList<String>();

//    static void rewriteForm1(String strings [],ArrayList <String> imgs, ArrayList <String> arrayX, ArrayList <String> arrayY) {
//        String filePath = "form1.xml";
//        File xmlFile = new File(filePath);
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder;
//        try {
//            builder = factory.newDocumentBuilder();
//            Document doc = builder.parse(xmlFile);
//            doc.getDocumentElement().normalize();
//
//            // обновляем значения
//            updateElementValue(doc, "Document", "Name", strings[0]);
//            updateElementValue(doc, "Document", "Number", strings[1]);
//            updateElementValue(doc, "Document", "Date", strings[2]);
//            updateElementValue(doc, "Document", "IssueOrgan", strings[3]);
//
//            updateElementValue(doc, "NewParcels", "CadastralBlock", strings[4]);
//            updateElementValueIntros(doc, "Area", "Area", strings[5]);
//            updateElementValue(doc, "Area", "Unit", strings[6]);
//
//            updateElementValueIntro(doc, "Entity_Spatial", "Ent_Sys", strings[7]);
//
//
//            updateElementValueIntro(doc, "NewOrdinate", "X", strings[8]);
//            updateElementValueIntro(doc, "NewOrdinate", "Y", strings[9]);
//            updateElementValueIntro(doc, "NewOrdinate", "Num_Geopoint", "1");
//
//
//            updateElementValue(doc, "NewParcels", "Note", strings[10]);
//
//            updateElementValueIntro(doc, "Utilization", "ByDoc", strings[11]);
//
//            updateElementValueIntro(doc, "Category", "Category", strings[12]);
//            updateElementValueIntro(doc, "Coord_System", "Cs_Id", strings[7]);
//            updateElementValueIntro(doc, "Coord_System", "Name", strings[13]);
//
//
//            //определение длины массива имгс и в зависимость от этого добавление картинок
//            updateElementValueIntro(doc, "AppliedFile", "Name", strings[14]);
//            if (imgs.size() > 0) {
//                for (int i = 0; i <= imgs.size() - 1; i++) {    //добавить нужное количество новых
//                    addElement(doc, "ParcelSchema_In_Block", "AppliedFile", "Name", imgs.get(i));
//                }
//            }
//
//            if (arrayX.size() > 0 && arrayY.size() > 0) {
//                for (int i = 0; i <= arrayX.size() - 1; i++) {    //добавить нужное количество новых
//                    int d = i + 2;
//                    String d1 = String.valueOf(d);
//                    Node nodeName = doc.getElementsByTagName("Spatial_Element").item(0);
//                    Element newElement = doc.createElement("Spelement_Unit");
//                    nodeName.appendChild(newElement);
//                    newElement.setAttribute("Type_Unit", "Точка");
//
//
//                    Element newElement1 = doc.createElement("NewOrdinate");
//                    newElement.appendChild(newElement1);
//                    newElement1.setAttribute("Num_Geopoint", d1);
//                    newElement1.setAttribute("Y", arrayY.get(i));
//                    newElement1.setAttribute("X", arrayX.get(i));
//                }
//            }

            //запишем отредактированный элемент в файл
            //или выведем в консоль

//            String guid = "GUID";
//            doc.getDocumentElement().normalize();
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            DOMSource source = new DOMSource(doc);
//            StreamResult result = new StreamResult(new File(System.getProperty("user.dir"),"SchemaParcels_" + guid+ ".xml"));
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            transformer.transform(source, result);
//
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Готово");
//            alert.setHeaderText("");
//            alert.setContentText("Файл сохранен" );
//            alert.show();

//            save(doc);
////
//        } catch (Exception exc) {
//            exc.printStackTrace();
//        }
//    }


    public static void save (Document doc) throws TransformerException {

        String guid = "GUID";
        doc.getDocumentElement().normalize();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        String fileName = System.getProperty("user.dir") + "\\" + "SchemaParcels_" + guid + ".xml";
        File file = new File(fileName);
        if (file.exists()) {
        infoBox("Внимание!!", "Такой фал уже есть", "Перезаписать?"); }

        if (answere || !file.exists()) {
            StreamResult result = new StreamResult(new File(fileName));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Готово");
            alert.setHeaderText("");
            alert.setContentText("Файл сохранен");
            alert.show();
            Form.form1.close();
        }
    }

    // изменяем значение существующего элемента
    public static void updateElementValue(Document doc, String tagName, String elementName, String newText) {
        Node nodeName = doc.getElementsByTagName(elementName).item(0);
        nodeName.setTextContent(newText);
    }

    // изменяем значение существующего элемента атрибут
    static void updateElementValueIntro(Document doc, String elementName, String attrName, String newText) {
        Node nodeName = doc.getElementsByTagName(elementName).item(0);
        NamedNodeMap attributes = nodeName.getAttributes();
        Node atr = attributes.getNamedItem(attrName);
        atr.setTextContent(newText);
    }

    // изменяем значение существующего вложенные тэги
    static void updateElementValueIntros(Document doc, String tagName, String elementName, String newText) {
        Node nodeName = doc.getElementsByTagName(tagName).item(0);
        NodeList nodeList = nodeName.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nextNode = nodeList.item(i);

            if (nextNode.getNodeName().equals(elementName)) {
                nextNode.setTextContent(newText);
            }
        }
    }
    static void addElement(Document doc, String parentTag, String childTag, String attrName, String content) {
        Node nodeName = doc.getElementsByTagName(parentTag).item(0);
        Element newElement = doc.createElement(childTag);
        nodeName.appendChild(newElement);
        newElement.setAttribute(attrName, content);
    }

    private static void infoBox(String infoMessage, String headerMessage, String qwestion)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(infoMessage);
        alert.setHeaderText(headerMessage);
        alert.setContentText(qwestion);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            answere = true;
        } else {
            alert.close();
        }
    }

    static void openFile(TextField feldName, Stage formNumber) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открыть файл");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Все типы файлов (*.*)", "*.*");
        fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showOpenDialog(formNumber);
        feldName.setText("Images\\" + file.getName());
        // return file;
    }

    static void noFocusImg(TextField textField) {
        textField.focusedProperty().addListener( (obs, oldValue, newValue) -> {
            if (newValue) { }
            else if (textField.getLength() != 0) {
                System.out.println(textField.getLength());
                infoBox("Внимание!!", "Проверьте данные новой картинки", "Все правильно?");
                if (answere) {
                    //добавленеи в массив текста из окна ввода
                    imgs.add(textField.getText());
                    textField.setDisable(true);
                }
            }
        });
    }

    static void noFocusPoint(TextField textField1, TextField textField2) {
        textField2.focusedProperty().addListener( (obs, oldValue, newValue) -> {
            if (newValue) {
            } else if (textField1.getLength() != 0 | textField2.getLength() != 0) {
                //уведомление, что картинка добавлена
                infoBox("Уведомление", "Проверьте данные новой точки", "Все правильно?");
                if (answere) {
                    //добавленеи в массив текста
                    arrayX.add(textField1.getText());
                    arrayY.add(textField2.getText());
                    //выключение поля
                    textField1.setDisable(true);
                    textField2.setDisable(true);
                }
            }
        });
    }
}


