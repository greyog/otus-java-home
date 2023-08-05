package ru.otus.javaprojpql.entity.homework;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ADDRESSES")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String Street;

    @OneToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public Address(String street) {
        setStreet(street);
    }

    public void setClient(Client client) {
        this.client = client;
        client.setAddress(this);
    }
}
