package chuachua;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load DialogBox.fxml", e);
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the image is on the right and text is on the left.
     */
    private void flip() {
        setAlignment(Pos.CENTER_RIGHT);
        // Reverse the children order: [ImageView, Label] -> [Label, ImageView]
        getChildren().setAll(dialog, displayPicture);

        // Change bubble color for user (optional but nice)
        dialog.setStyle("-fx-background-color: #dfa2f5; -fx-background-radius: 12; -fx-padding: 8;");
    }

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    public static DialogBox getChuaDialog(String text, Image img) {
        return new DialogBox(text, img);
    }
}
