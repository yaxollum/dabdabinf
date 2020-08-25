package dabdabinf.application;

import java.io.PrintStream;
import dabdabinf.blockchain.Blockchain;
import dabdabinf.profile.Profile;
import dabdabinf.profile.ProfileManager;
import dabdabinf.transaction.TransactionManager;

public class RealMessenger implements Messenger
{
    private PrintStream stdout;

    public RealMessenger(PrintStream output)
    {
        stdout=output;
    }
    public void help()
    {
        stdout.println("(Help tips here)");
    }
    public void exit()
    {
        stdout.println("See you next time ...");
    }
    public void printBlock(Blockchain blockchain,int i)
    {
        stdout.println(blockchain.blockToString(i));
    }
    public void printBlocks(Blockchain blockchain)
    {
        stdout.println(blockchain);
    }
    public void cmdNotFound(String cmd)
    {
        stdout.println("Command \""+cmd+"\" not found!");
    }
    public void blockOutOfRange()
    {
        stdout.println("Block out of range!");
    }
    public void expectedNumber()
    {
        stdout.println("Expected a number!");
    }
    public void profileNotFound(String name)
    {
        stdout.printf("Profile \"%s\" not found!\n",name);
    }
    public void profileCreated(String name)
    {
        stdout.println("A new profile named "+name+" has been created!");
    }
    public void printTransactionReport(Profile lookupProfile, TransactionManager tm)
    {
        stdout.println(tm.getTransactionReport(lookupProfile));
    }
    public void exceptionCaught(Exception e)
    {
        stdout.println(e);
    }
    public void prompt(Profile activeProfile)
    {
        stdout.printf("%s@dabdabinf %c ",
            activeProfile.name,
            activeProfile.hasPrivateKey()?'#':'$');
    }
    public void noPrivateKey(Profile p)
    {
        stdout.printf("No private key found for profile \"%s\"!\n",p.name);
    }
    public void listProfiles(ProfileManager pm)
    {
        stdout.print(pm.getProfileList());
    }
    public void profileExists(String name)
    {
        stdout.printf("Profile \"%s\" already exists!\n",name);
    }
    public void failedToMine()
    {
        stdout.println("Failed to mine a new block.");
    }
    public void successfullyMined(double timeInSeconds)
    {
        stdout.printf("Mined a new block in %f seconds!\n", timeInSeconds);
    }
    public void currentlyMining()
    {
        stdout.println("Working hard to mine a new block ...");
    }
    public void blockExported()
    {
        stdout.println("Exported the new block to a file!");
    }
    public void notEnoughArguments()
    {
        stdout.println("Not enough arguments.");
    }
    public void listUnprocessed(TransactionManager tm)
    {
        stdout.print(tm.getUnprocessed());
    }
}
