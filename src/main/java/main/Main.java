package main;

import main.comparators.RenterFirstNameComparator;
import main.exceptions.CarNotFoundException;
import main.models.Car;
import main.models.Rental;
import main.models.Renter;
import main.services.RentalService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RentalService rentalService = new RentalService();

        List<Car> cars = Arrays.asList(
                new Car("Toyota", "VIN1", "ABC123", 2010, 120000),
                new Car("Honda", "VIN2", "DEF456", 2015, 90000),
                new Car("Ford", "VIN3", "GHI789", 2005, 150000)
        );

        // Створення об'єктів Renter
        List<Renter> renters = Arrays.asList(
                new Renter("John", "Doe", "ID123", "DL123"),
                new Renter("Alice", "Smith", "ID456", "DL456"),
                new Renter("Bob", "Brown", "ID789", "DL789")
        );


        List<Rental> rentals = new ArrayList<>();

        for (Renter renter : renters) {
            Car freeCar = rentalService.getOneFreeCar(cars, rentals);
            if (freeCar == null) {
                throw new CarNotFoundException("Free car not found");
            }
            rentals.add(
                    Rental.Builder.builder()
                            .setCar(freeCar)
                            .setRenter(renter)
                            .setPricePerDay(200.0)
                            .setStartDate(LocalDate.now())
                            .setEndDate(LocalDate.now().plusDays(3))
                            .build()
            );
        }


        System.out.println("Rentals" + rentals);

        // 1. Знаходження машини з найбільшим пробігом
        Car carWithHighestMileage = rentalService.findCarWithHighestMileage(cars);
        System.out.println("Car with the highest mileage: " + carWithHighestMileage);

        // 2. Сортування рентерів за прізвищем
        List<Renter> sortedRenters = rentalService.sortRentersByLastName(renters);
        System.out.println("Sorted renters by last name: " + sortedRenters);

        // 3. Пошук машин, старіших за 10 років
        List<Car> oldCars = rentalService.getCarsOlderThan(cars, 10);
        System.out.println("Cars older than 10 years: " + oldCars);

        // 4. Сортування рентерів за ім'ям за допомогою Comparator
        RenterFirstNameComparator firstNameComparator = new RenterFirstNameComparator();
        renters.sort(firstNameComparator);
        System.out.println("Sorted renters by first name: " + renters);
    }
}