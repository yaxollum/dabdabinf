package dabdabinftest;

import org.junit.Assert;
import org.junit.Test;
import dabdabinf.blockchain.Blockchain;
import dabdabinf.profile.ProfileManager;
import dabdabinf.profile.Profile;
import dabdabinf.profile.ProfileExporter;
import dabdabinf.transaction.TransactionManager;
import dabdabinf.application.Messenger;
import dabdabinf.application.CommandProcessor;
import dabdabinf.miner.Miner;

public class CommandProcessorTest
{
    @Test
    public void shouldAnswerWithCmdNotFound()
    {
        Blockchain fb=new FakeBlockchain();
        ProfileManager fpm=new FakeProfileManager();
        Profile profile=new Profile();
        TransactionManager ftm=new FakeTransactionManager();
        FakeMessenger fm=new FakeMessenger();
        Miner miner=new Miner(fm);
        ProfileExporter pe=new ProfileExporter(fm);
        
        CommandProcessor cp=new CommandProcessor(fb,fpm,profile,pe,ftm,fm,miner);
        
        String[] test1={"help","exit"};
        cp.process(test1);
        Assert.assertEquals(fm.helpCalled,1);
        Assert.assertEquals(fm.exitCalled,0);
    }
}
