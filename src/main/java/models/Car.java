package models;

import java.util.Objects;

/**
 * Represents a car available for rent.
 */
public class Car {
    private String make;
    private String vin;
    private String licensePlate;
    private int yearOfManufacture;
    private int mileage;

    /**
     * Constructs a car with the specified details.
     *
     * @param make the make (brand) of the car
     * @param vin the VIN code of the car
     * @param licensePlate the license plate number of the car
     * @param yearOfManufacture the year the car was manufactured
     * @param mileage the current mileage of the car
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

    @Override
    public int hashCode() {
        return Objects.hash(make, vin, licensePlate, yearOfManufacture, mileage);
    }
}