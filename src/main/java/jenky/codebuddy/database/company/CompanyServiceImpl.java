package jenky.codebuddy.database.company;

import jenky.codebuddy.database.generic.GenericServiceImpl;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
@Service
public class CompanyServiceImpl extends GenericServiceImpl<Company, Integer> implements CompanyService {

    private CompanyDao companyDao;

    @Autowired
    public CompanyServiceImpl(@Qualifier("companyDaoImpl") CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public CompanyServiceImpl(){

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Company> getCompanies(){
        return companyDao.getCompanies();
    }

    @Override
    @Transactional(propagation =  Propagation.REQUIRED)
    public boolean checkIfCompanyExists(String company) {
        return companyDao.checkIfCompanyExists(company);
    }
}
