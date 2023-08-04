package ru.otus.javaprojpql.entity.homework;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "PHONES")
@NoArgsConstructor
@Getter
@Setter
public class Phone {

    @Id
    private UUID id;

    @Column
    private String number;

    public Phone(String number) {
        id = UUID.randomUUID();
        setNumber(number);
    }
}
