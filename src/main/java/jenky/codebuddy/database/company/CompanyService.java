package jenky.codebuddy.database.company;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Company;

import java.util.List;

/**
 * Specific methods for CompanyService
 */
public interface CompanyService extends GenericService<Company, Integer> {
    /**
     * Get all the companies
     * @return list containing companies
     */
    public List<Company> getCompanies();

    /**
     * Ask the companyDao if a company exists
     * @param company
     * @return true or false
     */
    public boolean checkIfCompanyExists(String company);
}
