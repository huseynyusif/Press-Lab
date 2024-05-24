package response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsCardResponse {
    private Integer id;
    private String title;
    private LocalDate publicationDate;
    private int views;
    private String imagePath;
}
