package jenky.codebuddy;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.database.score.ScoreServiceImpl;
import jenky.codebuddy.models.entities.Score;
import jenky.codebuddy.models.rest.UserCommit;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Fabian on 9-6-2016.
 */
public class ScoreTest extends GenericDaoImpl<Score, Integer> {

    private ApplicationContext context;


    public static void main(String[] args) {

    }

    @Test
    public void getAllScore() throws Exception {
        Score testScore = new Score();
        Score score = super.findById(1);
        assertEquals(testScore, score);
    }

    @Test
    public void testModel() throws Exception{
//        UserCommit c = new UserCommit("Joost","joost1235@hotmail.com","master","asdasd12113ui1h3ir","cyka/TestclassForDAO/idi/nahui");
//        assertNotEquals(c.getEmail(),"");
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
