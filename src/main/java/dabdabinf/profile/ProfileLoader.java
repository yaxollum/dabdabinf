package dabdabinf.profile;

import java.io.File;
import java.security.KeyPair;
import java.util.List;
import java.util.ArrayList;
import dabdabinf.tools.Rsa;

public class ProfileLoader
{
    List<Profile> load()
    {
        List<Profile> profiles=new ArrayList<Profile>();
              
        File profileBigFolder = new File("profiles");
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
	        
	        KeyPair kp=Rsa.loadKeyPair(publicFile,privateFile);
	        Profile prof=new Profile(profileName,kp);
	        
	        profiles.add(prof);
	    }
	    return profiles;
    }
}
