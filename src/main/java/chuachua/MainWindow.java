package chuachua;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class MainWindow {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    // Adding a backend field and setter
    private ChuaChua chuaChua;

    // Load images from resources/images
    private final Image userImage = new Image(getClass()
            .getResource("/images/IMG_2051.png").toExternalForm());
    private final Image chuaImage = new Image(getClass()
            .getResource("/images/IMG_2187.png").toExternalForm());

    public void setChuaChua(ChuaChua chuaChua) {
        this.chuaChua = chuaChua;
    }

    @FXML
    private void initialize() {
        // Auto-scroll to the bottom when dialogContainer grows
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Both click and Enter trigger the same handler
        sendButton.setOnAction(event -> handleUserInput());
        userInput.setOnAction(event -> handleUserInput());
    }

    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (input.isEmpty()) {
            return;
        }

        String response = chuaChua.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChuaDialog(response, chuaImage)
        );

        userInput.clear();

        // exit application after "bye"
        if (input.equalsIgnoreCase("bye")) {
            Platform.exit();
        }
    }

}
