package jenky.codebuddy.services;

import jenky.codebuddy.models.rest.Response;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by joost on 2-9-2016.
 */
public class SecurityService {
    public SecurityService(){

    }

    public Response checkHash(String calculatedHash, String hash){
        System.out.println(calculatedHash);
        System.out.println(hash);
        if(insecure_compare(calculatedHash, hash)){
            return new Response(200, "Hash is valid");
        }
        else{
            return new Response(500, "Hash is invalid");
        }
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

    private boolean insecure_compare(String calculatedHash, String hash){
        boolean equals = true;
        for (int i = 0; i <= calculatedHash.length() - 1; i++) {
            if(calculatedHash.getBytes()[i] != hash.getBytes()[i]){
                equals = false;
                break;
            }
        }
        return equals;
    }
}
