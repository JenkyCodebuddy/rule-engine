package jenky.codebuddy.database.company;

import jenky.codebuddy.database.generic.GenericServiceImpl;
import jenky.codebuddy.models.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer for Company. Inherits GenericService and implements CompanyService interface.
 */
@Service
public class CompanyServiceImpl extends GenericServiceImpl<Company, Integer> implements CompanyService {

    private CompanyDao companyDao;

    /**
     * Injected by spring
     * @param companyDao
     */
    @Autowired
    public CompanyServiceImpl(@Qualifier("companyDaoImpl") CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public CompanyServiceImpl(){

    }

    /**
     * Get all the companies
     * @return list containing companies
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Company> getCompanies(){
        return companyDao.getCompanies();
    }

    /**
     * Ask the companyDao if a company exists
     * @param company
     * @return true or false
     */
    @Override
    @Transactional(propagation =  Propagation.REQUIRED)
    public boolean checkIfCompanyExists(String company) {
        return companyDao.checkIfCompanyExists(company);
    }
}
