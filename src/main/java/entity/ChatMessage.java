package entity;

public class ChatMessage {
    final private String role;
    final private String message;

    public ChatMessage(String role, String message) {
        this.role = role;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getRole() {
        return role;
    }
}
