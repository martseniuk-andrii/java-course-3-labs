package main;


import main.models.Car;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:sample.db");


            if (conn == null) {
                System.out.println("DB connection failed");
                System.exit(0);
            }

            // init database
            {
                String sql = new String(Files.readAllBytes(Paths.get("./database.sql")));
                String[] sqlStatements = sql.split(";");

                try (Statement stmt = conn.createStatement()) {
                    for (String command : sqlStatements) {
                        // Удаляем лишние пробелы и выполняем команду, если она не пустая
                        if (!command.trim().isEmpty()) {
                            stmt.execute(command.trim());
                        }
                    }
                    System.out.println("Database tables created successfully.");
                }
            }

            {
                Random rand = new Random();
                Car car = Car.Builder.builder()
                        .setYearOfManufacture(1901)
                        .setLicensePlate("LP:"+rand.nextInt(100000))
                        .setMake("Ukraine")
                        .setVin("test:"+rand.nextInt(100000))
                        .build();

                car.save(conn);
                System.out.println("Car created successfully. "+ car);
                car.setMake("Anime 227"+rand.nextInt(100000));
                car.save(conn);
                System.out.println("Car updated successfully. "+ car);
            }

            {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM car");

                ArrayList<Car> cars = new ArrayList<>();
                while (rs.next()) {
                    cars.add(new Car(rs));
                }
                System.out.println(cars);

                stmt.close();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if (conn != null){
                conn.close();
            }
        }

    }
}