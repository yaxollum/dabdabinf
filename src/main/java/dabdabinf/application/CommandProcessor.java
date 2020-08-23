package dabdabinf.application;

import dabdabinf.blockchain.Blockchain;
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
    
    public void process(String[] args)
    {
        if(args.length==0) return;
        String cmd=args[0];
        switch(cmd)
        {
            case "":
                break; // ignore empty command
            case "help":
                messenger.help();
                break;
            case "exit":
                messenger.exit();
            case "blocks":
                messenger.printBlocks(blockchain);
                break;
            case "block":
                try
                {
                    int i=Integer.parseInt(args[1]);
                    messenger.printBlock(blockchain,i);
                }
                catch(NumberFormatException e)
                {
                    messenger.expectedNumber();
                }
                break;
            case "mine":
                String transactionData=transactionManager.getTransactionData();
                miner.mine(activeProfile,transactionData,blockchain.getBlock(blockchain.length()-1));
                transactionManager.processAll();
                break;
            case "send":
                String to=args[1];
                int amount=Integer.parseInt(args[2]);
                Profile toProfile=profileManager.findProfile(to);
                if(toProfile!=null) transactionManager.newTransaction(toProfile,amount);
                else messenger.profileNotFound(to);
                break;
            case "balance":
               String lookup=args[1];
               Profile lookupProfile=profileManager.findProfile(lookup);
               messenger.printTransactionReport(lookupProfile,transactionManager);
               break;
            case "switch": // change active profile
                String switchProfileName=args[1];
                Profile switchProfile=profileManager.findProfile(switchProfileName);
                if(switchProfile!=null)
                {
                    activeProfile.replaceWith(switchProfile);
                }
                else
                {
                    messenger.profileNotFound(switchProfileName);
                }
                break;
            case "profiles": // list all profiles
                messenger.listProfiles(profileManager);
                break;
            case "generate": // generate a new profile
                String newProfileName=args[1];
                if(profileManager.findProfile(newProfileName)==null)
                {
                    Profile newProfile=ProfileGenerator.generate(newProfileName);
                    profileExporter.export(newProfile);
                }
                else
                {
                    messenger.profileExists(newProfileName);
                }
                break;
            default:
                messenger.cmdNotFound(cmd);
        }
    }
}
