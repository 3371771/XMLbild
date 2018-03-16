import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

class NewField extends TextField{

    private String key;

    NewField(String key) {
        this.key = key;
        this.adToArray(Controller.data);
    }

    private void adToArray(HashMap<String, String> dataList) {
        this.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) { }
            else {
                if (this.getLength() != 0) {
                    dataList.put(this.key,this.getText());
                    System.out.println(Controller.data);
                }
                    else Controller.data.remove(this.key);
                    System.out.println(Controller.data);
                }
            });
        }

    void onClick(Stage formNumber, HashMap<String, String> dataList) {
        if ( this.getLength() == 0) {
            try {
                Controller.openFile(this,formNumber);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            adToArray(dataList);
        }
    }
}

