package dabdabinf.profile;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ProfileChooser
{
    private InputStream stdin;
    private PrintStream stdout;
    private ProfileManager pm;

    public Profile choose()
    {
        Scanner input=new Scanner(stdin);
        stdout.println("Existing profiles\n----------");
        pm.listProfiles();
        stdout.print("Please choose a profile, or create a new one by specifying a new name: ");
        String name=input.next();
        
        return pm.findProfile(name);
    }
}
