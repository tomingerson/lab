package de.fh_kiel.person;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Developer is a Person that has some experience with some programming languages and
 * wants to earn some money.
 */
@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
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
        return getId() != null && Objects.equals(getId(), developer.getId());
    }

    @Override
    public int hashCode() {
        return 21;
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
