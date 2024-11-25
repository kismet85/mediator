package localdb.mediator;

import javafx.application.Application;
import javafx.stage.Stage;

public class ChatApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        Mediator mediator = new ChatRoomMediator();

        ChatClient client1 = new ChatClient("Spindle", mediator);
        ChatClient client2 = new ChatClient("Makkaralaatikko", mediator);
        ChatClient client3 = new ChatClient("Goldenboy", mediator);

        client1.display();
        client2.display();
        client3.display();
    }
    public static void main(String[] args) {
        launch();
    }
}