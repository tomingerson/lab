package de.fh_kiel.person;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * A Projectmanager is a Developer with some experience in project management.
 */
@Entity
public class ProjectManager implements Serializable {

    private static final long serialVersionUID = 1928256958685673365L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id")
    private Developer developer;

    @Column(nullable = false)
    private int experienceInYearsAsPM;


    public ProjectManager() {
    }

    public ProjectManager(final Developer developer) {
        this.developer = developer;
    }


    public int getExperienceInYearsAsPM() {
        return this.experienceInYearsAsPM;
    }

    public void setExperienceInYearsAsPM(final int experienceInYearsAsPM) {
        this.experienceInYearsAsPM = experienceInYearsAsPM;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof ProjectManager)) {
            return false;
        }
        final ProjectManager objCurr = (ProjectManager) o;
        return new EqualsBuilder()
                .append(developer.getId(), objCurr.developer.getId())
                .isEquals();
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
