package main.models;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.constraints.*;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a car available for rent.
 */
public class Car implements Comparable<Car>{
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


    public Car(){}

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

        public static Builder builder(){
            return new Builder();
        }

        public Builder(){}


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
                throw new  IllegalArgumentException(
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