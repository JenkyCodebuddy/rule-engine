package jenky.codebuddy;

import jenky.codebuddy.models.restModels.Commit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by joost on 23-5-2016.
 */
public class springTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        Commit commit = (Commit) context.getBean("commit");
        System.out.println("commit = " + commit);
        commit.setBranch("dua");
        System.out.println("commit = " + commit);
    }
}
