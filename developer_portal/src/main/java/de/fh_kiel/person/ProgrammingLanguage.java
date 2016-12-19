package de.fh_kiel.person;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A programming language.
 *
 * @author Created by tom on 04.12.2016.
 */
@Entity
public class ProgrammingLanguage implements Serializable {

    private static final long serialVersionUID = 6424200047500493324L;

    @Id
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    private Set<Developer> developers = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProgrammingLanguage)) return false;
        ProgrammingLanguage programmingLanguage = (ProgrammingLanguage) o;
        return getName() != null && Objects.equals(getName(), programmingLanguage.getName());
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .toString();
    }
}
