package de.fh_kiel.person;

import javax.annotation.Generated;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Generated("annotation processor")
public class ProjectManager extends Developer {
    private int experienceInYearsAsPM;

    public ProjectManager() {
    }

    public ProjectManager(final int experienceInYearsAsPM) {
        this.experienceInYearsAsPM = experienceInYearsAsPM;
    }

    public int getExperienceInYearsAsPM() {
        return this.experienceInYearsAsPM;
    }

    public void setExperienceInYearsAsPM(final int experienceInYearsAsPM) {
        this.experienceInYearsAsPM = experienceInYearsAsPM;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ProjectManager)) {
            return false;
        }
        final ProjectManager objCurr = (ProjectManager) o;
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
