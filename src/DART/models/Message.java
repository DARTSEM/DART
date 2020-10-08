package DART.models;

import java.util.UUID;

public class Message {
    private UUID senderID;
    private boolean status;
    private String content;
    private String title;

    public Message(UUID senderID, String content, String title) {
        this.senderID = senderID;
        this.status = false;
        this.content = content;
        this.title = title;
    }

    public UUID getSenderID() {
        return senderID;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "ID:" + senderID + "\nTitle: " + title + "\n\n" + content;
    }

}