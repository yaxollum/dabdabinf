package dabdabinf.profile;

import java.security.*;
import dabdabinf.tools.Rsa;

public class Profile
{
    public String name;
    public KeyPair keys;
    public Profile(){}
    public Profile(String n,KeyPair k)
    {
        name=n;
        keys=k;
    }
    public String sign(String message)
    {
        return Rsa.sign(message,keys.getPrivate());
    }
    public String publicKeyBase64()
    {
        return Rsa.publicToBase64(keys.getPublic());
    }
    public boolean hasPrivateKey()
    {
        return keys.getPrivate()!=null;
    }
    public void replaceWith(Profile other)
    {
        name=other.name;
        keys=other.keys;
    }
}
