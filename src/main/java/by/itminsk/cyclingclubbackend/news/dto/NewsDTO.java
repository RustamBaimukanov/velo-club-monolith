package by.itminsk.cyclingclubbackend.news.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO {

    private Long id;

    private String title;

    private String content;

    private Date creationDate;
}
