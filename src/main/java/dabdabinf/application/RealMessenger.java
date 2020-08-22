package dabdabinf.application;

import java.io.PrintStream;
import dabdabinf.blockchain.Blockchain;
import dabdabinf.profile.Profile;
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
        System.exit(0);
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
        stdout.println("Profile "+name+" not found!");
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
            (activeProfile.keys.getPrivate()==null)?'$':'#');
    }
}
