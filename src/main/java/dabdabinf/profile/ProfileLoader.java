package dabdabinf.profile;

import java.io.File;
import java.security.KeyPair;
import java.util.List;
import java.util.ArrayList;
import dabdabinf.tools.*;

public class ProfileLoader
{
    public static List<Profile> load()
    {
        List<Profile> profiles=new ArrayList<Profile>();
        String userHomeDir=System.getProperty("user.home"); 
        File profileBigFolder = new File(userHomeDir+"/.dabdabinf/profiles");
        profileBigFolder.mkdirs();
        
        File exampleProfile=new File(profileBigFolder,"example");
        if(!exampleProfile.exists())
        {
            exampleProfile.mkdir();
            General.writeToFile(new File(exampleProfile,"public"),General.readEntireResource("/example_public_key"));
        }

        File[] profileFolders = profileBigFolder.listFiles();

        for(File profileFolder : profileFolders)
        {
	        String profileName=profileFolder.getName();
	        File[] keyFiles=profileFolder.listFiles();
	        
	        File publicFile=null,privateFile=null;
	        for(File keyFile : keyFiles)
	        {
	            if(keyFile.getName().equals("public"))
	            {
	                publicFile=keyFile;
	            }
	            else if(keyFile.getName().equals("private"))
	            {
	                privateFile=keyFile;
	            }
	        }
            if(publicFile==null) continue; // every Profile must have a public key
	        
	        KeyPair kp=Rsa.loadKeyPair(publicFile,privateFile);
	        Profile prof=new Profile(profileName,kp);
	        
	        profiles.add(prof);
	    }
	    return profiles;
    }
}
