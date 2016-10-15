package de.fh_kiel.person;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Class Developer, which is a Person
 *
 * @author jpr
 */
public class Developer extends Person {

    private int experienceInYears;
    private int minimumSalary;
    private final Set<String> programmingLanguages = new HashSet<>();

    public Developer() {

    }

    public Developer(final String firstName, final String lastName, final LocalDate dayOfBirth, final Gender gender, final int experienceInYears, final int minimumSalary) {
        super(firstName, lastName, dayOfBirth, gender);
        this.experienceInYears = experienceInYears;
        this.minimumSalary = minimumSalary;
    }

    public int getExperienceInYears() {
        return experienceInYears;
    }

    public void setExperienceInYears(final int experienceInYears) {
        this.experienceInYears = experienceInYears;
    }

    public int getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(final int minimumSalary) {
        this.minimumSalary = minimumSalary;
    }

    public Set<String> getProgrammingLanguages() {
        return programmingLanguages;
    }


    public void removeProgrammingLanguage(String programmingLanguage) {
        programmingLanguages.remove(programmingLanguage);
    }

    public void addProgrammingLanguage(final String programmingLanguage) {
        this.programmingLanguages.add(programmingLanguage);
    }
}
