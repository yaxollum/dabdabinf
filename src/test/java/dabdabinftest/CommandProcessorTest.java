package dabdabinftest;

import org.junit.Assert;
import org.junit.Test;
import dabdabinf.blockchain.Blockchain;
import dabdabinf.block.BlockNumberInvalidException;
import dabdabinf.profile.ProfileManager;
import dabdabinf.profile.Profile;
import dabdabinf.profile.ActiveProfile;
import dabdabinf.profile.ProfileExporter;
import dabdabinf.transaction.BlockMinerNotAddedException;
import dabdabinf.transaction.TransactionManager;
import dabdabinf.application.Messenger;
import dabdabinf.application.CommandProcessor;
import dabdabinf.application.Command;
import dabdabinf.miner.Miner;

public class CommandProcessorTest
{
    @Test
    public void shouldCallHelp() throws BlockMinerNotAddedException,BlockNumberInvalidException
    {
        Blockchain fb=new FakeBlockchain();
        ProfileManager fpm=new FakeProfileManager();
        ActiveProfile activeProfile=new ActiveProfile(new Profile(null,null));
        TransactionManager ftm=new FakeTransactionManager();
        FakeMessenger fm=new FakeMessenger();
        Miner miner=new Miner(fm);
        ProfileExporter pe=new ProfileExporter(fm);
        
        CommandProcessor cp=new CommandProcessor(fb,fpm,activeProfile,pe,ftm,fm,miner);
        
        Command test1=new Command("help exit");
        cp.process(test1);
        Assert.assertEquals(1,fm.helpCalled);
        Assert.assertEquals(0,fm.exitCalled);
    }
}
