package jenky.codebuddy.database.company;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Company;

import java.util.List;

/**
 * Specific methods for CompanyDao
 */
public interface CompanyDao extends GenericDao<Company, Integer> {
    /**
     * get all the companies
     * @return List of all the companies
     */
    public List<Company> getCompanies();

    /**
     * Check if a company exists
     * @param company
     * @return true or false
     */
    public boolean checkIfCompanyExists(String company);
}
