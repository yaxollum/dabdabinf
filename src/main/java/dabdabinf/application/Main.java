package dabdabinf.application;

import java.util.Scanner;
import java.util.List;
import java.io.File;
import dabdabinf.blockchain.Blockchain;
import dabdabinf.blockchain.RealBlockchain;
import dabdabinf.blockchain.BlockchainLoader;
import dabdabinf.profile.*;
import dabdabinf.transaction.*;
import dabdabinf.miner.Miner;

public class Main
{
	///static String welcomeMessage, helpMessage;
	static Scanner input;
	/*
	private static void initializeMessages()
	{
		welcomeMessage=General.readEntireFile(new File("messages/welcomeMessage"));
		helpMessage=General.readEntireFile(new File("messages/helpMessage"));
	}
	
	private static Profile chooseProfile(ProfileManager pm)
	{
	    System.out.println("Existing profiles\n----------");
		pm.listProfiles();
		
		System.out.print("Please choose a profile, or create a new one by specifying a new name: ");
	    String name=input.next();
	    
	    return pm.findProfile(name,true);
	}
	*/
	public static void main(String[] args)
	{
		//initializeMessages();
		//System.out.println(welcomeMessage);
		input=new Scanner(System.in);
		Blockchain blockchain=new RealBlockchain(BlockchainLoader.load());
		/*if(blockchain.verify())
		{
		    System.out.printf("%d blocks successfully loaded and verified!\n",blockchain.length());
		}*/

		ProfileManager profileManager=new RealProfileManager(ProfileLoader.load());
        Profile activeProfile=profileManager.findProfile("example");
		
		TransactionManager transactionManager=new RealTransactionManager(activeProfile,blockchain);
		
		Messenger messenger=new RealMessenger(System.out);

        Miner miner=new Miner(messenger);

        ProfileExporter profileExporter=new ProfileExporter(messenger);
		
		CommandProcessor cp=new CommandProcessor(blockchain,
		    profileManager,
		    activeProfile,
            profileExporter,
		    transactionManager,
		    messenger,
            miner);
		
		while(true)
		{
            messenger.prompt(activeProfile);
            if(!input.hasNextLine()) break;
			String cmd=input.nextLine();
			cp.process(cmd.split(" "));
		}
	}
}
