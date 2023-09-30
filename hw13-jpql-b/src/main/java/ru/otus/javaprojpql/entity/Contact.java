package ru.otus.javaprojpql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.otus.javaprojpql.ContactType;

import java.util.UUID;

@Entity
@Table(name = "CONTACTS",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"TYPE", "STUDENT_ID"})})
@NoArgsConstructor
@Getter
@Setter
public class Contact {

    @Id
    private UUID id;

    @Column(name = "VALUE", length = 70, nullable = false)
    private String value;

    @Enumerated
    @Column(name = "TYPE")
    private ContactType contactType;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID", nullable = false)
    private Student student;
}
