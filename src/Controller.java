import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.*;

import javax.print.Doc;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;


class Controller {
    static HashMap<String, String> data = new HashMap<>();

    static void save(Document doc, Stage form) throws TransformerException {

        UUID uuid = UUID.randomUUID();
        String guid = uuid.toString();
        updateElementValueIntro(doc, "eDocument", "GUID", guid);

        doc.getDocumentElement().normalize();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        String fileName = System.getProperty("user.dir") + "\\" + "SchemaParcels_" + guid + ".xml";
        //File file = new File(fileName);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Готово");
            alert.setHeaderText("");
            alert.setContentText("Файл сохранен");
            alert.show();
            form.close();
    }

    // изменяем значение существующего элемента
    static void updateElementValue(Document doc, String tagName, String elementName, String newText) {
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

    static Element addElement(Document doc, String parentTag, String childTag, String attrName, String content) {
        Node nodeName = doc.getElementsByTagName(parentTag).item(0);
        Element newElement = doc.createElement(childTag);
        nodeName.appendChild(newElement);
        newElement.setAttribute(attrName, content);
        return newElement;
    }

   // static void newElement (Document doc, String parentTag)

    static void openFile(TextField feldName, Stage formNumber) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открыть файл");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Все типы файлов (*.*)", "*.*");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(formNumber);
        feldName.setText("Images\\" + file.getName());
    }
}


