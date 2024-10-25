package main.models;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a car rental transaction.
 */
public class Rental implements ISavable<Rental>{

    private Integer id;

    @NotNull
    public Integer carId;

    @NotNull
    public Integer renterId;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @Min(0)
    private double pricePerDay;

    @Min(0)
    private double totalPrice;

    public Rental(){}


    public Rental save(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            if (this.id != null) {
                PreparedStatement ps = conn.prepareStatement("update rental set car_id = ?, renter_id = ?, start_date = ?, end_date = ?, price_per_day =?, total_price = ? where id = ?");
                ps.setInt(1, this.carId);
                ps.setInt(2, this.renterId);
                ps.setString(3, this.startDate.toString());
                ps.setString(4, this.endDate.toString());
                ps.setDouble(5, this.pricePerDay);
                ps.setDouble(6, this.totalPrice);
                ps.setInt(7, this.id);
                ps.executeUpdate();

            } else {
                PreparedStatement ps = conn.prepareStatement("insert into rental (car_id, renter_id, start_date, end_date, price_per_day, total_price) VALUES (?, ?, ?, ?, ?, ?) returning id");
                ps.setInt(1, this.carId);
                ps.setInt(2, this.renterId);
                ps.setString(3, this.startDate.toString());
                ps.setString(4, this.endDate.toString());
                ps.setDouble(5, this.pricePerDay);
                ps.setDouble(6, this.totalPrice);
                int affectedRows = ps.executeUpdate();
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
        }
        throw new SQLException("Failed to save car");
    }

    public Rental(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.carId = rs.getInt("car_id");
        this.renterId = rs.getInt("renter_id");
        this.startDate = LocalDate.parse(rs.getString("start_date"));
        this.endDate = LocalDate.parse(rs.getString("end_date"));
        this.pricePerDay = rs.getDouble("price_per_day");
        this.totalPrice = rs.getDouble("total_price");
    }

    private Rental(Builder builder) {
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.pricePerDay = builder.pricePerDay;
        this.totalPrice = builder.totalPrice;
        this.renterId = builder.renterId;
        this.carId = builder.carId;
    }

    public static class Builder {
        private LocalDate startDate;
        private LocalDate endDate;
        private double pricePerDay;
        private double totalPrice;
        private Integer carId;
        private Integer renterId;

        public static Builder builder(){
            return new Builder();
        }

        /**
         * Sets the car for this rental.
         *
         * @param carId the carId being rented
         * @return the Builder instance
         */
        public Builder setCarId( Integer carId) {
            this.carId = carId;
            return this;
        }


        public Builder setRenterId( Integer renterId) {
            this.renterId = renterId;
            return this;
        }

        /**
         * Sets the start date for this rental.
         *
         * @param startDate the start date of the rental period
         * @return the Builder instance
         */
        public Builder setStartDate( LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        /**
         * Sets the end date for this rental.
         *
         * @param endDate the end date of the rental period
         * @return the Builder instance
         */
        public Builder setEndDate( LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        /**
         * Sets the price per day for this rental.
         *
         * @param pricePerDay the price per day for renting the car
         * @return the Builder instance
         */
        public Builder setPricePerDay(double pricePerDay) {
            this.pricePerDay = pricePerDay;
            return this;
        }

        /**
         * Calculates and sets the total price for this rental.
         *
         * @return the Builder instance
         */
        public Builder calculateTotalPrice() {
            this.totalPrice = pricePerDay * (endDate.toEpochDay() - startDate.toEpochDay());
            return this;
        }

        /**
         * Builds the rental instance.
         *
         * @return a new Rental instance
         */
        public Rental build() {
            Rental rental = new Rental(this);
            Set<ConstraintViolation<Rental>> validate = Validation.buildDefaultValidatorFactory().getValidator().validate(rental);

            if (!validate.isEmpty()) {
                throw new  IllegalArgumentException(
                        validate.stream()
                                .map(rentalConstraintViolation -> {
                                    String fieldName = rentalConstraintViolation.getPropertyPath().toString().toUpperCase();
                                    return fieldName + " " + rentalConstraintViolation.getMessage();
                                }).collect(Collectors.joining(", "))
                );
            }

            return rental;
        }
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", carId=" + carId +
                ", renterId=" + renterId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", pricePerDay=" + pricePerDay +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }


    public Integer getRenterId() {
        return renterId;
    }

    public void setRenterId(Integer renterId) {
        this.renterId = renterId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}