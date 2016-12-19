package de.fh_kiel.company;

import de.fh_kiel.CheckNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for {@link Company}
 *
 * @author jpr
 */
@Service
public class CompanyService {

    private final CompanyDAO companyDAO;

    /**
     * Constructor
     *
     * @param companyDAO the data storage for instances of {@link Company}
     */
    @Autowired
    public CompanyService(final CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

    /**
     * Persists the passed Company in our datastore.
     *
     * @param company the company to create
     */
    @CheckNull
    @Transactional
    public Company createCompany(final Company company) {
        return companyDAO.save(company);
    }

    /**
     * Updates the passed Company in our datastore.
     *
     * @param company the company to update
     */
    @CheckNull
    @Transactional
    public Company updateCompany(final Company company) {
        return companyDAO.save(company);
    }

    /**
     * Deletes the passed Company from our datastore.
     *
     * @param company the company to delete
     */
    @CheckNull
    @Transactional
    public void deleteCompany(final Company company) {
        companyDAO.delete(company);
    }


    /**
     * Trys to find the company with the given id
     *
     * @param id the id of the company
     * @return the company found or {@code null}, if no matching company was found
     */
    @CheckNull
    @Transactional
    public Optional<Company> getCompanyById(final Long id) {
        return Optional.ofNullable(companyDAO.findOne(id));
    }

    /**
     * @return all companies currently stored
     */
    @Transactional
    public List<Company> getAllCompanies() {
        return companyDAO.findAll();
    }

}
