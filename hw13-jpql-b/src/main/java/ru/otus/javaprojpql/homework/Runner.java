package ru.otus.javaprojpql.homework;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ru.otus.javaprojpql.entity.homework.Address;
import ru.otus.javaprojpql.entity.homework.Client;
import ru.otus.javaprojpql.entity.homework.Phone;

import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

public class Runner {

    private static long recentClientId;

    public static void main(String[] args) {
        createStructureAndData();
        readFromDb();
    }

    private static void createStructureAndData() {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("SingleUnit")) {
            try (EntityManager entityManager = emf.createEntityManager()) {

                var transaction = entityManager.getTransaction();
                transaction.begin();

                var phones = new HashSet<Phone>();
                for (int i = 0; i <= new Random().nextInt(5); i++) {
                    phones.add(new Phone("+" + System.nanoTime() % 10000000000L));
                }

                var address = new Address("i live at " + UUID.randomUUID().toString().split("-")[0]);

                var client = new Client("my name is " + UUID.randomUUID().toString().split("-")[1],
                        address,
                        phones);
                System.out.println("Original client: " + client);

                address.setClient(client);
                phones.forEach(phone -> phone.setClient(client));

                entityManager.persist(client);

                transaction.commit();

                System.out.println("Persisted client: " + client);
                recentClientId = client.getId();
                System.out.println("Recent client Id: " + recentClientId);

            }
        }
    }

    private static void readFromDb() {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("SingleUnit")) {
            try (EntityManager entityManager = emf.createEntityManager()) {

                var clientFromDb = entityManager.find(Client.class, recentClientId);
                System.out.println("Client from DB: " + clientFromDb);

            }
        }
    }


}
