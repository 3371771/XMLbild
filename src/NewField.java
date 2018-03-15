import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

class NewField extends TextField{

        private String text;

    NewField(String text) { this.text = text; }

//потом можно будет сюда передавать массив, куда записывать

    void adToArray(HashMap<String, String> dataList, String key, String value) {
       if (this.text.length() != 0) {
            System.out.println(this.getLength());
            this.focusedProperty().addListener((obs, oldValue, newValue) -> {
                if (newValue) { }
                else {
                    dataList.put(key,this.getText());
                    System.out.println(Controller.data);
                }
            });
        }
    }

    void onClick(Stage formNumber, HashMap<String, String> dataList, String key) {
                 if ( this.getLength() == 0) {
                      try {
                          Controller.openFile(this,formNumber);
                      } catch (IOException e1) {
                          e1.printStackTrace();
                      }
                      adToArray(dataList,key, this.getText());
                 }
    }
}

