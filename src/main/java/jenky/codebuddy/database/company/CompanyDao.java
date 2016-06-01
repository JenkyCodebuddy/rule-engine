package jenky.codebuddy.database.company;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Company;

import java.util.List;

/**
 * Specific methods for CompanyDao
 */
public interface CompanyDao extends GenericDao<Company, Integer> {
    public List<Company> getCompanies();

    public boolean checkIfCompanyExists(String company);
}
