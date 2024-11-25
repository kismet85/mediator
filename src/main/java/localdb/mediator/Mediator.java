package localdb.mediator;


public interface Mediator {
    void sendMessage(String message, String sender, String recipient);
    void addClient(ChatClient client);

}