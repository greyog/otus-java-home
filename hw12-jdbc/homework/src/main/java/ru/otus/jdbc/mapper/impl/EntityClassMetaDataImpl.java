package ru.otus.jdbc.mapper.impl;

import ru.otus.crm.annotation.Id;
import ru.otus.jdbc.mapper.EntityClassMetaData;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {

    private final Class<T> clientClass;

    public EntityClassMetaDataImpl(Class<T> clientClass) {
        this.clientClass = clientClass;
    }

    @Override
    public String getName() {
        return clientClass.getName();
    }

    @Override
    public Constructor<T> getConstructor() {
        try {
            return clientClass.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Field getIdField() {
        return Arrays.stream(clientClass.getFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public List<Field> getAllFields() {
        return Arrays.stream(clientClass.getFields())
                .toList();
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return Arrays.stream(clientClass.getFields())
                .filter(field -> !field.isAnnotationPresent(Id.class))
                .toList();
    }
}
