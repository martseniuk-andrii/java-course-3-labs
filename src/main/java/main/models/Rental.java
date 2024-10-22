package main.models;

import java.time.LocalDate;

/**
 * Represents a car rental transaction.
 */
public class Rental {
    private Car car;
    private Renter renter;
    private LocalDate startDate;
    private LocalDate endDate;
    private double pricePerDay;
    private double totalPrice;

    public Rental(){}


    private Rental(Builder builder) {
        this.car = builder.car;
        this.renter = builder.renter;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.pricePerDay = builder.pricePerDay;
        this.totalPrice = builder.totalPrice;
    }

    public static class Builder {
        private Car car;
        private Renter renter;
        private LocalDate startDate;
        private LocalDate endDate;
        private double pricePerDay;
        private double totalPrice;

        public static Builder builder(){
            return new Builder();
        }

        /**
         * Sets the car for this rental.
         *
         * @param car the car being rented
         * @return the Builder instance
         */
        public Builder setCar(Car car) {
            this.car = car;
            return this;
        }

        /**
         * Sets the renter for this rental.
         *
         * @param renter the person renting the car
         * @return the Builder instance
         */
        public Builder setRenter(Renter renter) {
            this.renter = renter;
            return this;
        }

        /**
         * Sets the start date for this rental.
         *
         * @param startDate the start date of the rental period
         * @return the Builder instance
         */
        public Builder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        /**
         * Sets the end date for this rental.
         *
         * @param endDate the end date of the rental period
         * @return the Builder instance
         */
        public Builder setEndDate(LocalDate endDate) {
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
            return new Rental(this);
        }
    }

    @Override
    public String toString() {
        return "Rental{" +
                "car=" + car +
                ", renter=" + renter +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", pricePerDay=" + pricePerDay +
                ", totalPrice=" + totalPrice +
                '}';
    }


    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
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