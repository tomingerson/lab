package de.fh_kiel.person;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Person is a basic implementation having a name, a birthdate and a gender.
 */
@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Person implements Serializable {

    private static final long serialVersionUID = 2158786489745462736L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    private String firstName, lastName;

    private LocalDate dayOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
