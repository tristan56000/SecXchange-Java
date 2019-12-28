import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Represents the AES symetric protocol used to encrypt/decrypt messages
 * @author Tristan Guerin
 * @version 1.0
 */
public class AES {

    /**
     * Encrypts a string using AES symetric protocol
     * @param strToEncrypt string to encrypt
     * @param secretKeySpec secret key used to encrypt
     * @return string encrypted
     */
    public static String encrypt(String strToEncrypt, SecretKeySpec secretKeySpec){
        try{
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        }catch (Exception e){
            System.out.println("Error while encrypting: " + e.toString());
        }return null;
    }

    /**
     * Decrypts a string using AES symetric protocol
     * @param strToDecrypt string to decrypt
     * @param secretKeySpec secret key used to decrypt
     * @return string decrypted
     */
    public static String decrypt(String strToDecrypt, SecretKeySpec secretKeySpec){
        try{
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }catch (Exception e){
            System.out.println("Error while decrypting: " + e.toString());
        }return null;
    }
}
