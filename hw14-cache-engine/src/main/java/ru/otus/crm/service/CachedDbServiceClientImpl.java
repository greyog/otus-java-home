package ru.otus.crm.service;

import ru.otus.cachehw.HwCache;
import ru.otus.cachehw.MyCache;
import ru.otus.crm.model.Client;

import java.util.List;
import java.util.Optional;

public class CachedDbServiceClientImpl implements DBServiceClient{

    private final HwCache<Long, Client> cache = new MyCache<>();
    private final DBServiceClient noCacheClient;

    public CachedDbServiceClientImpl(DBServiceClient noCacheClient) {
        this.noCacheClient = noCacheClient;
    }

    @Override
    public Client saveClient(Client client) {
        var savedClient = noCacheClient.saveClient(client);
        cache.put(savedClient.getId(), savedClient);
        return savedClient;
    }

    @Override
    public Optional<Client> getClient(long id) {
        var cachedClient = cache.get(id);
        if (cachedClient == null) {
            return noCacheClient.getClient(id);
        }
        return Optional.of(cachedClient);
    }

    @Override
    public List<Client> findAll() {
        var allClients = noCacheClient.findAll();
        allClients.forEach(client -> cache.put(client.getId(), client));
        return allClients;
    }
}
