package by.bntu.info.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String path;
    private Long faculty_id;
    private String type;

    public News() {
    }

    public News(String title, String content, String path, Long faculty_id, String type) {
        this.title = title;
        this.content = content;
        this.path = path;
        this.faculty_id = faculty_id;
        this.type = type;
    }

    public News(String title, String content, String path, String type) {
        this.title = title;
        this.content = content;
        this.path = path;
        this.type = type;
    }

    public boolean validate() {
        return title.length() > 5 && content.length() > 20 && type!=null && !type.equals("");
    }
}
