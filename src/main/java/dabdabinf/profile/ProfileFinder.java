package dabdabinf.profile;

import java.security.KeyPair;
import java.io.File;
import java.util.List;
import dabdabinf.tools.Rsa;

public class ProfileFinder
{
    public Profile findProfile(String name,boolean createIfNotFound,List<Profile> profiles)
    {
        for(int i=0;i<profiles.size();++i)
        {
            if(name.equals(profiles.get(i).name)) return profiles.get(i);
        }
        
        if(createIfNotFound)
        {
            KeyPair newKeys=Rsa.generateKeyPair();
            Profile newProfile=new Profile(name,newKeys);
            
            String profilePath="profiles/"+name;
            
            File profileDir=new File(profilePath);
            profileDir.mkdir();
            
            File publicFile=new File(profilePath+"/public");
            File privateFile=new File(profilePath+"/private");
            
            Rsa.exportKeyPair(publicFile,privateFile,newKeys);
            
            System.out.printf("A new profile named \"%s\" has been successfully created!\n",name);
            
            return newProfile;
        }
        else return null;
    }
}
