package az.news.presslab.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "news",schema = "press-lab")
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column()
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column()
    @CreationTimestamp
    private LocalDate publicationDate;

    @Column()
    private String author;

    @Column(name = "fkCategory")
    private int categoryId;

    private int views;

    private String imagePath;
}
