package response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsReadResponse {
    private int id;
    private String title;
    private String content;
    private String author;
    private LocalDate publicationDate;
    private String imagePath;
    private int categoryId;
}
