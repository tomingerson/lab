package de.fh_kiel.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammingLanguageDAO extends JpaRepository<ProgrammingLanguage,
        String> {
}
