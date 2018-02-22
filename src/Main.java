import com.sun.javafx.css.Style;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;



public class Main extends Application {
    Stage window;
    Scene scene;
    Button buttonChoose;
    String comboValue;

    public static void main (String [] args) {launch();}


    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        window.setTitle("XMLbild");
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Макет1","Макет2");
        comboBox.setPromptText("Выберите макет");


        Label labelMain = new Label("Выберите вот тут");
        labelMain.setFont(Font.font("Times New Roman", 17));

        buttonChoose = new Button("Выбрать");
        buttonChoose.setFont(Font.font("Times New Roman", 17));
        //в зависимости от выбора разные формы
        buttonChoose.setOnAction(e ->
                {
                comboValue = comboBox.getValue();
                switch (comboValue) {
                    case "Макет1": Form.display();
                    break;
                    case "Макет2": Form2.display();
                        break;
                    default:break;
                }
                });

        VBox layoutMain = new VBox(10);
        layoutMain.setPadding(new Insets(20));
        layoutMain.setAlignment(Pos.CENTER);
        layoutMain.getChildren().addAll(labelMain, comboBox, buttonChoose);

        Scene scene = new Scene(layoutMain, 350,200);
        window.setScene(scene);
        window.show();
    }
}
