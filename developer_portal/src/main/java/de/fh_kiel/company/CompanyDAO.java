package de.fh_kiel.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DAO for operations on {@link Company}-Instances
 *
 * @author jpr
 */
@Repository
public interface CompanyDAO extends JpaRepository<Company, Long>{}
