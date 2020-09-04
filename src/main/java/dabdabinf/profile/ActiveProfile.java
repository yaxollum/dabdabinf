package dabdabinf.profile;

public class ActiveProfile
{
    private Profile activeProfile;
    public ActiveProfile(Profile ap)
    {
        activeProfile=ap;
    }
    public void set(Profile ap)
    {
        activeProfile=ap;
    }
    public Profile get()
    {
        return activeProfile;
    }
}
