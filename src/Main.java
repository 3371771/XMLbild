
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {
    private Stage window;
    private Button buttonChoose;
    private String comboValue;

    public static void main (String [] args) {launch();}


    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("XMLbild");
        ComboBox<String> comboBox = new ComboBox<>();

        comboBox.getItems().addAll("Форма 1","Форма 2");
        comboBox.setPromptText("Форма №?");
        comboBox.setStyle("-fx-font: 15px \"Calibri\";");

        Label labelMain = new Label("Выбор формы:");
        labelMain.setFont(Font.font("Calibri", 17));

        buttonChoose = new Button("Выбрать");
        buttonChoose.setFont(Font.font("Calibri", 17));
        //в зависимости от выбора разные формы
        buttonChoose.setOnAction(e ->
                {
                comboValue = comboBox.getValue();
                switch (comboValue) {
                    case "Форма 1": Form.display();
                    break;
                  // case "Макет2": Form2.display();
                    //    break;
                    default:break;
                }
                });

        VBox layoutMain = new VBox(10);
        layoutMain.setPadding(new Insets(20));
        layoutMain.setAlignment(Pos.CENTER);
        layoutMain.getChildren().addAll(labelMain, comboBox, buttonChoose);

        Scene scene = new Scene(layoutMain, 250,200);
        primaryStage.getIcons().add(new Image("/res/mainIcon.jpg"));
        primaryStage.setResizable(false);
        window.setScene(scene);
        window.show();
    }
}
