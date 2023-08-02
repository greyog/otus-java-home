package ru.otus.javaprojpql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "CATEGORIES")
@NamedEntityGraph(
        name = "category-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("courseSet")
        }
)
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    private UUID id;

    @Column(name = "NAME", length = 30, nullable = false)
    private String name;

    //    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "category")
//    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Course> courseSet;

    public Category(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }
}
