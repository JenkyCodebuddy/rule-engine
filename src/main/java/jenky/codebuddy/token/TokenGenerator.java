package jenky.codebuddy.token;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;


public class TokenGenerator {

    private Key signingKey;

    /**
     * Generates a token with an expiration date if set.
     * @param id
     * @param issuer
     * @param subject
     * @param ttlMillis
     * @return String token
     */
    public String createJWT(String id, String issuer, String subject, Long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        Long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        this.signingKey =  MacProvider.generateKey();

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //Make it expire if time greater than 0
        if (ttlMillis > 0) {
            Long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the token and return a string
        return builder.compact();
    }

    public Key getSigningKey() {
        return signingKey;
    }

    public void setSigningKey(Key signingKey) {
        this.signingKey = signingKey;
    }
}
