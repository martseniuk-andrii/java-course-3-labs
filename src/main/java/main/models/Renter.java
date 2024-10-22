package main.models;

import java.util.Objects;

/**
 * Represents a person who rents a car.
 */
public class Renter {
    private String firstName;
    private String lastName;
    private String idDocument;
    private String driverLicense;

    public Renter(){}

    /**
     * Constructs a renter with the specified details.
     *
     * @param firstName the first name of the renter
     * @param lastName the last name of the renter
     * @param idDocument the ID document number of the renter
     * @param driverLicense the driver's license number of the renter
     */
    public Renter(String firstName, String lastName, String idDocument, String driverLicense) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idDocument = idDocument;
        this.driverLicense = driverLicense;
    }

    // Getters and setters omitted for brevity

    @Override
    public String toString() {
        return "Renter{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", idDocument='" + idDocument + '\'' +
                ", driverLicense='" + driverLicense + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Renter renter = (Renter) o;
        return Objects.equals(firstName, renter.firstName) &&
                Objects.equals(lastName, renter.lastName) &&
                Objects.equals(idDocument, renter.idDocument) &&
                Objects.equals(driverLicense, renter.driverLicense);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, idDocument, driverLicense);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(String idDocument) {
        this.idDocument = idDocument;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }
}