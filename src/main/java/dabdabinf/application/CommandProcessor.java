package dabdabinf.application;

import dabdabinf.blockchain.Blockchain;
import dabdabinf.profile.*;
import dabdabinf.transaction.*;

public class CommandProcessor
{
    private Blockchain blockchain;
    private ProfileManager profileManager;
    private Profile activeProfile;
    private TransactionManager transactionManager;
    private Messenger messenger;
    
    public CommandProcessor(Blockchain bc,
        ProfileManager pm,
        Profile ap,
        TransactionManager tm,
        Messenger m)
    {
        blockchain=bc;
        profileManager=pm;
        activeProfile=ap;
        transactionManager=tm;
        messenger=m;
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
                //transactionManager.mine(blockchain);
                break;
            case "send":
                String to=args[1];
                int amount=Integer.parseInt(args[2]);
                Profile toProfile=profileManager.findProfile(to);
                if(toProfile!=null) Sender.newTransaction(activeProfile,toProfile,amount);
                else messenger.profileNotFound(to);
                break;
            case "balance":
               String lookup=args[1];
               Profile lookupProfile=profileManager.findProfile(lookup);
               messenger.printBalance(lookupProfile,transactionManager);
               break;
            default:
                messenger.cmdNotFound(cmd);
        }
    }
}
