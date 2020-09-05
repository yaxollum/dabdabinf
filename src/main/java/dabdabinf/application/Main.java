package dabdabinf.application;

import java.util.Scanner;
import java.util.List;
import java.io.File;
import dabdabinf.blockchain.Blockchain;
import dabdabinf.blockchain.RealBlockchain;
import dabdabinf.blockchain.BlockchainLoader;
import dabdabinf.blockchain.BlockchainValidator;
import dabdabinf.block.BlockInvalidException;
import dabdabinf.block.SplitBlockDataException;
import dabdabinf.profile.*;
import dabdabinf.transaction.*;
import dabdabinf.miner.Miner;

public class Main
{
	public static void main(String[] args) throws BlockInvalidException,SplitBlockDataException,BlockMinerNotAddedException
	{
		Scanner input=new Scanner(System.in);
		Blockchain blockchain=new RealBlockchain(BlockchainLoader.load());
        BlockchainValidator.validate(blockchain);

		ProfileManager profileManager=new RealProfileManager(ProfileLoader.load());
        ActiveProfile activeProfile=new ActiveProfile(profileManager.findProfile("example"));
		
		TransactionManager transactionManager=new RealTransactionManager(activeProfile,blockchain,profileManager);
		
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
		
		messenger.welcome();
		while(true)
		{
            messenger.prompt(activeProfile.get());
            if(!input.hasNextLine()) break;
			String cmd=input.nextLine();
			cp.process(new Command(cmd));
		}
	}
}
