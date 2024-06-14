package by.itminsk.cyclingclubbackend.news.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class NewsDTO {

    private Long id;

    private String title;

    private String content;

    private Date creationDate;

    public NewsDTO(Long id, String title, String content, Date creationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
    }
}
