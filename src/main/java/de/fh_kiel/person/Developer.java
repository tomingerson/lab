package de.fh_kiel.person;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by tom on 08.10.2016.
 */
public class Developer extends Person {

    private long yearsOfExperience;
    private double minimumSalary;
    private final List<ProgrammingLanguage> knownLanguages = new ArrayList<>();

    public long getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(long yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public double getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(double minimumSalary) {
        this.minimumSalary = minimumSalary;
    }

    public List<ProgrammingLanguage> getKnownLanguages() {
        return knownLanguages;
    }

    public boolean addNewLanguage(final ProgrammingLanguage pl) {
        return this.knownLanguages.add(pl);
    }

    public boolean  removeLanguage(final ProgrammingLanguage pl) {
        return this.knownLanguages.remove(pl);
    }
}
