package main;

import main.comparators.RenterFirstNameComparator;
import main.exceptions.CarNotFoundException;
import main.models.Car;
import main.models.Rental;
import main.models.Renter;
import main.serializers.IEntitySerializer;
import main.serializers.impl.JsonEntitySerializer;
import main.serializers.impl.XmlEntitySerializer;
import main.serializers.impl.YamlEntitySerializer;
import main.services.RentalService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        RentalService rentalService = new RentalService();

        File carsDataFile = Paths.get("./data/cars.yaml").toFile();
        File renterDataFile = Paths.get("./data/renter.xml").toFile();
        File rentalsDataFile = Paths.get("./data/rental.json").toFile();

        IEntitySerializer<Car> carsSerializer = new YamlEntitySerializer<>();
        IEntitySerializer<Renter> rentersSerializer = new XmlEntitySerializer<>();
        IEntitySerializer<Rental> rentalsSerializer = new JsonEntitySerializer<>();
        //data loaded
        List<Renter> renters = Arrays.stream(rentersSerializer.deserializeArray(renterDataFile, Renter[].class)).collect(Collectors.toList());
        List<Car> cars = Arrays.stream(carsSerializer.deserializeArray(carsDataFile, Car[].class)).collect(Collectors.toList());
        List<Rental> rentals = Arrays.stream(rentalsSerializer.deserializeArray(rentalsDataFile, Rental[].class)).collect(Collectors.toList());
//        cars.add(
//                Car.Builder.builder()
//                        .setMake("asdasd")
//                        .setMileage(-1)  //error here
//                        .setLicensePlate("asdas")
//                        .setVin("123sdfsdf")
//                        .setYearOfManufacture(1899)  //error here
//                        .build()
//        );

        renters.add(
                Renter.Builder.builder()
                        .setDriverLicense("DL:asdsadasd")
                        .setIdDocument("test123") // invalid id document
                        .setLastName("Abum")
                        .setFirstName("Kek")
                        .build()
        );

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

        carsSerializer.serializeArray(cars.toArray(new Car[]{}), carsDataFile);
        rentersSerializer.serializeArray(renters.toArray(new Renter[]{}), renterDataFile);
        rentalsSerializer.serializeArray( rentals.toArray(new Rental[]{}), rentalsDataFile);
        System.out.println("all ok");
    }
}