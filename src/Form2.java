import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Form2 {

    static void display() {
        Stage form2 = new Stage();

        form2.setTitle("Форма 2");

        // form1.getIcons().add(new Image("info_.jpg"));
        form2.setHeight(190);
        form2.setWidth(380);

        Label label1 = new javafx.scene.control.Label("Тут одно2");
        Label label2 = new Label("Тут другое2");

        TextField text1 = new TextField();
        TextField text2 = new TextField();



        VBox vForm1Text = new VBox(15);
        vForm1Text.setPadding(new Insets(13));
        vForm1Text.getChildren().addAll(label1, label2);
        vForm1Text.setAlignment(Pos.CENTER);

        VBox vForm1Enter = new VBox(15);
        vForm1Enter.setPadding(new Insets(13));
        vForm1Enter.getChildren().addAll(text1,text2);
        vForm1Enter.setAlignment(Pos.CENTER);

        HBox hForm1 = new HBox(15);
        hForm1.setPadding(new Insets(13));
        hForm1.getChildren().addAll(vForm1Text, vForm1Enter);
        hForm1.setAlignment(Pos.CENTER);

        Scene sceneAbout = new Scene(hForm1);

        form2.setScene(sceneAbout);
        form2.show();

    }
}
