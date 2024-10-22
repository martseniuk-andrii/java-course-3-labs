package main.serializers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.serializers.IEntitySerializer;

import java.io.File;
import java.io.IOException;

public class JsonEntitySerializer<T> implements IEntitySerializer<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonEntitySerializer(){
        objectMapper.findAndRegisterModules();
    }

    @Override
    public void serialize(T entity, File file) throws IOException {
        objectMapper.writeValue(file, entity);
    }

    @Override
    public T deserialize(File file, Class<T> clazz) throws IOException {
        return objectMapper.readValue(file, clazz);
    }

    @Override
    public void serializeArray(T[] entities, File file) throws IOException {
        objectMapper.writeValue(file, entities);
    }

    @Override
    public T[] deserializeArray(File file, Class<T[]> clazz) throws IOException {
        return objectMapper.readValue(file, clazz);
    }
}