package by.itminsk.cyclingclubbackend.news_metainfo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "news_metainfo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsMetaInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "metainfo", columnDefinition = "bytea")
    private byte[] metaInfo;

    @Column(name = "format")
    private String format;
}