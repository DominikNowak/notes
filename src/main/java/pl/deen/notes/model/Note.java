package pl.deen.notes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private Timestamp timestamp;
    private String remoteAddr;

    public Note(String title, String content, Timestamp timestamp, String remoteAddr) {
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
        this.remoteAddr = remoteAddr;
    }
}
