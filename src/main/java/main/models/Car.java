package main.models;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.constraints.*;
import java.sql.*;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a car available for rent.
 */
public class Car implements Comparable<Car>, ISavable<Car> {
    private Integer id;

    @NotNull
    private String make;

    @NotNull
    private String vin;

    @NotNull
    private String licensePlate;

    @NotNull
    @Min(value = 1900)
    private int yearOfManufacture;

    @Min(value = 0)
    private int mileage;


    public Car save(Connection conn) throws SQLException {
        if (this.id == null) {
            try (PreparedStatement ps = conn.prepareStatement("insert into car (make, vin, license_plate, year_of_manufacture, mileage) VALUES (?, ?, ?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS)) {
                System.out.println(make + vin + licensePlate + yearOfManufacture + mileage);

                ps.setString(1, make);
                ps.setString(2, vin);
                ps.setString(3, licensePlate);
                ps.setInt(4, yearOfManufacture);
                ps.setInt(5, mileage);
                ps.execute();
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        this.id = id;
                        return this;
                   } else {
                        throw new SQLException("Failed to insert new  car");
                    }
                }
            }
        } else {
            try (PreparedStatement ps = conn.prepareStatement("update car set make = ?, vin = ?, license_plate = ?, year_of_manufacture= ?, mileage = ? where id = ?")) {
                ps.setString(1, make);
                ps.setString(2, vin);
                ps.setString(3, licensePlate);
                ps.setInt(4, yearOfManufacture);
                ps.setInt(5, mileage);
                ps.setInt(6, id);
                ps.executeUpdate();
                return this;
            }

        }
    }

    public Car() {
    }

    public Car(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.make = rs.getString("make");
        this.vin = rs.getString("vin");
        this.licensePlate = rs.getString("license_plate");
        this.yearOfManufacture = rs.getInt("year_of_manufacture");
        this.mileage = rs.getInt("mileage");
    }

    private Car(Builder builder) {
        this.make = builder.make;
        this.vin = builder.vin;
        this.licensePlate = builder.licensePlate;
        this.yearOfManufacture = builder.yearOfManufacture;
        this.mileage = builder.mileage;
    }


    public static class Builder {
        private String make;
        private String vin;
        private String licensePlate;
        private int yearOfManufacture;
        private int mileage;

        public static Builder builder() {
            return new Builder();
        }

        public Builder() {
        }


        public Builder setMake(String make) {
            this.make = make;
            return this;
        }

        public Builder setVin(String vin) {
            this.vin = vin;
            return this;
        }

        public Builder setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Builder setYearOfManufacture(int yearOfManufacture) {
            this.yearOfManufacture = yearOfManufacture;
            return this;
        }

        public Builder setMileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Car build() {
            Car car = new Car(this);
            Set<ConstraintViolation<Car>> validate = Validation.buildDefaultValidatorFactory().getValidator().validate(car);

            if (!validate.isEmpty()) {
                throw new IllegalArgumentException(
                        validate.stream()
                                .map(rentalConstraintViolation -> {
                                    String fieldName = rentalConstraintViolation.getPropertyPath().toString().toUpperCase();
                                    return fieldName + " " + rentalConstraintViolation.getMessage();
                                }).collect(Collectors.joining(", "))
                );
            }

            return car;
        }
    }

    /**
     * Constructs a car with the specified details.
     *
     * @param make              the make (brand) of the car
     * @param vin               the VIN code of the car
     * @param licensePlate      the license plate number of the car
     * @param yearOfManufacture the year the car was manufactured
     * @param mileage           the current mileage of the car
     */
    public Car(String make, String vin, String licensePlate, int yearOfManufacture, int mileage) {
        this.make = make;
        this.vin = vin;
        this.licensePlate = licensePlate;
        this.yearOfManufacture = yearOfManufacture;
        this.mileage = mileage;
    }

    // Getters and setters omitted for brevity

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                "make='" + make + '\'' +
                ", vin='" + vin + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", yearOfManufacture=" + yearOfManufacture +
                ", mileage=" + mileage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return yearOfManufacture == car.yearOfManufacture &&
                mileage == car.mileage &&
                Objects.equals(make, car.make) &&
                Objects.equals(vin, car.vin) &&
                Objects.equals(licensePlate, car.licensePlate);
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, vin, licensePlate, yearOfManufacture, mileage);
    }

    @Override
    public int compareTo(Car otherCar) {
        return Integer.compare(this.yearOfManufacture, otherCar.yearOfManufacture);
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}