package entity;

public class ChatMessage {
    private final String role;
    private final String message;

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
