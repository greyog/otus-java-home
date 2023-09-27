package ru.otus.demo;

import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.core.repository.DataTemplateHibernate;
import ru.otus.core.repository.HibernateUtils;
import ru.otus.core.sessionmanager.TransactionManagerHibernate;
import ru.otus.crm.dbmigrations.MigrationsExecutorFlyway;
import ru.otus.crm.model.Address;
import ru.otus.crm.model.Client;
import ru.otus.crm.model.Phone;
import ru.otus.crm.service.DbServiceClientImpl;

import java.util.ArrayList;
import java.util.UUID;

public class DbServiceDemo {

    private static final Logger log = LoggerFactory.getLogger(DbServiceDemo.class);

    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    private static final int CLIENT_COUNT = 1000;
    private static final int CLIENT_PHONES = 10;

    public static void main(String[] args) {
        var configuration = new Configuration().configure(HIBERNATE_CFG_FILE);

        var dbUrl = configuration.getProperty("hibernate.connection.url");
        var dbUserName = configuration.getProperty("hibernate.connection.username");
        var dbPassword = configuration.getProperty("hibernate.connection.password");

        new MigrationsExecutorFlyway(dbUrl, dbUserName, dbPassword).executeMigrations();

        var sessionFactory = HibernateUtils.buildSessionFactory(configuration,
                Client.class,
                Address.class,
                Phone.class
        );

        var transactionManager = new TransactionManagerHibernate(sessionFactory);
///
        var clientTemplate = new DataTemplateHibernate<>(Client.class);
///
        var dbServiceClient = new DbServiceClientImpl(transactionManager, clientTemplate);

        var clientIds = new ArrayList<Long>();

        var startTime = System.currentTimeMillis();
        for (int i = 0; i < CLIENT_COUNT; i++) {
            clientIds.add(dbServiceClient.saveClient(generateRandomClient()).getId());
        }
        log.info("clients persisted:{}", clientIds.size());

        var clientsSelected = dbServiceClient.findAll();
        log.info("clients Selected:{}", clientsSelected.size());
///
        clientsSelected.forEach(client -> {
            client.setName("New name is " + UUID.randomUUID() );
            dbServiceClient.saveClient(client);
        });

        var endTime = System.currentTimeMillis();

        log.info("Time elapsed: {}", endTime - startTime);
    }

    private static Client generateRandomClient() {
        var phones = new ArrayList<Phone>();
        for (int i = 0; i <= CLIENT_PHONES; i++) {
            phones.add(new Phone(UUID.randomUUID().toString()));
        }
        return new Client(null, "My name " + UUID.randomUUID(),
                new Address("Street " + UUID.randomUUID()),
                phones);
    }
}
