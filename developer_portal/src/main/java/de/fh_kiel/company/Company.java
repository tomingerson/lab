package de.fh_kiel.company;

import de.fh_kiel.person.Person;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.List;

public class Company implements Serializable {

    private static final long serialVersionUID = 5880276806654543721L;

    private Long id;

    private String name;

    private List<Person> employees;

    public Company() {
    }

    public Company(final Long id, final String name, final List<Person> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Person> getEmployees() {
        return this.employees;
    }

    public void setEmployees(final List<Person> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Company)) {
            return false;
        }
        final Company objCurr = (Company) o;
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
