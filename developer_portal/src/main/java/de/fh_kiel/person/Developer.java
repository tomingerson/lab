package de.fh_kiel.person;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A Developer is a Person that has some experience with some programming languages.
 */
@Getter
@EqualsAndHashCode(callSuper = true, exclude = {"experienceInYears", "minimumSalary",
        "programmingLanguages"})
@ToString
public class Developer extends Person {

    private static final long serialVersionUID = 7129301796553739404L;

    private final Set<String> programmingLanguages = new HashSet<>();
    @Setter
    private int experienceInYears;
    @Setter
    private int minimumSalary;

    public Developer() {
    }

    public Developer(final Long id, final String firstName, final String lastName,
                     final LocalDate dayOfBirth, final Gender gender, int experienceInYears,
                     int minimumSalary, final String... programmingLanguages) {
        super(id, firstName, lastName, dayOfBirth, gender);
        this.experienceInYears = experienceInYears;
        this.minimumSalary = minimumSalary;
        this.programmingLanguages.addAll(Arrays.asList(programmingLanguages));
    }

    public void addProgrammingLanguage(String pl) {
        this.programmingLanguages.add(pl);
    }

    public boolean removeProgrammingLanguage(String pl) {
        return this.programmingLanguages.remove(pl);
    }
}
