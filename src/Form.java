import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


class Form {

    //объявление
    private static NewField textName;
    private static NewField textNumber;
    private static NewField textDate;
    private static NewField textOSMUname;
    private static NewField textCadastr;
    private static NewField textArea;
    private static NewField textUnit;
    private static NewField textID;

    private static NewField textPoint1X;
    private static NewField textPoint1Y;

    private static NewField textAdres;
    private static NewField textUtil;
    private static NewField textCategory;
    private static NewField textCoordSys;
    private static NewField textImg;
    private static GridPane mainGrid;
    private static GridPane mainGrid1;

    private static int gridaddFeldNumber = 5;
    private static int grid1addRowNumber = 11;
    private static int numberPoitValue = 2;

    static Stage form1;
    private static int i;

    static void display() {
        form1 = new Stage();
        ScrollPane scrollPane = new ScrollPane();
        form1.setTitle("Форма 1");
        form1.getIcons().add(new Image("/res/form1.png"));
        form1.setHeight(650);
        form1.setWidth(400);
        form1.setResizable(false);

        //названия полей
        Label labelName = new Label("Название");
        Label labelNumber = new Label("Номер");
        Label labelDate = new Label("Дата");
        Label labelOSMUname = new Label("Название ОСМУ");
        Label labelCadastr = new Label("Кадастровый номер");
        Label labelArea = new Label("Area");
        Label labelUnit = new Label("Unit");
        Label labelID1 = new Label("ID1");
        Label labelPoint1X = new Label("Точка 1 X");
        Label labelPoint1Y = new Label("Точка 1 Y");
        Label labelAdres = new Label("Адрес");
        Label labelUtil = new Label("Применение");
        Label labelCategory = new Label("Категория");
        Label labelCoordSys = new Label("Система координат");
        Label labelImg = new Label("Изображения");

        //инициализация полей ввода
        textName = new NewField(" ");
        textNumber = new NewField(" ");
        textDate = new NewField(" ");
        textOSMUname = new NewField(" ");
        textCadastr = new NewField(" ");
        textArea = new NewField(" ");
        textUnit = new NewField(" ");
        textID = new NewField(" ");
        textPoint1X = new NewField(" ");
        textPoint1Y = new NewField(" ");
        textAdres = new NewField(" ");
        textUtil = new NewField(" ");
        textCategory = new NewField(" ");
        textCoordSys = new NewField(" ");
        textImg = new NewField(" ");


        //ивенты для кликов
        textName.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> textName.adToArray(Controller.data, "Name",textName.getText()));
        textNumber.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> textNumber.adToArray(Controller.data, "Number",textNumber.getText()));
        textDate.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> textDate.adToArray(Controller.data, "Date",textDate.getText()));
        textOSMUname.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> textOSMUname.adToArray(Controller.data, "OSMU",textOSMUname.getText()));
        textCadastr.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> textCadastr.adToArray(Controller.data, "Cadastr",textCadastr.getText()));
        textArea.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> textArea.adToArray(Controller.data, "Area",textArea.getText()));
        textUnit.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> textUnit.adToArray(Controller.data, "Unit",textUnit.getText()));
        textID.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> textID.adToArray(Controller.data, "ID",textCoordSys.getText()));
        textPoint1X.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> textPoint1X.adToArray(Controller.data, "X1",textPoint1X.getText()));
        textPoint1Y.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> textPoint1Y.adToArray(Controller.data, "Y1",textPoint1Y.getText()));
        textAdres.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> textAdres.adToArray(Controller.data, "Adres",textAdres.getText()));
        textUtil.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> textUtil.adToArray(Controller.data, "Util",textUtil.getText()));
        textCategory.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> textCategory.adToArray(Controller.data, "Category",textCategory.getText()));
        textCoordSys.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> textCoordSys.adToArray(Controller.data, "Coord",textCoordSys.getText()));
        textImg.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> textImg.onClick(form1,Controller.data, "Img"));


        //кнопка формирования XML
        Button cast = new Button("Создать XML");
        cast.setOnAction(e -> {
            //нужна будет точная цифра ниже
            if (Controller.data.size() <14) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("");
                alert.setContentText("Заполните все поля!");
                alert.show();
            } else {
                try {
                    getStrings();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                gridaddFeldNumber = 5;
                numberPoitValue = 2;
                grid1addRowNumber = 11;
                Controller.arrayX.clear();
                Controller.arrayY.clear();
                Controller.data.clear();
            }
        });

        //кнопка добавления картинок
        Button addImg = new Button("+");
        addImg.setOnAction(e -> {
            NewField newImg = new NewField(" ");
            mainGrid1.add(newImg, 1, gridaddFeldNumber);
            i++;
            gridaddFeldNumber++;
            newImg.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> newImg.onClick(form1,Controller.data, "newImg"+i));
        });

        //кнопка добавления точки
        Button addPoint = new Button("+");
        addPoint.setOnAction(e -> {
           // Controller.answere = false;
            Label newLabelPointX;
            Label newLabelPointY;
            TextField newText1;
            TextField newText2;

            newText1 = new TextField();
            newText2 = new TextField();

            //новые лэйблы с номером точки
            newLabelPointX = new Label("Точка" + " " + numberPoitValue + " " + "X");
            newLabelPointY = new Label("Точка" + " " + numberPoitValue + " " + "Y");
            //увеличиваем значенеи счетчика номра строки

            mainGrid.add(newLabelPointX, 0, grid1addRowNumber);
            mainGrid.add(newText1, 1, grid1addRowNumber);

            mainGrid.add(newLabelPointY, 0, grid1addRowNumber + 1);
            mainGrid.add(newText2, 1, grid1addRowNumber + 1);
            //увеличиваем значенеи счетчика номра строки т.к. строки 2
            grid1addRowNumber++;
            grid1addRowNumber++;
            numberPoitValue++;

            //сохранение при потере фокуса
            Controller.noFocusPoint(newText1,newText2);
        });

        ////первый блок разметка
        mainGrid = new GridPane();
        mainGrid.setAlignment(Pos.CENTER);
        mainGrid.setHgap(10);
        mainGrid.setVgap(10);
        mainGrid.setPadding(new Insets(25, 25, 10, 25));

        Text title = new Text("Форма 1");
        title.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
        mainGrid.add(title, 0, 0, 2, 1);

        mainGrid.add(labelName, 0, 1);
        mainGrid.add(textName, 1, 1);

        mainGrid.add(labelNumber, 0, 2);
        mainGrid.add(textNumber, 1, 2);

        mainGrid.add(labelDate, 0, 3);
        mainGrid.add(textDate, 1, 3);

        mainGrid.add(labelOSMUname, 0, 4);
        mainGrid.add(textOSMUname, 1, 4);

        mainGrid.add(labelCadastr, 0, 5);
        mainGrid.add(textCadastr, 1, 5);

        mainGrid.add(labelArea, 0, 6);
        mainGrid.add(textArea, 1, 6);

        mainGrid.add(labelUnit, 0, 7);
        mainGrid.add(textUnit, 1, 7);

        mainGrid.add(labelID1, 0, 8);
        mainGrid.add(textID, 1, 8);

        mainGrid.add(labelPoint1X, 0, 9);
        mainGrid.add(textPoint1X, 1, 9);

        mainGrid.add(labelPoint1Y, 0, 10);
        mainGrid.add(textPoint1Y, 1, 10);

        mainGrid.add(addPoint, 2, 10);

        //второй блок разметка
        mainGrid1 = new GridPane();
        mainGrid1.setAlignment(Pos.CENTER);
        mainGrid1.setHgap(10);
        mainGrid1.setVgap(10);
        mainGrid1.setPadding(new Insets(0, 25, 10, 25));

        mainGrid1.add(labelAdres, 0, 0);
        mainGrid1.add(textAdres, 1, 0);

        mainGrid1.add(labelUtil, 0, 1);
        mainGrid1.add(textUtil, 1, 1);

        mainGrid1.add(labelCategory, 0, 2);
        mainGrid1.add(textCategory, 1, 2);

        mainGrid1.add(labelCoordSys, 0, 3);
        mainGrid1.add(textCoordSys, 1, 3);

        mainGrid1.add(labelImg, 0, 4);
        mainGrid1.add(textImg, 1, 4);

        mainGrid1.add(addImg, 2, 4);

        //добавление гридов в бокс, его в сцену и отображение формы
        VBox vMain = new VBox(0);
        vMain.getChildren().addAll(mainGrid, mainGrid1);

        BorderPane bMain = new BorderPane();
        BorderPane.setMargin(bMain, new Insets(20));
        bMain.setCenter(vMain);
        bMain.setBottom(cast);
        BorderPane.setAlignment(cast, Pos.TOP_RIGHT);
        BorderPane.setMargin(cast, new Insets(15, 27, 20, 20));
        //скроллинг
        scrollPane.setContent(bMain);
        Scene sceneMain = new Scene(scrollPane);

        form1.setScene(sceneMain);
        form1.show();

        form1.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
                numberPoitValue = 2;
                grid1addRowNumber = 11;
                gridaddFeldNumber = 5;
                Controller.arrayX.clear();
                Controller.arrayY.clear();
                Controller.data.clear();
                form1.close();
            }
        });
    }

    private static void getStrings() throws IOException {
        rewriteForm1(Controller.arrayX,Controller.arrayY);
    }

    private static void rewriteForm1(ArrayList<String> arrayX, ArrayList<String> arrayY) {
        String filePath = "form1.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // обновляем значения
            Controller.updateElementValue(doc, "Document", "Name", Controller.data.get("Name"));
            Controller.updateElementValue(doc, "Document", "Number", Controller.data.get("Number"));
            Controller.updateElementValue(doc, "Document", "Date", Controller.data.get("Date"));
            Controller.updateElementValue(doc, "Document", "IssueOrgan", Controller.data.get("OSMU"));

            Controller.updateElementValue(doc, "NewParcels", "CadastralBlock", Controller.data.get("Cadastr"));
            Controller.updateElementValueIntros(doc, "Area", "Area", Controller.data.get("Area"));
            Controller.updateElementValue(doc, "Area", "Unit", Controller.data.get("Unit"));

            Controller.updateElementValueIntro(doc, "Entity_Spatial", "Ent_Sys", Controller.data.get("ID"));

            Controller.updateElementValueIntro(doc, "NewOrdinate", "X", Controller.data.get("X1"));
            Controller.updateElementValueIntro(doc, "NewOrdinate", "Y", Controller.data.get("Y1"));
            Controller.updateElementValueIntro(doc, "NewOrdinate", "Num_Geopoint", "1");


            Controller.updateElementValue(doc, "NewParcels", "Note", Controller.data.get("Adres"));

            Controller.updateElementValueIntro(doc, "Utilization", "ByDoc", Controller.data.get("Util"));

            Controller.updateElementValueIntro(doc, "Category", "Category", Controller.data.get("Category"));
            Controller.updateElementValueIntro(doc, "Coord_System", "Cs_Id", Controller.data.get("ID"));
            Controller.updateElementValueIntro(doc, "Coord_System", "Name",  Controller.data.get("Coord"));
            Controller.updateElementValueIntro(doc, "AppliedFile", "Name", Controller.data.get("Img"));

            //определение длины массива имгс и в зависимость от этого добавление картинок
            if (Controller.data.size() > 0) {
                for (int j = 1; j <=  i; j++) {//добавить нужное количество новых
                    Controller.addElement(doc, "ParcelSchema_In_Block", "AppliedFile", "Name",  Controller.data.get("newImg"+j));
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
            Controller.save(doc,form1);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
