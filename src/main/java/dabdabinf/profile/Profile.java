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
}
