package dabdabinf.profile;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.security.*;

public interface ProfileManager
{
    public void listProfiles();
    public void addProfile(Profile p);
    public Profile findProfile(String name);
}
