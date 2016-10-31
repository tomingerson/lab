package de.fh_kiel.person;

import java.io.Serializable;
import java.time.LocalDate;

import javax.annotation.Generated;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Generated("annotation processor")
public class Person implements Serializable {
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate dayOfBirth;

    private Gender gender;

    public Person() {
    }

    public Person(final Long id, final String firstName, final String lastName, final LocalDate dayOfBirth, final Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dayOfBirth = dayOfBirth;
        this.gender = gender;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDayOfBirth() {
        return this.dayOfBirth;
    }

    public void setDayOfBirth(final LocalDate dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Person)) {
            return false;
        }
        final Person objCurr = (Person) o;
        return new EqualsBuilder().append(this.id, objCurr.id).isEquals();
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder().append(this.id).hashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
