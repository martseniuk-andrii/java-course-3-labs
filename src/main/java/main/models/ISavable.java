package main.models;

import java.sql.Connection;
import java.sql.SQLException;

public interface ISavable<T>{
    public T save(Connection conn) throws SQLException;
}
