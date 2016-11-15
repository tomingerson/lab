package de.fh_kiel.person;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * A Projectmanager is a Developer with some experience in project management.
 */
public class ProjectManager implements Serializable {

    private static final long serialVersionUID = -1463477907893936200L;

    private final Developer developer;
    private int experienceInYearsAsPM;


    public ProjectManager(final Developer developer) {
        this.developer = developer;
    }


    public int getExperienceInYearsAsPM() {
        return this.experienceInYearsAsPM;
    }

    public void setExperienceInYearsAsPM(final int experienceInYearsAsPM) {
        this.experienceInYearsAsPM = experienceInYearsAsPM;
    }

    public Developer getDeveloper() {
        return developer;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ProjectManager)) {
            return false;
        }
        final ProjectManager objCurr = (ProjectManager) o;
        return new EqualsBuilder().append(developer.getId(), objCurr.developer.getId()).isEquals();
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder().append(developer.getId()).hashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
