package jenky.codebuddy.token;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import java.security.Key;


public class Verify {
    /**
     * Verifies the content of the token with the id
     * @param token
     * @param signingkey
     * @param id
     * @return
     */
    public boolean verify(String token, Key signingkey, String id) {
        try {
            return Jwts.parser().setSigningKey(signingkey).parseClaimsJws(token).getBody().getSubject().equals(id);
        } catch (SignatureException e) {
            return false;
        }
    }
}
