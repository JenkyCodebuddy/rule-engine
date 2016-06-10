package jenky.codebuddy.database.company;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Company;

import java.util.List;

/**
 * Specific methods for CompanyService
 */
public interface CompanyService extends GenericService<Company> {
    public List<Company> getCompanies();

    public boolean checkIfCompanyExists(String company);
}
