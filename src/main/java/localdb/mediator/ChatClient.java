package localdb.mediator;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatClient {
    private final String username;
    private final Mediator mediator;

    @FXML
    private TextArea chatArea;
    @FXML
    private TextField messageField;
    @FXML
    private ComboBox<String> recipientComboBox;

    public ChatClient(String username, Mediator mediator) {
        this.username = username;
        this.mediator = mediator;
        mediator.addClient(this);
    }

    public String getUsername() {
        return username;
    }

    public void display() {
        Stage stage = new Stage();
        stage.setTitle("Chat " + username);

        chatArea = new TextArea();
        chatArea.setEditable(false);

        messageField = new TextField();
        messageField.setPromptText("Enter your message");

        recipientComboBox = new ComboBox<>();
        recipientComboBox.setPromptText("Select recipient");

        Button sendButton = new Button("Send");
        sendButton.setOnAction(e -> sendMessage());

        VBox layout = new VBox(10, chatArea, recipientComboBox, messageField, sendButton);
        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.show();

        updateRecipients();
    }

    public void sendMessage() {
        String message = messageField.getText();
        String recipient = recipientComboBox.getValue();

        if (!message.isEmpty() && recipient != null) {
            chatArea.appendText("To " + recipient + ": " + message + "\n");
            mediator.sendMessage(message, username, recipient);
            messageField.clear();
        }
    }


    public void receiveMessage(String sender, String message) {
        Platform.runLater(() -> chatArea.appendText(sender + ": " + message + "\n"));
    }

    public void updateRecipients() {
        Platform.runLater(() -> {
            recipientComboBox.getItems().clear();
            for (ChatClient client : ((ChatRoomMediator) mediator).getClients().values()) {
                if (!client.getUsername().equals(username)) {
                    recipientComboBox.getItems().add(client.getUsername());
                }
            }
        });
    }
}
