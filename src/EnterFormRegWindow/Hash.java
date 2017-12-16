package EnterFormRegWindow;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/** This method hashing pasword that is sent to it as an argument
 * @return a hash with a constant salt  that is created in that function
 * using SHA_512 algorithm */
public class Hash {
    public String hashPass(String password){
        String salt = "abrakadabradlyatebia";
        String hashed = password;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(hashed.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16)).substring(1);
            }
            hashed = sb.toString();
        }

        catch (NoSuchAlgorithmException | UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return hashed;
    }
}
