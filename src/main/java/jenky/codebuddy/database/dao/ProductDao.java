package jenky.codebuddy.database.dao;

import javax.persistence.Column;
import java.util.Collection;

/**
 * Created by joost on 26-5-2016.
 */
public interface ProductDao {

    public Collection loadProductsByCategory(String category);

}
