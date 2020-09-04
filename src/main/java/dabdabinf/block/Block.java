package dabdabinf.block;

import java.io.File;
import dabdabinf.tools.General;

public class Block implements Comparable<Block>
{
	public int blockNumber;
	public String previousBlockHash;
	public String blockData;
	public String blockHash;
	
	public String toString()
	{
        return String.format("Block Number: %d\n\nPrevious Block Hash: %s\n\nBlock Data: %s\n\nBlock Hash: %s\n",
        blockNumber,
        previousBlockHash,
        blockData,
        blockHash);
	}
	
	public void generateHash()
	{
		byte[] hashBytes=General.sha256(blockData.getBytes());
		String hashString="";
		for(byte b : hashBytes) hashString+=String.format("%02x",b);
		blockHash=hashString;
	}
	
	public int compareTo(Block other)
	{
		return blockNumber-other.blockNumber;
	}
} 
