package dabdabinf.application;

import java.io.PrintStream;
import dabdabinf.block.Block;
import dabdabinf.profile.Profile;
import dabdabinf.profile.ProfileLoader;
import dabdabinf.profile.ProfileManager;
import dabdabinf.transaction.TransactionManager;
import dabdabinf.transaction.BlockTransactions;
import dabdabinf.tools.General;
import dabdabinf.blockchain.BlockchainLoader;

public class RealMessenger implements Messenger
{
    private PrintStream stdout;

    public RealMessenger(PrintStream output)
    {
        stdout=output;
    }
    public void welcome()
    {
        stdout.println("dabdabinf - the future of useless software\nType \"help\" for more information.");
    }
    public void help()
    {
        stdout.printf("Where everything is stored:\nThe blockchain is stored in the folder %s\nThe profiles are stored in the folder %s\n\nThe prompt:\nSimilar to many Linux shell prompts, the prompt is in the format: ACTIVE_PROFILE@dabdabinf SYMBOL\nwhere ACTIVE_PROFILE is the name of the active profile, and SYMBOL is '$' when the active profile only has a public key, '#' when the active profile also has a private key.\n\nList of all the commands:\n%s\n",
            BlockchainLoader.getBlockchainStoragePath(),
            ProfileLoader.getProfileStoragePath(),
            General.readEntireResource("/command_list"));
    }
    public void exit()
    {
        stdout.println("See you next time ...");
    }
    public void printBlock(Block block,BlockTransactions bt)
    {
        stdout.println(block);
        stdout.print(bt);
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
    public void printProfile(Profile p)
    {
        stdout.print(p.getProfileReport());
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
    public void printDashedLine()
    {
        stdout.println("------------------------------------------------------------");
    }
}
