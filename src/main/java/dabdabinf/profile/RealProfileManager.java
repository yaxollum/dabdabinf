package dabdabinf.profile;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.security.*;

public class RealProfileManager implements ProfileManager
{
    private List<Profile> profiles;
    
    public RealProfileManager(List<Profile> lp)
    {
        profiles=lp;
    }
    
    public void addProfile(Profile p)
    {
        if(findProfile(p.name)==null)
        {
           profiles.add(p); 
        }
    }
    public Profile findProfile(String name)
    {
        for(Profile p : profiles)
        {
            if(name.equals(p.name)) return new Profile(p); //returs copy of found profile
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
}
