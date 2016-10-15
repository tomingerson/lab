package de.fh_kiel.person;

import java.time.LocalDate;

public class ProjectManager extends Developer {

    private int experienceInYearsAsPM;

    public ProjectManager() {
    }

    public ProjectManager(final String firstName, final String lastName, final LocalDate dayOfBirth, final Gender gender, final int experienceInYears, final int minimumSalary,
                          final int experienceInYearsAsPM) {
        super(firstName, lastName, dayOfBirth, gender, experienceInYears, minimumSalary);
        this.setExperienceInYearsAsPM(experienceInYearsAsPM);
    }

    public int getExperienceInYearsAsPM() {
        return experienceInYearsAsPM;
    }

    public void setExperienceInYearsAsPM(final int experienceInYearsAsPM) {
        if (experienceInYearsAsPM > getExperienceInYears()) {
            throw new IllegalArgumentException(
                    "experienceInYearsAsPM (" + this.experienceInYearsAsPM + ") is bigger than the overall experienceInYears (" + getExperienceInYears() + ")");
        }
        this.experienceInYearsAsPM = experienceInYearsAsPM;
    }
}
