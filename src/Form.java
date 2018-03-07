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
    private static TextField textName;
    private static TextField textNumber;
    private static TextField textDate;
    private static TextField textOSMUname;
    private static TextField textCadastr;
    private static TextField textArea;
    private static TextField textUnit;
    private static TextField textID1;
    private static TextField textPoint1X;
    private static TextField textPoint1Y;
    private static TextField textAdres;
    private static TextField textUtil;
    private static TextField textCategory;
    private static TextField textCoordSys;
    private static TextField textImg;
    private static GridPane mainGrid;
    private static GridPane mainGrid1;


//    public static ArrayList<String> imgs = new ArrayList<String>();
//    public static ArrayList<String> arrayX = new ArrayList<String>();
//    public static ArrayList<String> arrayY = new ArrayList<String>();
    private static int gridaddFeldNumber = 5;
    private static int grid1addRowNumber = 11;
    private static int numberPoitValue = 2;
    private static int clickCount = 0;

    private static TextField newImage;

    public static Stage form1;
    public static Scene sceneMain;

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
        labelAdres.setId("adres");
        Label labelUtil = new Label("Применение");
        Label labelCategory = new Label("Категория");
        Label labelCoordSys = new Label("Система координат");
        Label labelImg = new Label("Изображения");

        //инициализация полей ввода
        textName = new TextField();
        textNumber = new TextField();
        textDate = new TextField();
        textOSMUname = new TextField();
        textCadastr = new TextField();
        textArea = new TextField();
        textUnit = new TextField();
        textID1 = new TextField();
        textPoint1X = new TextField();
        textPoint1Y = new TextField();
        textAdres = new TextField();
        textUtil = new TextField();
        textCategory = new TextField();
        textCoordSys = new TextField();
        textImg = new TextField();
        textImg.setPromptText("Выберите картинку");
        textImg.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if ( clickCount == 0 || textImg.getLength() == 0) {
                try {
                    Controller.openFile(textImg,form1);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                clickCount++;
            }
        });

        //кнопка формирования XML
        Button cast = new Button("Создать XML");
        cast.setOnAction(e -> {
            if (textName.getText().isEmpty() || textNumber.getText().isEmpty() || textDate.getText().isEmpty() || textOSMUname.getText().isEmpty() ||
                    textCadastr.getText().isEmpty() || textArea.getText().isEmpty() || textUnit.getText().isEmpty() || textID1.getText().isEmpty() ||
                    textPoint1X.getText().isEmpty() || textPoint1Y.getText().isEmpty() || textAdres.getText().isEmpty() || textUtil.getText().isEmpty() ||
                    textCategory.getText().isEmpty() || textCoordSys.getText().isEmpty() || textImg.getText().isEmpty()) {
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
                //возможно это нужно будет перенести в Conroller.save
                gridaddFeldNumber = 5;
                numberPoitValue = 2;
                grid1addRowNumber = 11;
                clickCount = 0;
                Controller.arrayX.clear();
                Controller.arrayY.clear();
                Controller.imgs.clear();
            }
        });

        //кнопка добавления картинок
        Button addImg = new Button("+");
        addImg.setOnAction(e -> { test();
//            newImage = new TextField();
//            newImage.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
//                System.out.println(clickCount);
//                System.out.println(newImage.getLength());
//                 if ( clickCount == 0 || newImage.getLength() == 0) {
//                      try {
//                          Controller.openFile(newImage,form1);
//                      } catch (IOException e1) {
//                          e1.printStackTrace();
//                      }
//                      clickCount++;
//                 }
//            });
//                newImage.setPromptText("Выберите картинку");
//                gridaddFeldNumber++;
//                mainGrid1.add(newImage, 1, gridaddFeldNumber);
//
////                newImage.setOnKeyPressed(ke -> {
////                    if (ke.getCode().equals(KeyCode.ENTER) && newImage.getLength() != 0) {
////                        //проверка правильности ввода
////                        infoBox("Внимание!!", "Проверьте введенные данные", "Все правильно?");
////                        if (answere) {
////                            //добавленеи в массив текста из окна ввода
////                            imgs.add(newImage.getText());
////                            newImage.setDisable(true);
////                        }
////                    }
////                });
//            Controller.noFocusImg(newImage);
        });

        //кнопка добавления точки
        Button addPoint = new Button("+");
        addPoint.setOnAction(e -> {

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
            grid1addRowNumber++;

            mainGrid.add(newLabelPointX, 0, grid1addRowNumber);
            mainGrid.add(newText1, 1, grid1addRowNumber);

            mainGrid.add(newLabelPointY, 0, grid1addRowNumber + 1);
            mainGrid.add(newText2, 1, grid1addRowNumber + 1);
            //увеличиваем значенеи счетчика номра строки т.к. строки 2
            grid1addRowNumber++;
            numberPoitValue++;

            Controller.noFocusPoint(newText1,newText2);
//            newText2.setOnKeyPressed(ke -> {
//                if (ke.getCode().equals(KeyCode.ENTER) && newText1.getLength() != 0) {
//                    //уведомление, что картинка добавлена
//                    infoBox("Уведомление", "Проверьте введенные данные", "Все правильно?");
//                    if (answere) {
//                        //добавленеи в массив текста
//                        arrayX.add(newText1.getText());
//                        arrayY.add(newText2.getText());
//                        //выключение поля
//                        newText1.setDisable(true);
//                        newText2.setDisable(true);
//                    }
//                }
//            });
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
        mainGrid.add(textID1, 1, 8);

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
        sceneMain = new Scene(scrollPane);

        form1.setScene(sceneMain);
        form1.show();


        form1.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
                numberPoitValue = 2;
                grid1addRowNumber = 11;
                gridaddFeldNumber = 5;
                clickCount = 0;
                Controller.arrayX.clear();
                Controller.arrayY.clear();
                Controller.imgs.clear();
                form1.close();
            }
        });
    }

    private static void getStrings() throws IOException {
        String strings []= {textName.getText(),textNumber.getText(), textDate.getText(), textOSMUname.getText(), textCadastr.getText(), textArea.getText(),
                textUnit.getText(), textID1.getText(), textPoint1X.getText(), textPoint1Y.getText(), textAdres.getText(),
                textUtil.getText(), textCategory.getText(),textCoordSys.getText(),textImg.getText()};

        rewriteForm1(strings,Controller.imgs,Controller.arrayX,Controller.arrayY);
    }

    private static void rewriteForm1(String strings[], ArrayList<String> imgs, ArrayList<String> arrayX, ArrayList<String> arrayY) {
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

            Controller.save(doc);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private static void test () {

        Label test = (Label) sceneMain.lookup("#adres");
       test.setText("новы");
    }
}
