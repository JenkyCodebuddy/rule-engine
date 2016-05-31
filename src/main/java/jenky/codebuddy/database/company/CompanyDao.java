package jenky.codebuddy.database.company;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Company;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
public interface CompanyDao extends GenericDao<Company, Integer> {
    public List<Company> getCompanies();

    public boolean checkIfCompanyExists(String company);
}
