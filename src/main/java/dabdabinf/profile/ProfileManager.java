package dabdabinf.profile;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.security.*;

public interface ProfileManager
{
    public String getProfileList();
    public boolean addProfile(Profile p);
    public Profile findProfile(String name);
    public Profile findProfileWithPublicKey(String publicKeyString);
    public Profile createTmpProfile(PublicKey pk);
}
