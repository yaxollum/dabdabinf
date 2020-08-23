package dabdabinf.profile;

import java.security.KeyPair;
import java.io.File;
import java.util.List;
import dabdabinf.tools.Rsa;
import dabdabinf.application.Messenger;

public class ProfileExporter
{
    private Messenger messenger;
    public void export(Profile newProfile)
    {
        String userHomeDir=System.getProperty("user.home");
        File profileBigFolder = new File(userHomeDir+"/.dabdabinf/profiles");
        File profileDir=new File(profileBigFolder,newProfile.name);
        profileDir.mkdirs();
        
        File publicFile=new File(profileDir,"public");
        File privateFile=new File(profileDir,"private");
        
        Rsa.exportKeyPair(publicFile,privateFile,newProfile.keys);
        
        messenger.profileCreated(newProfile.name); 
    }
    public ProfileExporter(Messenger m)
    {
        messenger=m;
    }
}
