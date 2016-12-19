package de.fh_kiel.person;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * A Developer is a Person that has some experience with some programming languages and
 * wants to earn some money.
 */
@Entity
public class Developer extends Person {

    private static final long serialVersionUID = 7071792139422669663L;

    @ManyToMany
    @JoinTable(name = "DevPl")
    private final Set<ProgrammingLanguage> programmingLanguages = new HashSet<>();

    @Column(nullable = false)
    private int experienceInYears;

    @Column(nullable = false)
    private int minimumSalary;

    public Set<ProgrammingLanguage> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public int getExperienceInYears() {
        return experienceInYears;
    }

    public void setExperienceInYears(int experienceInYears) {
        this.experienceInYears = experienceInYears;
    }

    public int getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(int minimumSalary) {
        this.minimumSalary = minimumSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Developer)) return false;

        Developer developer = (Developer) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(experienceInYears, developer.experienceInYears)
                .append(minimumSalary, developer.minimumSalary)
                .append(programmingLanguages, developer.programmingLanguages)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(programmingLanguages)
                .append(experienceInYears)
                .append(minimumSalary)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("programmingLanguages", programmingLanguages)
                .append("experienceInYears", experienceInYears)
                .append("minimumSalary", minimumSalary)
                .toString();
    }
}
