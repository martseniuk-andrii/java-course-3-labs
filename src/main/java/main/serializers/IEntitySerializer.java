package main.serializers;

import java.io.File;
import java.io.IOException;

public interface IEntitySerializer<T> {
    void serialize(T entity, File file) throws IOException;
    T deserialize(File file, Class<T> clazz) throws IOException;
    void serializeArray(T[] entities, File file) throws IOException;
    T[] deserializeArray(File file, Class<T[]> clazz) throws IOException;
}