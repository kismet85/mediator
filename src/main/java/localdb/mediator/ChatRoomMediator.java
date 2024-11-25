package localdb.mediator;

import java.util.HashMap;
import java.util.Map;

public class ChatRoomMediator implements Mediator {
    private final Map<String, ChatClient> clients = new HashMap<>();

    @Override
    public void sendMessage(String message, String sender, String recipient) {
        ChatClient recipientClient = clients.get(recipient);
        if (recipientClient != null) {
            recipientClient.receiveMessage(sender, message);
        } else {
            ChatClient senderClient = clients.get(sender);
            senderClient.receiveMessage("System", "Recipient not found: " + recipient);
        }
    }

    @Override
    public void addClient(ChatClient client) {
        clients.put(client.getUsername(), client);

        // Notify all clients to update their recipient dropdown
        for (ChatClient c : clients.values()) {
            c.updateRecipients();
        }
    }

    public Map<String, ChatClient> getClients() {
        return clients;
    }
}
