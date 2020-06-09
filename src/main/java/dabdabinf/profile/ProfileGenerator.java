package dabdabinf.profile;

import java.security.KeyPair;
import java.io.File;
import java.util.List;
import dabdabinf.tools.Rsa;

public class ProfileGenerator
{
    public static Profile generate(String name)
    {
            KeyPair newKeys=Rsa.generateKeyPair();
            Profile newProfile=new Profile(name,newKeys);
          return newProfile; 
    }
}
