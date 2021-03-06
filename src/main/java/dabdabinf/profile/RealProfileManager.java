package dabdabinf.profile;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.security.*;
import dabdabinf.tools.General;

public class RealProfileManager implements ProfileManager
{
    private List<Profile> profiles;
    
    public RealProfileManager(List<Profile> lp)
    {
        profiles=lp;
    }
    
    public boolean addProfile(Profile p)
    {
        if(findProfile(p.name)==null)
        {
           profiles.add(p); 
           return true;
        }
        return false;
    }
    public Profile findProfile(String name)
    {
        for(Profile p : profiles)
        {
            if(name.equals(p.name)) return p; 
        }
        return null;
    }
    public Profile findProfileWithPublicKey(String publicKeyString)
    {
        for(Profile p : profiles)
        {
            if(publicKeyString.equals(p.getPublicKeyBase64())) return p;
        }
        return null;
    }

    public String getProfileList()
    {
        String profilesString="";
        for(Profile p : profiles)
        {
            profilesString+=p.name+'\n';
        }
        return profilesString;
    }
    
    public Profile createTmpProfile(PublicKey pk) // create a temporary profile for a public key (not exported to a file)
    {
        while(true)
        {
            Profile tmpProfile=new Profile("tmp"+General.randomDigitString(5),new KeyPair(pk,null));
            if(addProfile(tmpProfile)) return tmpProfile;
        }
    }
}
