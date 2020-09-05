package dabdabinf.tools;

import java.security.*;
import java.security.spec.*;
import java.security.interfaces.*;
import java.util.Base64;
import java.io.File;

public class Rsa
{
    public static KeyPair generateKeyPair()
    {
        try
        {
            KeyPairGenerator gen=KeyPairGenerator.getInstance("RSA");
            gen.initialize(2048, new SecureRandom());
            return gen.generateKeyPair();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
    
    public static String sign(String plainText,PrivateKey privateKey)
    {
        try
        {
            Signature privateSig=Signature.getInstance("SHA256withRSA");
            privateSig.initSign(privateKey);
            privateSig.update(plainText.getBytes());

            byte[] signature = privateSig.sign();

            return Base64.getEncoder().encodeToString(signature);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public static boolean verify(String msg, String signature, PublicKey publicKey)
    {
        try
        {
            Signature publicSig=Signature.getInstance("SHA256withRSA");
            publicSig.initVerify(publicKey);
            publicSig.update(msg.getBytes());

            byte[] sigBytes = Base64.getDecoder().decode(signature);

            return publicSig.verify(sigBytes);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
            return false;
        }
    }
    
    public static KeyPair loadKeyPair(File publicKeyFile,File privateKeyFile)
    {
        try
        {
            String publicBytesBase64=General.readEntireFile(publicKeyFile);
            byte[] publicBytes=Base64.getDecoder().decode(publicBytesBase64);
            
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey publicKey = kf.generatePublic(new X509EncodedKeySpec(publicBytes));
            PrivateKey privateKey=null;
            
            if(privateKeyFile!=null)
            {
                String privateBytesBase64=General.readEntireFile(privateKeyFile);
                byte[] privateBytes=Base64.getDecoder().decode(privateBytesBase64);
                privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(privateBytes));
            }
            
            return new KeyPair(publicKey,privateKey);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
    
    public static void exportKeyPair(File publicKeyFile,File privateKeyFile,KeyPair kp)
    {
        String publicBytesBase64=publicToBase64(kp.getPublic());
        General.writeToFile(publicKeyFile,publicBytesBase64);
        
        if(kp.getPrivate()!=null)
        {
            byte[] privateBytes=kp.getPrivate().getEncoded();
            String privateBytesBase64=new String(Base64.getEncoder().encode(privateBytes));
            General.writeToFile(privateKeyFile,privateBytesBase64);
        }
    }
    
    public static String publicToBase64(PublicKey k)
    {
        byte[] publicBytes=k.getEncoded();
        return new String(Base64.getEncoder().encode(publicBytes));
    }

    public static String privateToBase64(PrivateKey k)
    {
        byte[] privateBytes=k.getEncoded();
        return new String(Base64.getEncoder().encode(privateBytes));
    }
    
    public static PublicKey base64ToPublic(String publicBytesBase64)
    {
        byte[] publicBytes=Base64.getDecoder().decode(publicBytesBase64);
        try
        {
            KeyFactory kf=KeyFactory.getInstance("RSA");
            return kf.generatePublic(new X509EncodedKeySpec(publicBytes));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public static String getFingerprint(String data) // the fingerprint is the SHA256 hash of the data, in uppercase hexadecimal form
    {
        byte[] hashBytes=General.sha256(data.getBytes());
        String fingerprint="";
        for(int i=0;i<hashBytes.length;++i) 
        {
            fingerprint+=String.format("%02X",hashBytes[i]);
        }
        return fingerprint;
    }

    public static KeyPair keyPairFromPrivateKeyString(String privateBytesBase64)
    {
        try
        {
            KeyFactory kf=KeyFactory.getInstance("RSA");
            byte[] privateBytes=Base64.getDecoder().decode(privateBytesBase64);
            PKCS8EncodedKeySpec pkcs=new PKCS8EncodedKeySpec(privateBytes);
            PrivateKey privateKey = kf.generatePrivate(pkcs);
            if(privateKey instanceof RSAPrivateCrtKey)
            {
                RSAPrivateCrtKey privateCrtKey=(RSAPrivateCrtKey) privateKey;
                RSAPublicKeySpec publicKeySpec=new RSAPublicKeySpec(privateCrtKey.getModulus(), privateCrtKey.getPublicExponent());   
                PublicKey publicKey = kf.generatePublic(publicKeySpec);
                return new KeyPair(publicKey,privateKey);
            }
            else
            {
                throw new IllegalArgumentException("Need an RSAPrivateCrtKey");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
}
