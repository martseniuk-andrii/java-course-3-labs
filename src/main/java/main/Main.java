package main;

import models.Car;
import models.Rental;
import models.Renter;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Car car = new Car("Toyota", "JT3FJ62G8K1003279", "AB123CD", 2015, 85000);
        Renter renter = new Renter("John", "Doe", "ID123456", "DL987654");

        Rental rental = new Rental.Builder()
                .setCar(car)
                .setRenter(renter)
                .setStartDate(LocalDate.of(2024, 10, 20))
                .setEndDate(LocalDate.of(2024, 10, 25))
                .setPricePerDay(50.0)
                .calculateTotalPrice()
                .build();

        System.out.println(rental);
    }
}