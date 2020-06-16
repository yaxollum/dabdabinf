package dabdabinf.application;

import java.util.Scanner;
import java.io.File;
import dabdabinf.blockchain.Blockchain;
import dabdabinf.blockchain.RealBlockchain;
import dabdabinf.blockchain.BlockchainLoader;
import dabdabinf.profile.*;
import dabdabinf.transaction.*;

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
		//blockchain.load();
		/*if(blockchain.verify())
		{
		    System.out.printf("%d blocks successfully loaded and verified!\n",blockchain.length());
		}*/
/*		
		ProfileManager profileManager=new RealProfileManager();
	        ProfileChooser profileChooser=new ProfileChooser();	
		Profile activeProfile=profileChooser.choose();
		
		TransactionManager transactionManager=new RealTransactionManager();
		
		Messenger messenger=new RealMessenger();
		
		CommandProcessor cp=new CommandProcessor(blockchain,
		    profileManager,
		    activeProfile,
		    transactionManager,
		    messenger);
		
		while(true)
		{
			System.out.print("> ");
			String cmd=input.nextLine();
			cp.process(cmd.split(" "));
		}
    */
	}
}
