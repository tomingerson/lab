package de.fh_kiel.person;

/**
 * @author Created by tom on 08.10.2016.
 */
public class ProjectManager {
    private final Developer developer;
    private long yearsOfExperience;

    public ProjectManager(final Developer developer) {
        this.developer = developer;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public long getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(final long yearsOfExperience) {
        if (yearsOfExperience > this.getDeveloper().getYearsOfExperience()) {
            throw new IllegalStateException("experience as project manager must not be greater than " +
                    "the experience as developer");
        }
        this.yearsOfExperience = yearsOfExperience;
    }
}
