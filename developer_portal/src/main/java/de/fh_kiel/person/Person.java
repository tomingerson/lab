package de.fh_kiel.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Person is a basic implementation having a name, a birthdate and a gender.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Person implements Serializable {

    private static final long serialVersionUID = -7226275274513325803L;

    private Long id;

    private String firstName, lastName;

    private LocalDate dayOfBirth;

    private Gender gender;
}
