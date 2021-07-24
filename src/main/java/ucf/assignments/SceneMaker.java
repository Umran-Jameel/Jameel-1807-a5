package ucf.assignments;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SceneMaker {
    public Stage makeScene(int width, int height, Node... elements) {
        Stage stage = new Stage();

        // Vbox with spacing 10, padding 20 all around
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.getChildren().addAll(elements);
        vBox.setAlignment(Pos.TOP_CENTER);

        // set the scene
        Scene scene = new Scene(vBox, width, height);
        stage.setScene(scene);

        return stage; // return the stage for use
    }
}
