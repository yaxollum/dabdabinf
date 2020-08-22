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
            if(name.equals(p.name)) return p;
        }
        return null;
    }

    public void listProfiles()
    {
        for(int i=0;i<profiles.size();++i)
        {
            System.out.println(profiles.get(i).name);
        }
    }
}
