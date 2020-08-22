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
    private TransactionManager transactionManager;
    private Messenger messenger;
    private Miner miner;
    
    public CommandProcessor(Blockchain bc,
        ProfileManager pm,
        Profile ap,
        TransactionManager tm,
        Messenger _messenger,
        Miner _miner)
    {
        blockchain=bc;
        profileManager=pm;
        activeProfile=ap;
        transactionManager=tm;
        messenger=_messenger;
        miner=_miner;
    }
    
    public void process(String[] args)
    {
        String cmd=args[0];
        switch(cmd)
        {
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
            default:
                messenger.cmdNotFound(cmd);
        }
    }
}
