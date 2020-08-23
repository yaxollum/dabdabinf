package dabdabinftest;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.security.*;
import dabdabinf.profile.ProfileManager;
import dabdabinf.profile.Profile;

public class FakeProfileManager implements ProfileManager 
{
    int listProfilesCalled=0;
    int findProfileCalled=0;
    int addProfileCalled=0;

    public void listProfiles()
    {
        ++listProfilesCalled;
    }
    public Profile findProfile(String name)
    {
        ++findProfileCalled;
        return null;
    }
    public void addProfile(Profile p)
    {
        ++addProfileCalled;
    }
    public String getProfileList() 
    {
        return null;
    }
}
