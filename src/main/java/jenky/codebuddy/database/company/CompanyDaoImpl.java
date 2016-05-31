package jenky.codebuddy.database.company;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Company;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
@Repository
public class CompanyDaoImpl extends GenericDaoImpl<Company, Integer> implements CompanyDao {

    @Override
    public List<Company> getCompanies() {
        List<Company> companies = super.findAll();
        return companies;
    }

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
