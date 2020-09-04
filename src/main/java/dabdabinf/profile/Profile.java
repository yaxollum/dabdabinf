package dabdabinf.profile;

import java.security.*;
import dabdabinf.tools.Rsa;

public class Profile
{
    public final String name;
    public final KeyPair keys;

    public Profile(String n,KeyPair k)
    {
        name=n;
        keys=k;
    }
    /*
    public Profile(Profile other)
    {
        name=other.name;
        keys=other.keys;
    }
    */
    public String sign(String message)
    {
        return Rsa.sign(message,keys.getPrivate());
    }
    public String getPublicKeyBase64()
    {
        return Rsa.publicToBase64(keys.getPublic());
    }
    public boolean hasPrivateKey()
    {
        return keys.getPrivate()!=null;
    }
    public String getProfileReport()
    {
        String publicKeyString=getPublicKeyBase64();
        String fingerprint=Rsa.getFingerprint(publicKeyString);
        String report=String.format("Fingerprint: %s\n\nPublic key: %s\n",
            fingerprint,
            publicKeyString);
        if(hasPrivateKey())
        {
            report+=String.format("\nPrivate key: %s\n",Rsa.privateToBase64(keys.getPrivate()));
        }
        return report;
    }
}
