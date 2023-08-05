package ru.otus.javaprojpql.entity.homework;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PHONES")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String number;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public Phone(String number) {
        setNumber(number);
    }

    public void setClient(Client client) {
        this.client = client;
        client.addPhone(this);
    }
}
