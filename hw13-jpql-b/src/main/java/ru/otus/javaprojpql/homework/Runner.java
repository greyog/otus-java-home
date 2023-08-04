package ru.otus.javaprojpql.homework;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ru.otus.javaprojpql.entity.homework.Address;
import ru.otus.javaprojpql.entity.homework.Client;
import ru.otus.javaprojpql.entity.homework.Phone;

import java.util.Set;

public class Runner {
    public static void main(String[] args) {
        clientsWithPhonesAndAddress();
    }

    private static void clientsWithPhonesAndAddress() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SingleUnit");
        EntityManager entityManager = emf.createEntityManager();

        var transaction = entityManager.getTransaction();
        transaction.begin();

        var phones = Set.of(new Phone("1234"),
                new Phone("2542435"),
                new Phone("3456"));
        entityManager.persist(phones);

        var address = new Address("abc");
        entityManager.persist(address);

        var client = new Client("my name is", address, phones);
        entityManager.persist(client);

        transaction.commit();
        entityManager.close();
    }
}
