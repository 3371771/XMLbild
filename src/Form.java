import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;



class Form {

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
    private static TextField textImg;

    static void display() {
        Stage form1 = new Stage();

        form1.setTitle("Форма 1");

       // form1.getIcons().add(new Image("info_.jpg"));
        form1.setHeight(650);
        form1.setWidth(380);

        //названия полей
        Label labelName = new Label("Название");
        Label labelNumber = new Label("Номер");
        Label labelDate = new Label("Дата");
        Label labelOSMUname = new Label("Название ОСМУ");
        Label labelCadastr = new Label("Кадастровый номер");
        Label labelArea = new Label("Area");
        Label labelUnit = new Label("Unit");

        //эти данные в 2-х местах
        Label labelID1 = new Label("ID1");

        Label labelPoint1X = new Label("Точка 1 X");
        Label labelPoint1Y = new Label("Точка 1 Y");
        //может понадобиться добавленеи полей для точек
        Label labelAdres = new Label("Адрес");
        Label labelUtil = new Label("Примененеие");
        Label labelCategory = new Label("Категория");
        //сделать так, чтобы можно было добавить несколько
        Label labelImg = new Label("Изображения");

        //сами поля
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
        textImg = new TextField();


        Button cast = new Button("Создать XML");
        cast.setOnAction(e -> getText());

        Button addImg = new Button("+");

        GridPane mainGrid = new GridPane();
        mainGrid.setAlignment(Pos.TOP_CENTER);
        mainGrid.setHgap(10);
        mainGrid.setVgap(10);
        mainGrid.setPadding(new Insets(25));

        Text title = new Text("Форма 1");
        title.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
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

        mainGrid.add(labelAdres, 0, 11);
        mainGrid.add(textAdres, 1, 11);

        mainGrid.add(labelUtil, 0, 12);
        mainGrid.add(textUtil, 1, 12);

        mainGrid.add(labelCategory, 0, 13);
        mainGrid.add(textCategory, 1, 13);

        mainGrid.add(labelImg, 0, 14);
        mainGrid.add(textImg, 1, 14);

        mainGrid.add(cast, 1,15);
        mainGrid.add(addImg, 3,14);



        Scene sceneAbout = new Scene(mainGrid);

        form1.setScene(sceneAbout);
        form1.show();
    }

    private static void getText() {
        //тут постичтать количесво картинок и в зависимости от этого запустить свой билд
        Contrller.bildForm1(textName.getText(),textNumber.getText(), textDate.getText(), textOSMUname.getText(), textCadastr.getText(), textArea.getText(),
                textUnit.getText(), textID1.getText(), textPoint1X.getText(), textPoint1Y.getText(), textAdres.getText(),
                textUtil.getText(), textCategory.getText(), textImg.getText());
        Contrller.rewriteForm1();
    }

    private static void newFeld () {
        //принимать значение имя поля и создавать такое же, но ниже (что было +1)
        //нужно получить значение имени поля, номер строки и вид заполняемого поля
        //как потом передать несколько знчений в билд?

    }
}
