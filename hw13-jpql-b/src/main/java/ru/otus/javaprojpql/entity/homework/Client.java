package ru.otus.javaprojpql.entity.homework;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CLIENTS")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Phone> phones = new HashSet<>();

    public Client(String name, Address address, Set<Phone> phones) {
        setName(name);
        setAddress(address);
        setPhones(phones);
    }

    public void addPhone(Phone phone) {
        phones.add(phone);
        if (phone.getClient() == null) phone.setClient(this);
        else if (!phone.getClient().equals(this)) phone.setClient(this);
//        else if (!phone.getClient().id.equals(id)) phone.setClient(this);
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = new HashSet<>();
        phones.forEach(this::addPhone);
    }

    public void setAddress(Address address) {
        this.address = address;
        if (address.getClient() == null) address.setClient(this);
        else if (!address.getClient().equals(this)) address.setClient(this);
    }
}
