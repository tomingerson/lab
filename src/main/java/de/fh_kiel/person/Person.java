package de.fh_kiel.person;


import java.time.LocalDate;
import java.time.Period;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class Person modelling a person
 *
 * @author jpr
 */
public class Person implements Comparable<Person> {

    private String firstName;
    private String lastName;
    private LocalDate dayOfBirth;
    private Gender gender;

    /**
     * Default constructor
     */
    public Person() {
        // nothing to do here
    }

    /**
     * Constructor
     *
     * @param firstName  the firstname
     * @param lastName   the lastname
     * @param dayOfBirth the birthday
     * @param gender     the gender
     */
    public Person(final String firstName, final String lastName, final LocalDate dayOfBirth, final Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dayOfBirth = dayOfBirth;
        this.gender = gender;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public final String getLastName() {
        return lastName;
    }

    public final void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public final LocalDate getDayOfBirth() {
        return dayOfBirth;
    }

    public final void setDayOfBirth(LocalDate dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public final Gender getGender() {
        return gender;
    }

    public final void setGender(Gender gender) {
        this.gender = gender;
    }

    public final int getAge() {
        if (dayOfBirth != null) {
            return Period.between(this.dayOfBirth, LocalDate.now()).getYears();
        }
        return 0;
    }

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person)) {
            return false;
        }
        Person checkPerson = (Person) o;

        return new EqualsBuilder().append(this.firstName, checkPerson.getFirstName()).append(this.lastName, checkPerson.getLastName())
                .append(this.dayOfBirth, checkPerson.getDayOfBirth()).append(this.gender, checkPerson.getGender()).isEquals();
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder().append(this.firstName).append(this.lastName).append(this.dayOfBirth).append(this.gender).toHashCode();
    }

    @Override
    public int compareTo(final Person other) {
        return new CompareToBuilder().append(this.lastName, other.lastName).append(this.firstName, other.firstName).toComparison();
    }
}

