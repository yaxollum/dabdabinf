package dabdabinf.application;

import dabdabinf.blockchain.Blockchain;
import dabdabinf.block.*;
import dabdabinf.profile.*;
import dabdabinf.transaction.*;
import dabdabinf.miner.Miner;

public class CommandProcessor
{
    private Blockchain blockchain;
    private ProfileManager profileManager;
    private Profile activeProfile;
    private ProfileExporter profileExporter;
    private TransactionManager transactionManager;
    private Messenger messenger;
    private Miner miner;
    
    public CommandProcessor(Blockchain bc,
        ProfileManager pm,
        Profile ap,
        ProfileExporter pe,
        TransactionManager tm,
        Messenger _messenger,
        Miner _miner)
    {
        blockchain=bc;
        profileManager=pm;
        activeProfile=ap;
        profileExporter=pe;
        transactionManager=tm;
        messenger=_messenger;
        miner=_miner;
    }
    
    public void process(Command cmd)
    {
        String cmdName=cmd.getName();
        try
        {
            switch(cmdName)
            {
                case "":
                    break; // ignore empty command
                case "help":
                    messenger.help();
                    break;
                case "exit":
                    messenger.exit();
                    System.exit(0); // exits from the application
                case "blocks":
                    messenger.printBlocks(blockchain);
                    break;
                case "block":
                    blockCmd(cmd);
                    break;
                case "mine":
                    mineCmd(cmd);
                    break;
                case "send":
                    sendCmd(cmd);
                    break;
                case "balance":
                    balanceCmd(cmd);
                    break;
                case "switch": // change active profile
                    switchCmd(cmd);
                    break;
                case "profiles": // list all profiles
                    messenger.listProfiles(profileManager);
                    break;
                case "generate": // generate a new profile
                    generateCmd(cmd);
                    break;
                default:
                    messenger.cmdNotFound(cmdName);
            }
        }
        catch(ExpectedNumberException e)
        {
            messenger.expectedNumber();
        }
        catch(NotEnoughArgumentsException e)
        {
            messenger.notEnoughArguments();
        }
    }

    private void blockCmd(Command cmd) throws ExpectedNumberException, NotEnoughArgumentsException
    {
        int i=cmd.getIntArgument(1);
        messenger.printBlock(blockchain,i);
    }

    private void mineCmd(Command cmd)
    {
        String transactionData=transactionManager.getTransactionData();
        Block newBlock=miner.mine(activeProfile,transactionData,blockchain.getBlock(blockchain.length()-1));
        if(newBlock!=null)
        {
            transactionManager.processAll();
            blockchain.addBlock(newBlock);
            BlockExporter.export(newBlock);
            messenger.blockExported();
        }
        else
        {
            messenger.failedToMine();
        }
    }

    private void sendCmd(Command cmd) throws ExpectedNumberException, NotEnoughArgumentsException
    {
        String to=cmd.getArgument(1);
        int amount=cmd.getIntArgument(2);
        Profile toProfile=profileManager.findProfile(to);
        if(toProfile!=null) transactionManager.newTransaction(toProfile,amount);
        else messenger.profileNotFound(to);
    }

    private void balanceCmd(Command cmd) throws NotEnoughArgumentsException
    {
        String lookup=cmd.getArgument(1);
        Profile lookupProfile=profileManager.findProfile(lookup);
        messenger.printTransactionReport(lookupProfile,transactionManager);
    }

    private void switchCmd(Command cmd) throws NotEnoughArgumentsException
    {
        String switchProfileName=cmd.getArgument(1);
        Profile switchProfile=profileManager.findProfile(switchProfileName);
        if(switchProfile!=null)
        {
            activeProfile.replaceWith(switchProfile);
        }
        else
        {
            messenger.profileNotFound(switchProfileName);
        }
    }

    private void generateCmd(Command cmd) throws NotEnoughArgumentsException
    {
        String newProfileName=cmd.getArgument(1);
        if(profileManager.findProfile(newProfileName)==null)
        {
            Profile newProfile=ProfileGenerator.generate(newProfileName);
            profileExporter.export(newProfile);
        }
        else
        {
            messenger.profileExists(newProfileName);
        }
    }
}
