package dabdabinf.application;

import dabdabinf.blockchain.Blockchain;
import dabdabinf.block.*;
import dabdabinf.profile.*;
import dabdabinf.transaction.*;
import dabdabinf.miner.Miner;
import dabdabinf.tools.Rsa;
import java.security.KeyPair;

public class CommandProcessor
{
    private Blockchain blockchain;
    private ProfileManager profileManager;
    private ActiveProfile activeProfile;
    private ProfileExporter profileExporter;
    private TransactionManager transactionManager;
    private Messenger messenger;
    private Miner miner;
    
    public CommandProcessor(Blockchain bc,
        ProfileManager pm,
        ActiveProfile ap,
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
    
    public void process(Command cmd) throws BlockMinerNotAddedException,BlockNumberInvalidException
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
                    blocksCmd();
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
                case "profile": // show infomation about a profile
                    profileCmd(cmd);
                    break;
                case "generate": // generate a new profile
                    generateCmd(cmd);
                    break;
                case "unprocessed": // list unprocessed transactions
                    messenger.listUnprocessed(transactionManager);
                    break;
                case "full-import": // import both public and private key
                    fullImportCmd(cmd);
                    break;
                case "import": // import only public key
                    importCmd(cmd);
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
        if(i>=0 && i<blockchain.length())
        {
            messenger.printBlock(blockchain.getBlock(i),transactionManager.getBlockTransactions(i));
        }
        else
        {
            messenger.blockOutOfRange();
        }
    }

    private void blocksCmd()
    {
        for(int i=0;i<blockchain.length();++i)
        {
            messenger.printBlock(blockchain.getBlock(i),transactionManager.getBlockTransactions(i));
            messenger.printDashedLine();
        }
    }

    private void mineCmd(Command cmd) throws BlockMinerNotAddedException,BlockNumberInvalidException
    {
        String transactionData=transactionManager.getTransactionData();
        Block newBlock=miner.mine(activeProfile.get(),transactionData,blockchain.getBlock(blockchain.length()-1));
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
            activeProfile.set(switchProfile);
            transactionManager.discardUnprocessed(); 
            // discard unprocessed transactions from old active profile
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
            profileManager.addProfile(newProfile);
        }
        else
        {
            messenger.profileExists(newProfileName);
        }
    }

    private void profileCmd(Command cmd) throws NotEnoughArgumentsException
    {
        String lookup=cmd.getArgument(1);
        Profile lookupProfile=profileManager.findProfile(lookup);
        if(lookupProfile!=null)
        {
            messenger.printProfile(lookupProfile);
        }
        else
        {
            messenger.profileNotFound(lookup);
        }
    }
    
    private void fullImportCmd(Command cmd) throws NotEnoughArgumentsException
    {   
        String newProfileName=cmd.getArgument(1);
        String privateKeyBase64=cmd.getArgument(2);
        if(profileManager.findProfile(newProfileName)==null)
        {
            Profile newProfile=new Profile(newProfileName,Rsa.keyPairFromPrivateKeyString(privateKeyBase64));
            profileExporter.export(newProfile);
            profileManager.addProfile(newProfile);
        }
        else
        {
            messenger.profileExists(newProfileName);
        }
    }

    private void importCmd(Command cmd) throws NotEnoughArgumentsException
    {   
        String newProfileName=cmd.getArgument(1);
        String publicKeyBase64=cmd.getArgument(2);
        if(profileManager.findProfile(newProfileName)==null)
        {
            Profile newProfile=new Profile(newProfileName,
                new KeyPair(Rsa.base64ToPublic(publicKeyBase64),null));
            profileExporter.export(newProfile);
            profileManager.addProfile(newProfile);
        }
        else
        {
            messenger.profileExists(newProfileName);
        }
    }
}
