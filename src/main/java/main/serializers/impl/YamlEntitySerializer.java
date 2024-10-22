package main.serializers.impl;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import main.serializers.IEntitySerializer;

import java.io.File;
import java.io.IOException;

public class YamlEntitySerializer<T> implements IEntitySerializer<T> {

    private final YAMLMapper yamlMapper = new YAMLMapper();

    public YamlEntitySerializer(){
        yamlMapper.findAndRegisterModules();
    }

    @Override
    public void serialize(T entity, File file) throws IOException {
        yamlMapper.writeValue(file, entity);
    }

    @Override
    public T deserialize(File file, Class<T> clazz) throws IOException {
        return yamlMapper.readValue(file, clazz);
    }

    @Override
    public void serializeArray(T[] entities, File file) throws IOException {
        yamlMapper.writeValue(file, entities);
    }

    @Override
    public T[] deserializeArray(File file, Class<T[]> clazz) throws IOException {
        return yamlMapper.readValue(file, clazz);
    }
}