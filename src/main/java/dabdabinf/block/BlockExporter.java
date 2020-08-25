package dabdabinf.block;

import java.io.File;
import dabdabinf.tools.General;

public class BlockExporter 
{
	public static void export(Block b)
	{
        String userHomeDir=System.getProperty("user.home");
        File blockchainFolder=new File(userHomeDir+"/.dabdabinf/blockchain");
	    File blockFile=new File(blockchainFolder,
            String.format("block%04d",b.blockNumber));
	    General.writeToFile(blockFile,b.blockData);
	}
} 
