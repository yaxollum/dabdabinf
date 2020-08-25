package dabdabinftest;

import dabdabinf.application.Messenger;
import dabdabinf.blockchain.Blockchain;
import dabdabinf.profile.Profile;
import dabdabinf.profile.ProfileManager;
import dabdabinf.transaction.TransactionManager;

public class FakeMessenger implements Messenger
{
    int helpCalled=0;
    int exitCalled=0;
    int printBlockCalled=0;
    int printBlocksCalled=0;
    int cmdNotFoundCalled=0;
    int blockOutOfRangeCalled=0;
    int expectedNumberCalled=0;
    int profileCreatedCalled=0; 
    int profileNotFoundCalled=0;

    public void help()
    {
        ++helpCalled;
    }
    public void exit()
    {
        ++exitCalled;
    }
    public void printBlock(Blockchain blockchain,int i)
    {
        ++printBlockCalled;
    }
    public void printBlocks(Blockchain blockchain)
    {
        ++printBlocksCalled;
    }
    public void cmdNotFound(String cmd)
    {
        ++cmdNotFoundCalled;
    }
    public void blockOutOfRange()
    {
        ++blockOutOfRangeCalled;
    }
    public void expectedNumber()
    {
        ++expectedNumberCalled;
    }
    public void profileCreated(String name)
    {
        ++profileCreatedCalled;
    }
    public void profileNotFound(String name)
    {
        ++profileNotFoundCalled;
    }
    public void exceptionCaught(Exception e) {}
    public void printTransactionReport(Profile lp, TransactionManager tm) {}
    public void prompt(Profile activeProfile) {}
    public void noPrivateKey(Profile p) {}
    public void listProfiles(ProfileManager pm) {}
    public void profileExists(String name) {}
    public void failedToMine() {}
    public void successfullyMined(double timeInSeconds) {}
    public void currentlyMining() {}
    public void blockExported() {}
    public void notEnoughArguments() {}
    public void listUnprocessed(TransactionManager tm) {}
}
