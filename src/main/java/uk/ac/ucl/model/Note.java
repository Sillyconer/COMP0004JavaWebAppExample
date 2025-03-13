package uk.ac.ucl.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Note {
    private String id;
    private String title;
    private String content;
    private LocalDateTime dateCreated;
    private List<String> tags;
    private List<String> attachments;

    public Note(String title, String content) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.content = content;
        this.dateCreated = LocalDateTime.now();
        this.tags = new ArrayList<>();
        this.attachments = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    // Category Methods
    public List<String> getCategories() {
        return tags;
    }

    public void addTag(String tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

    // Attachment Methods
    public List<String> getAttachments() {
        return attachments;
    }

    public void addAttachment(String id) {
        attachments.add(id);
    }

    public void removeAttachment(String id) {
        attachments.remove(id);
    }

    public void saveNote(){
        try {
            WriteFile data = new WriteFile("data/Notes.json");
            data.writeToFile(content);
            WriteFile data2 = new WriteFile("data/Notes.json", true);
            data2.writeToFile(id + "," + title + "," + dateCreated + "," + tags + "," + attachments);
        }
        catch (Exception e) {
            System.out.println("Error writing to file");
        }
    }

    @Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", dateCreated=" + dateCreated +
                ", tags=" + tags +
                ", attachments=" + attachments +
                '}';
    }
}
