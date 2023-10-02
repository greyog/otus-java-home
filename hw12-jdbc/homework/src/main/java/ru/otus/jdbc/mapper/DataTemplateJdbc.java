package ru.otus.jdbc.mapper;

import ru.otus.core.repository.DataTemplate;
import ru.otus.core.repository.DataTemplateException;
import ru.otus.core.repository.executor.DbExecutor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;
    private final EntityClassMetaData<T> entityClassMetaData;

    public DataTemplateJdbc(DbExecutor dbExecutor,
                            EntitySQLMetaData entitySQLMetaData,
                            EntityClassMetaData<T> entityClassMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        return dbExecutor.executeSelect(connection,
                entitySQLMetaData.getSelectByIdSql(),
                List.of(id),
                rs -> {
                    try {
                        if (rs.next()) {
                            List<Object> fieldsValues = new ArrayList<>();
                            var fieldTypes = new ArrayList<Class<?>>();
                            for (Field field : entityClassMetaData.getAllFields()) {
                                fieldsValues.add(rs.getObject(field.getName(), field.getType()));
                                fieldTypes.add(field.getType());
                            }
                            return entityClassMetaData
                                    .getConstructor(fieldTypes.toArray(new Class[0]))
                                    .newInstance(fieldsValues.toArray());
                        }
                        return null;
                    } catch (SQLException e) {
                        throw new DataTemplateException(e);
                    } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
    }

    @Override
    public List<T> findAll(Connection connection) {
        return dbExecutor.executeSelect(connection,
                entitySQLMetaData.getSelectAllSql(),
                Collections.emptyList(),
                rs -> {
                    List<T> resultList = new ArrayList<>();
                    try {
                        while (rs.next()) {
                            var fieldsValues = new ArrayList<>();
                            var fieldTypes = new ArrayList<Class<?>>();
                            for (Field field : entityClassMetaData.getAllFields()) {
                                fieldsValues.add(rs.getObject(field.getName(), field.getType()));
                                fieldTypes.add(field.getType());
                            }
                            var instance = entityClassMetaData
                                    .getConstructor(fieldTypes.toArray(new Class[0]))
                                    .newInstance(fieldsValues.toArray());
                            resultList.add(instance);
                        }
                    } catch (SQLException e) {
                        throw new DataTemplateException(e);
                    } catch (InvocationTargetException | IllegalAccessException | InstantiationException e) {
                        e.printStackTrace();
                    }
                    return resultList;
                }).orElseThrow(() -> new RuntimeException("Unexpected error"));
    }

    @Override
    public long insert(Connection connection, T client) {
        var params = new ArrayList<>();
        for (Field field : entityClassMetaData.getFieldsWithoutId()) {
            Object value = null;
            try {
                field.setAccessible(true);
                value = field.get(client);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                field.setAccessible(false);
            }
            params.add(value);
        }
        var sql = entitySQLMetaData.getInsertSql();
        System.out.println(sql + " | params: " + params);
        try {
            return dbExecutor.executeStatement(connection,
                    sql,
                    params);
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }

    @Override
    public void update(Connection connection, T client) {
        var params = new ArrayList<>();
        for (Field field : entityClassMetaData.getAllFields()) {
            Object value = null;
            try {
                field.setAccessible(true);
                value = field.get(client);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                field.setAccessible(false);
            }
            params.add(value);
        }
        try {
            dbExecutor.executeStatement(connection,
                    entitySQLMetaData.getUpdateSql(),
                    params);
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }
}
