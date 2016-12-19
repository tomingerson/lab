package de.fh_kiel.person;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectManager)) return false;
        ProjectManager projectManager = (ProjectManager) o;
        return getId() != null && Objects.equals(getId(), projectManager.getId());
    }

    @Override
    public int hashCode() {
        return 19;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
