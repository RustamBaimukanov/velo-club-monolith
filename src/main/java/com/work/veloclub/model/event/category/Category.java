package com.work.veloclub.model.event.category;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EventRaceType type;

    @Column(name = "category_name")
    private String name;

    @Column(name = "property_numeric")
    private String numericProperty;

    @Column(name = "property_description")
    private String descriptionProperty;

}
