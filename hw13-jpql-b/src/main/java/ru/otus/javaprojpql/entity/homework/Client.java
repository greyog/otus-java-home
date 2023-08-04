package ru.otus.javaprojpql.entity.homework;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "CLIENTS")
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    private UUID id;

    @Column
    private String name;

    @OneToOne
    private Address address;

    @OneToMany
    private Set<Phone> phones;

    public Client(String name, Address address, Set<Phone> phones) {
        id = UUID.randomUUID();
        setName(name);
        setAddress(address);
        setPhones(phones);
    }
}
