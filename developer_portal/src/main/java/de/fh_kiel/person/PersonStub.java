package de.fh_kiel.person;


import de.fh_kiel.annotations.Boilerplate;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

/**
 * Class Person modelling a person
 *
 * @author jpr
 */
@Boilerplate
public class PersonStub implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dayOfBirth;
    private Gender gender;


    public final int getAge() {
        if (dayOfBirth != null) {
            return Period.between(this.dayOfBirth, LocalDate.now()).getYears();
        }
        return 0;
    }
}

