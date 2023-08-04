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
@Table(name = "ADDRESSES")
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    private UUID id;

    @Column
    private String Street;

    public Address(String street) {
        id = UUID.randomUUID();
        setStreet(street);
    }
}
