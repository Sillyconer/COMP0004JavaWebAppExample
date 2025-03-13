package uk.ac.ucl.model;

import java.io.File;
//import java.io.Reader;
//import java.io.FileReader;
import java.io.IOException;
//import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.apache.commons.csv.CSVFormat;
//import org.apache.commons.csv.CSVParser;
//import org.apache.commons.csv.CSVRecord;

public class Model {

  public List<String> getNoteNames() {
    List<Note> notes = readFile("data/Notes.json");
    List<String> noteNames = new ArrayList<>();
    for (Note note : notes) {
      noteNames.add(note.getTitle());
    }
    return noteNames;
  }

//  public List<Note> readFile(String fileName) {
//    List<Note> notes = new ArrayList<>();
//
//    try (Reader reader = new FileReader(fileName);
//         CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {
//      for (CSVRecord csvRecord : csvParser) {
//        String id = csvRecord.get("id");
//        String title = csvRecord.get("title");
//        String content = ""; // Assuming content is not stored in the CSV
//        LocalDateTime dateCreated = LocalDateTime.parse(csvRecord.get("dateCreated"));
//        List<String> tags = List.of(csvRecord.get("tags").split(","));
//        List<String> attachments = List.of(csvRecord.get("attachments").split(","));
//
//        Note note = new Note(title, content);
//        note.getCategories().addAll(tags);
//        note.getAttachments().addAll(attachments);
//
//        notes.add(note);
//      }
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//
//    return notes;
//  }

  public List<Note> readFile(String fileName) {
    List<Note> notes = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());

    try {
      File file = new File(fileName);
      if (file.exists()) {
        Note[] noteArray = objectMapper.readValue(file, Note[].class);
        for (Note note : noteArray) {
          notes.add(note);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return notes;
  }


  public List<String> searchFor(String keyword)
  {
    return List.of("Search keyword is: "+ keyword, "result1", "result2", "result3");
  }

  public void addNote(String title, String content) {
    Note note = new Note(title, content);
    note.saveNote();
  }



}