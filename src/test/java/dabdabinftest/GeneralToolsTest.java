package dabdabinftest;

import org.junit.Assert;
import org.junit.Test;
import dabdabinf.tools.General;
/*
import dabdabinf.blockchain.Blockchain;
import dabdabinf.profile.ProfileManager;
import dabdabinf.profile.Profile;
import dabdabinf.transaction.TransactionManager;
import dabdabinf.application.Messenger;
import dabdabinf.application.CommandProcessor;
*/

public class GeneralToolsTest
{
    @Test
    public void shouldReadEntireResource()
    {
        Assert.assertEquals("This is an example resource.\nSecond line!",
            General.readEntireResource("/example_resource"));
    }
}
