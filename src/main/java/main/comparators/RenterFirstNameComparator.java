package main.comparators;

import main.models.Renter;

import java.util.Comparator;

public class RenterFirstNameComparator implements Comparator<Renter> {

    @Override
    public int compare(Renter r1, Renter r2) {
        return r1.getFirstName().compareTo(r2.getFirstName());
    }
}