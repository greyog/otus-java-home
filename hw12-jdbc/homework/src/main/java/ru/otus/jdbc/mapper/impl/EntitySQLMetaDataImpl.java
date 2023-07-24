package ru.otus.jdbc.mapper.impl;

import ru.otus.jdbc.mapper.EntityClassMetaData;
import ru.otus.jdbc.mapper.EntitySQLMetaData;

import java.lang.reflect.Field;
import java.util.stream.Collectors;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData {

    private final EntityClassMetaData<T> entityClassMetaDataClient;

    public EntitySQLMetaDataImpl(EntityClassMetaData<T> entityClassMetaDataClient) {
        this.entityClassMetaDataClient = entityClassMetaDataClient;
    }

    private String getTableName() {
        return entityClassMetaDataClient.getName();
    }

    private String getIdName() {
        return entityClassMetaDataClient.getIdField().getName();
    }

    @Override
    public String getSelectAllSql() {
        return "select * from %s".formatted(getTableName());
    }

    @Override
    public String getSelectByIdSql() {
        return "select * from %s where %s = ?".formatted(getTableName(), getIdName());
    }

    @Override
    public String getInsertSql() {
        return "insert into %s( %s ) values (?)".formatted(getTableName(),
                entityClassMetaDataClient.getAllFields().stream()
                        .map(Field::getName)
                        .collect(Collectors.joining(", ")));
    }

    @Override
    public String getUpdateSql() {
        return "update %s set %s where %s = ?".formatted(getTableName(),
                entityClassMetaDataClient.getFieldsWithoutId().stream()
                        .map(Field::getName)
                        .map(s -> s + " = ?")
                        .collect(Collectors.joining(", ")),
                getIdName());
    }
}
