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
        createStructureAndData();
        readFromDb();
    }

    private static void createStructureAndData() {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("SingleUnit")) {
            try (EntityManager entityManager = emf.createEntityManager()) {

                var transaction = entityManager.getTransaction();
                transaction.begin();

                var phones = Set.of(new Phone("1234"),
                        new Phone("2542435"),
                        new Phone("3456"));

                var address = new Address("abc");

                var client = new Client("my name is", address, phones);
                entityManager.persist(client);
                System.out.println("Original client: " + client);

                transaction.commit();

            }
        }
    }

    private static void readFromDb() {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("SingleUnit")) {
            try (EntityManager entityManager = emf.createEntityManager()) {

                var clientFromDb = entityManager.find(Client.class, 1);
                System.out.println("Client from DB: " + clientFromDb);

            }
        }
    }
}
