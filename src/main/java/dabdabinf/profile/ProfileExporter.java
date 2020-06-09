package dabdabinf.profile;

import java.security.KeyPair;
import java.io.File;
import java.util.List;
import dabdabinf.tools.Rsa;
import dabdabinf.application.Messenger;

public class ProfileExporter
{
    public static void export(Profile newProfile,Messenger messenger)
    {
        String profilePath="profiles/"+newProfile.name;
        
        File profileDir=new File(profilePath);
        profileDir.mkdir();
        
        File publicFile=new File(profilePath+"/public");
        File privateFile=new File(profilePath+"/private");
        
        Rsa.exportKeyPair(publicFile,privateFile,newProfile.keys);
        
        messenger.profileCreated(newProfile.name); 
    }
}
