import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Form2 {

    static void rewriteForm1(String strings[], ArrayList<String> imgs, ArrayList<String> arrayX, ArrayList<String> arrayY) {
        String filePath = "form1.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // обновляем значения
            Controller.updateElementValue(doc, "Document", "Name", strings[0]);
            Controller.updateElementValue(doc, "Document", "Number", strings[1]);
            Controller.updateElementValue(doc, "Document", "Date", strings[2]);
            Controller.updateElementValue(doc, "Document", "IssueOrgan", strings[3]);

            Controller.updateElementValue(doc, "NewParcels", "CadastralBlock", strings[4]);
            Controller.updateElementValueIntros(doc, "Area", "Area", strings[5]);
            Controller.updateElementValue(doc, "Area", "Unit", strings[6]);

            Controller.updateElementValueIntro(doc, "Entity_Spatial", "Ent_Sys", strings[7]);


            Controller.updateElementValueIntro(doc, "NewOrdinate", "X", strings[8]);
            Controller.updateElementValueIntro(doc, "NewOrdinate", "Y", strings[9]);
            Controller.updateElementValueIntro(doc, "NewOrdinate", "Num_Geopoint", "1");


            Controller.updateElementValue(doc, "NewParcels", "Note", strings[10]);

            Controller.updateElementValueIntro(doc, "Utilization", "ByDoc", strings[11]);

            Controller.updateElementValueIntro(doc, "Category", "Category", strings[12]);
            Controller.updateElementValueIntro(doc, "Coord_System", "Cs_Id", strings[7]);
            Controller.updateElementValueIntro(doc, "Coord_System", "Name", strings[13]);


            //определение длины массива имгс и в зависимость от этого добавление картинок
            Controller.updateElementValueIntro(doc, "AppliedFile", "Name", strings[14]);
            if (imgs.size() > 0) {
                for (int i = 0; i <= imgs.size() - 1; i++) {    //добавить нужное количество новых
                    Controller.addElement(doc, "ParcelSchema_In_Block", "AppliedFile", "Name", imgs.get(i));
                }
            }

            if (arrayX.size() > 0 && arrayY.size() > 0) {
                for (int i = 0; i <= arrayX.size() - 1; i++) {    //добавить нужное количество новых
                    int d = i + 2;
                    String d1 = String.valueOf(d);
                    Node nodeName = doc.getElementsByTagName("Spatial_Element").item(0);
                    Element newElement = doc.createElement("Spelement_Unit");
                    nodeName.appendChild(newElement);
                    newElement.setAttribute("Type_Unit", "Точка");


                    Element newElement1 = doc.createElement("NewOrdinate");
                    newElement.appendChild(newElement1);
                    newElement1.setAttribute("Num_Geopoint", d1);
                    newElement1.setAttribute("Y", arrayY.get(i));
                    newElement1.setAttribute("X", arrayX.get(i));
                }
            }
            Controller.save(doc);
//
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }
}

