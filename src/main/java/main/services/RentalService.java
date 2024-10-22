package main.services;

import main.models.Car;
import main.models.Rental;
import main.models.Renter;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for handling operations related to rentals.
 */
public class RentalService {

    /**
     * Finds the car with the highest mileage from the collection of cars.
     *
     * @param cars the collection of cars
     * @return the car with the highest mileage
     */
    public Car findCarWithHighestMileage(List<Car> cars) {
        return cars.stream()
                .max(Comparator.comparingInt(Car::getMileage))
                .orElse(null);
    }

    /**
     * Sorts the list of renters alphabetically by their last name.
     *
     * @param renters the collection of renters
     * @return the sorted list of renters by last name
     */
    public List<Renter> sortRentersByLastName(List<Renter> renters) {
        return renters.stream()
                .sorted(Comparator.comparing(Renter::getLastName))
                .collect(Collectors.toList());
    }

    /**
     * Filters and returns a list of cars that are older than a specified number of years.
     *
     * @param cars the collection of cars
     * @param minAge the minimum age of the car in years
     * @return a list of cars older than the specified number of years
     */
    public List<Car> getCarsOlderThan(List<Car> cars, int minAge) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return cars.stream()
                .filter(car -> (currentYear - car.getYearOfManufacture()) > minAge)
                .collect(Collectors.toList());
    }

    /**
     * Return one free car or null
     *
     * @param cars the collection of cars
     * @param rentals list of Rental
     * @return a free car
     */
    public Car getOneFreeCar(List<Car> cars, List<Rental> rentals) {
        for (Car car : cars) {
            boolean isCarRented = rentals.stream().anyMatch(rental1 -> rental1.getCar().equals(car) && LocalDate.now().isBefore(rental1.getEndDate()));
            if (!isCarRented){
                return car;
            }
        }
        return null;
    }
}