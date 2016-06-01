package jenky.codebuddy.token;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import java.security.Key;


/**
 * Created by Fabian on 27-5-2016.
 */
public class Verification {
    public static void verify(String token, Key signingkey, String id) {
        try {
            System.out.println(Jwts.parser().setSigningKey(signingkey).parseClaimsJws(token).getBody().getSubject().equals(id));
        } catch (SignatureException e) {
            System.out.println("nerd");
        }
    }
}
