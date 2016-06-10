package jenky.codebuddy.token;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import java.security.Key;


/**
 * Created by Fabian on 27-5-2016.
 */
public class Verify {
    public boolean verify(String token, Key signingkey, String id) {
        try {
            return Jwts.parser().setSigningKey(signingkey).parseClaimsJws(token).getBody().getSubject().equals(id);
        } catch (SignatureException e) {
            return false;
        }
    }
}
