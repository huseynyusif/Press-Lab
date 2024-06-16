package az.news.presslab.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsCriteria {
    private String title;
    private String categoryName;
    private String content;
}
