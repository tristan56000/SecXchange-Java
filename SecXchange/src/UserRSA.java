import org.json.JSONObject;
import org.json.JSONTokener;

import javax.crypto.Cipher;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.security.*;

import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import java.security.spec.RSAPublicKeySpec;
import java.security.spec.RSAPrivateKeySpec;

/**
 * Represents a user of RSA (asymetric protocol used to encrypt/decrypt messages)
 * @author Tristan Guerin
 * @version 1.0
 */
public class UserRSA {

    /**
     * Name of the client user
     */
    private String nameUser;

    /**
     * RSA private key
     */
    private RSAPrivateKey privateKey;

    /**
     * RSA public key
     */
    private RSAPublicKey publicKey;

    /**
     * KeyFactory used to generate keys
     */
    private KeyFactory keyFactory;

    /**
     * Constructs objects from class UserRSA
     * @param publicKey public key of the user
     * @param privateKey private key of the user
     */
    public UserRSA(RSAPublicKeySpec publicKey, RSAPrivateKeySpec privateKey){
        try {
            this.keyFactory = KeyFactory.getInstance("RSA");
            this.publicKey = (RSAPublicKey) this.keyFactory.generatePublic(publicKey);
            this.privateKey = (RSAPrivateKey) this.keyFactory.generatePrivate(privateKey);
        }catch (Exception e){
            this.publicKey = null;
            this.privateKey = null;
        }
    }

    /**
     * Encrypts a string using RSA asymetric protocol
     * @param plainText string to encrypt
     * @param key key used to encrypt
     * @return string encrypted
     * @throws Exception
     */
    public static String encrypt(String plainText, RSAKey key) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, (Key) key);
        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes("UTF8"));
        return Base64.getEncoder().encodeToString(cipherText);
    }

    /**
     * Decrypts a string using RSA asymetric protocol
     * @param cipherText string to decrypt
     * @param key key used to decrypt
     * @return string decrypted
     * @throws Exception
     */
    public static String decrypt(String cipherText, RSAKey key) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);
        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, (Key) key);
        return new String(decriptCipher.doFinal(bytes), "UTF8");
    }

    /**
     * Gets the public key
     * @return RSA key
     */
    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * Sets the public key
     * @param publicKey public key to set
     * @throws InvalidKeySpecException
     */
    public void setPublicKey(RSAPublicKeySpec publicKey) throws InvalidKeySpecException {
        this.publicKey = (RSAPublicKey) this.keyFactory.generatePublic(publicKey);
    }

    /**
     * Gets the private key
     * @return RSA key
     */
    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    /**
     * Sets the private key
     * @param privateKey private key to set
     * @throws InvalidKeySpecException
     */
    public void setPrivateKey(RSAPrivateKeySpec privateKey) throws InvalidKeySpecException {
        this.privateKey = (RSAPrivateKey) this.keyFactory.generatePrivate(privateKey);
    }

    /**
     * Gets the name of the user
     * @return name
     */
    public String getNameUser() {
        return nameUser;
    }

    /**
     * Sets the user name
     * @param nameUser name to set
     */
    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    /**
     * Load a string from a text input stream
     *
     * @param in an input stream
     * @return the string loaded from the input stream
     *
     * @throws IOException if an error occurs while accessing the input stream
     * @pre {@code in != null}
     * @post {@code result != null}
     */
    public String loadString(InputStream in) throws IOException{
        assert in != null : "Precondition violated";
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = reader.readLine();
        while (line != null) {
            builder.append(line).append("\n");
            line = reader.readLine();
        }String str = builder.toString();
        assert str != null : "Postcondition violated";
        return str;
    }



    /**
     * Gets the public key of the given username
     * @param username username to search from
     * @return public key
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public RSAPublicKey getPublicKey(String username) throws NoSuchAlgorithmException, InvalidKeySpecException {
        RSAPublicKeySpec publicKeySpec = null;
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        try {
            File file1 = new File("public_RSA_keys.json");
            InputStream in1 = new FileInputStream(file1);

            JSONTokener token1 = new JSONTokener(this.loadString(in1));
            JSONObject object1 = new JSONObject(token1);
            JSONObject user = object1.getJSONObject(username);
            BigInteger modulus = new BigInteger(user.getString("modulus"));
            BigInteger exponent = new BigInteger(String.valueOf(user.getInt("exponent")));

            publicKeySpec = new RSAPublicKeySpec(modulus,exponent);
        }catch (Exception e){
        }finally {
            return (RSAPublicKey) keyFactory.generatePublic(publicKeySpec);
        }
    }

    /**
     * Inner class ImagePanel
     */
    static class ImagePanel extends JComponent {

        /**
         * Image
         */
        private Image image;

        /**
         * Constructs objects from inner class ImagePanel
         * @param image
         */
        public ImagePanel(Image image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }

}
