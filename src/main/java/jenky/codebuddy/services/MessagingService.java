package jenky.codebuddy.services;

import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Response;
import org.springframework.orm.jpa.vendor.Database;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * Created by joost on 17-6-2016.
 */
public class MessagingService {

    public MessagingService() {
    }

    public Response saveMessagingTokenForUser(String authenticationToken, String messagingToken){
        User user = DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(authenticationToken).getUser();
        user.setMessageToken(messagingToken);
        user.setUpdated_at(new Date());
        DatabaseFactory.getUserService().saveOrUpdate(user);
        return new Response(200,"Token saved");
    }

    public Response deleteMessagingTokenForUser(String authenticationToken, String messagingToken){
        User user = DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(authenticationToken).getUser();
        user.setMessageToken("");
        DatabaseFactory.getUserService().saveOrUpdate(user);
        return new Response(200,"Token deleted");
    }
}
