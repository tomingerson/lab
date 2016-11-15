package de.fh_kiel.company;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * DAO for operations on {@link Company}-Instances
 *
 * @author jpr
 */
@Repository
public class CompanyDAO {

    private final Set<Company> companies;

    /**
     * Default constructor for spring
     */
    public CompanyDAO() {
        this.companies = new HashSet<>();
    }

    /**
     * Constructor
     *
     * @param companies the already existing companies
     */
    public CompanyDAO(Company... companies) {
        this.companies = new HashSet<>(Arrays.asList(companies));
    }


    /**
     * Stores the passed Company.
     *
     * @param company the company to create
     */
    public void createCompany(final Company company) {
        if (company.getId() != null) {
            throw new IllegalArgumentException("Passed company is not new");
        }
        final Long maxId = Collections.max(companies, (o1, o2) -> {
            final CompareToBuilder ctb = new CompareToBuilder();
            ctb.append(o1.getId(), o2.getId());
            return ctb.toComparison();
        }).getId();
        company.setId(maxId + 1);
        companies.add(company);
    }

    /**
     * Updates the passed Company in our datastore.
     *
     * @param company the company to update
     */
    public void updateCompany(final Company company) {
        // Remove the old version of the passed company
        deleteCompany(company);
        companies.add(company);
    }

    /**
     * Deletes the passed Company from our datastore.
     *
     * @param company the company to delete
     */
    public void deleteCompany(final Company company) {
        companies.remove(company);
    }

    /**
     * Trys to find the company with the given id
     *
     * @param id the id of the company
     * @return the company found or {@code null}, if no matching company was found
     */
    public Company getCompanyById(final Long id) {
        return companies.stream().filter(c -> c.getId().equals(id)).findFirst()
                .orElseThrow(() -> new IllegalStateException("no company found with id " + id));
    }

    /**
     * @return all companies currently stored
     */
    public Collection<Company> getAllCompanies() {
        return new ArrayList<>(companies);
    }


}
