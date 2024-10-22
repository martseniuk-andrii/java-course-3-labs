package main.serializers.impl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import main.serializers.IEntitySerializer;

import java.io.File;
import java.io.IOException;

public class XmlEntitySerializer<T> implements IEntitySerializer<T> {

    private final XmlMapper xmlMapper = new XmlMapper();

    public XmlEntitySerializer(){
        xmlMapper.findAndRegisterModules();
    }

    @Override
    public void serialize(T entity, File file) throws IOException {
        xmlMapper.writeValue(file, entity);
    }

    @Override
    public T deserialize(File file, Class<T> clazz) throws IOException {
        return xmlMapper.readValue(file, clazz);
    }

    @Override
    public void serializeArray(T[] entities, File file) throws IOException {
        xmlMapper.writeValue(file, entities);
    }

    @Override
    public T[] deserializeArray(File file, Class<T[]> clazz) throws IOException {
        return xmlMapper.readValue(file, clazz);
    }
}
