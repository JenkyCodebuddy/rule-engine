package jenky.codebuddy.database.company;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Company;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Peristence for CompanyDao. Inherits GenericaDao and implements the CompanyDao interface
*/
@Repository
public class CompanyDaoImpl extends GenericDaoImpl<Company, Integer> implements CompanyDao {

    /**
     * get all the companies
     * @return List of all the companies
     */
    @Override
    public List<Company> getCompanies() {
        List<Company> companies = super.findAll();
        return companies;
    }

    /**
     * Check if a company exists
     * @param company
     * @return true or false
     */
    @Override
    public boolean checkIfCompanyExists(String company) {
        String hql = "FROM Company c WHERE c.name = :company";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("company",company);
        Company result = (Company) query.uniqueResult();
        if(result != null){
            return true;
        }
        else{
            return false;
        }
    }
}
