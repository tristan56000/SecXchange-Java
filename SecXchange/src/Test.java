import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

public class Test {


    public static void main(String args[]) throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();

        PublicKey pub = kp.getPublic();
        PrivateKey pvt = kp.getPrivate();

        RSAPublicKey p = (RSAPublicKey) pub;
        RSAPrivateKey pv = (RSAPrivateKey) pvt;

        System.out.println(p.getModulus());
        System.out.println(p.getPublicExponent());

        System.out.println(pv.getModulus());
        System.out.println(pv.getPrivateExponent());

        System.out.println();

        String mdpTg ="sk_)5z5b6yE2)<i6";
        String mdpGp = "ERX9A{=fPufVw2]";
        String mdpEtud1 = "s@dFq:H@'MKV6g`X";
        String mdpEtud2 ="vV/J&pbdZD?jL?TR";



        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] bytes = md.digest(mdpEtud2.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            if (i % 4 == 0) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            sb2.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        String hashedPassword = sb.toString();
        System.out.println(hashedPassword);
        String hashedPassword2 = sb2.toString();
        System.out.println(hashedPassword2);

        /*if(!hashedPassword2.equals(hashPassword)){
            throw new Exception("Invalid Password");
        }
        String modulusbis = (userbis.getString("modulus"));
        String exponentbis = (userbis.getString("exponent"));

         */
        SecretKeySpec sec = new SecretKeySpec(hashedPassword.getBytes(),"AES");

        //publicKeySpec = new RSAPublicKeySpec(modulus,exponent);

        String encryptM = AES.encrypt(String.valueOf(pv.getModulus()),sec);
        String encryptE = AES.encrypt(String.valueOf(pv.getPrivateExponent()),sec);

        System.out.println();
        System.out.println(encryptM);
        System.out.println(encryptE);

        //String tt = "r0LiOomuS265q03cxyFqd8INgjzu9NJZfGR+9UIU6tGM6wM7nyN9tP72HgP7ESbXObNv0IvGuW6jUWXgE3IZZQv+8h71LtFBRaxHsVYm4wSt3Igrh4TdsXAZ/5N/7xBuSvS9QlBz6plAyha5lgt/Atnd3btJOb+9emeuc/8pwgsG60t6H8E11TcAXaYgJKA7dLgTKAw9yoiWHhtjn1oSkEbDrOg7dQvU37nJnDyfxlW0RMAAYqISQsePjNJ/y5Lw+XqBgSwElE3uGa2tdd8TP6goAaMv8PKtzMjlI8Gw7EIArMiJB3exuCfBSb+sjFmMQGD5a9LmH4z6O5g5Wu6Rb+IXLPL7yY2XICX0RgjuBstDt3QJyQO5/Ajhwv6ob295lQgKzxqBtBSZRQU9LkgjH0N4BOHv6zlqybS9i7c/psvvPt/JAwIQJkdF0gcAZxFSZ+VCd0HbOaWm4ntSTOvDkchiEj5OHa2MUdfKdObMsTfgCWADL32pXVyKQkXbF+8R1A+9ZoNFi1AchdG0FvZIMeAJjPMiZXkIRmWCsmQwgkeeA5if96ZtAHr1qbeFNO9DQsx2uxAwJl9JT/03qqcpuVxhpaLuOU4nOPVdMNi5DD1as6TrvhUtfeA/KadJWZDon6ejubhwZBPipyDIjwdjM1FVSbidF/X2//O79GNduRmBXxG2H+2I1MlL5LJKJZJ93YFXphwJ+AWVQIzjNXNlUrYXCjhC3rWCd9ci2MZ1A6taKzxBaIzak0twtuDz5YKo6CSuSgEGTQ4hyppxtU34kdvMHO/xQY6oPNdQWOnl5nWu2hFm/eiZaiMH7qVL9luR";

        //String dec = AES.decrypt(tt,sec);
        //System.out.println(dec);



    }
}
