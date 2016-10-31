package de.fh_kiel.company;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.fh_kiel.CheckNull;

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
    public void createCompany(final Company company) {
        companyDAO.createCompany(company);
    }

    /**
     * Updates the passed Company in our datastore.
     *
     * @param company the company to update
     */
    @CheckNull
    public void updateCompany(final Company company) {
        companyDAO.updateCompany(company);
    }

    /**
     * Deletes the passed Company from our datastore.
     *
     * @param company the company to delete
     */
    @CheckNull
    public void deleteCompany(final Company company) {
        companyDAO.deleteCompany(company);
    }


    /**
     * Trys to find the company with the given id
     *
     * @param id the id of the company
     * @return the company found or {@code null}, if no matching company was found
     */
    @CheckNull
    public Company getCompanyById(final Long id) {
        return companyDAO.getCompanyById(id);
    }

    /**
     * @return all companies currently stored
     */
    public Collection<Company> getAllCompanies() {
        return companyDAO.getAllCompanies();
    }

}
