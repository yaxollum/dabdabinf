package dabdabinf.blockchain;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import dabdabinf.block.Block;
import dabdabinf.tools.General;

public class BlockchainLoader
{
    public static String getBlockchainStoragePath()
    {
        try
        {
            String userHomeDir=System.getProperty("user.home");
            File blockchainFolder=new File(userHomeDir+"/.dabdabinf/blockchain");
            return blockchainFolder.getCanonicalPath();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return "";
        }
    }
    public static List<Block> load()
	{
        String userHomeDir=System.getProperty("user.home");
        File blockchainFolder=new File(userHomeDir+"/.dabdabinf/blockchain");
        blockchainFolder.mkdirs();

        File blockZeroFile=new File(blockchainFolder,"block0000");

        if(!blockZeroFile.exists())
        {
            General.writeToFile(blockZeroFile,General.readEntireResource("/block0000"));
        }
        
	    List<Block> blocks=new ArrayList<Block>();
		File[] blockFiles = blockchainFolder.listFiles();
		for(File blockFile : blockFiles)
		{
			String blockFileName=blockFile.getName();
			Block block=new Block();
            if(blockFileName.startsWith("block"))
            {
                try
                {
                    block.blockNumber=Integer.parseInt(blockFileName.substring(5));
                    block.blockData=General.readEntireFile(blockFile);
                    blocks.add(block);
                }
                catch(NumberFormatException e) {}
            }
		}
		Collections.sort(blocks);
		
		blocks.get(0).previousBlockHash="dabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabd";
		blocks.get(0).generateHash();
		
		for(int i=1;i<blocks.size();++i)
		{
			blocks.get(i).previousBlockHash=blocks.get(i-1).blockHash;
			blocks.get(i).generateHash();
		}
		
		return blocks;
	}
}
