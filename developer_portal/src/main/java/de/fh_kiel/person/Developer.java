package de.fh_kiel.person;

import java.util.Set;

import javax.annotation.Generated;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Generated("annotation processor")
public class Developer extends Person {
    private int experienceInYears;

    private int minimumSalary;

    private Set<String> programmingLanguages;

    public Developer() {
    }

    public Developer(final int experienceInYears, final int minimumSalary, final Set<String> programmingLanguages) {
        this.experienceInYears = experienceInYears;
        this.minimumSalary = minimumSalary;
        this.programmingLanguages = programmingLanguages;
    }

    public int getExperienceInYears() {
        return this.experienceInYears;
    }

    public void setExperienceInYears(final int experienceInYears) {
        this.experienceInYears = experienceInYears;
    }

    public int getMinimumSalary() {
        return this.minimumSalary;
    }

    public void setMinimumSalary(final int minimumSalary) {
        this.minimumSalary = minimumSalary;
    }

    public Set<String> getProgrammingLanguages() {
        return this.programmingLanguages;
    }

    public void setProgrammingLanguages(final Set<String> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Developer)) {
            return false;
        }
        final Developer objCurr = (Developer) o;
        return new EqualsBuilder().append(super.getId(), objCurr.getId()).isEquals();
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder().append(super.getId()).hashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
