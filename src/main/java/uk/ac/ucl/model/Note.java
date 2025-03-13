package uk.ac.ucl.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
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

    @JsonCreator
    public Note(@JsonProperty("id") String id,
                @JsonProperty("title") String title,
                @JsonProperty("content") String content,
                @JsonProperty("dateCreated") LocalDateTime dateCreated,
                @JsonProperty("tags") List<String> tags,
                @JsonProperty("attachments") List<String> attachments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateCreated = dateCreated;
        this.tags = tags != null ? new ArrayList<>(tags) : new ArrayList<>();
        this.attachments = attachments != null ? new ArrayList<>(attachments) : new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public void saveNote() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            File file = new File("data/Notes.json");
            List<Note> notes;

            if (file.exists()) {
                Note[] existingNotes = objectMapper.readValue(file, Note[].class);
                notes = new ArrayList<>(List.of(existingNotes));
            } else {
                notes = new ArrayList<>();
            }

            notes.add(this);
            objectMapper.writeValue(file, notes);
        } catch (IOException e) {
            System.out.println("Error writing JSON file: " + e.getMessage());
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