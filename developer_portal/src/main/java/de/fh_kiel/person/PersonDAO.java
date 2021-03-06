package de.fh_kiel.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Access to {@link Person persons}.
 * @author Created by tom on 15.10.2016.
 */
@Repository
public interface PersonDAO extends JpaRepository<Person, Long> {
}
