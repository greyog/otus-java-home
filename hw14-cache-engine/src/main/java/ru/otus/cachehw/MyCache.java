package ru.otus.cachehw;


import java.util.*;

public class MyCache<K, V> implements HwCache<K, V> {
//Надо реализовать эти методы

    private final Map<K, V> map = new WeakHashMap<>();

    private final Set<HwListener<K, V>> listeners = new HashSet<>();

    @Override
    public void put(K key, V value) {
        map.put(key, value);
        listeners.forEach(listener -> listener.notify(key, value, "PUT"));
    }

    @Override
    public void remove(K key) {
        var removedValue = map.remove(key);
        if (removedValue != null) {
            listeners.forEach(listener -> listener.notify(key, removedValue, "REMOVE"));
        }
    }

    @Override
    public V get(K key) {
        var obtainedValue = map.get(key);
        listeners.forEach(listener -> listener.notify(key, obtainedValue, "GET"));
        return obtainedValue;
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        listeners.remove(listener);
    }
}
