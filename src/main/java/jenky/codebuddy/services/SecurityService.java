package jenky.codebuddy.services;

import jenky.codebuddy.controllers.SecurityController;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by joost on 2-9-2016.
 */
public class SecurityService {
    public SecurityService(){

    }

    public void checkHash(String calculatedHash, String hash){
        System.out.println(calculatedHash);
        System.out.println(hash);
        System.out.println(calculatedHash.equals(hash));
    }

    public String hmacSha1(String file) {
        try {
            String key = "null";
            // Get an hmac_sha1 key from the raw key bytes
            byte[] keyBytes = key.getBytes();
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);

            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(file.getBytes());

            // Convert raw bytes to Hex
            byte[] hexBytes = new Hex().encode(rawHmac);

            //  Covert array of Hex bytes to a String
            return new String(hexBytes, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
