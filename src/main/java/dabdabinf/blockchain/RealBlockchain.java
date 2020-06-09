package dabdabinf.blockchain;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.util.Collections;
import dabdabinf.block.Block;
import dabdabinf.application.Messenger;

public class RealBlockchain implements Blockchain
{
	private List<Block> blocks;
	private Messenger messenger;
	
	public String blockToString(int i)
	{
		if(i>=blocks.size())
		{
			messenger.blockOutOfRange();
			return "";
		}
		else
		{
			return blocks.get(i).toString();
		}
	}
	
	public String toString()
	{
	    String res="";
		Iterator<Block> itr=blocks.iterator();
		while(itr.hasNext())
		{
			res+=itr.next().toString();
			res+='\n';
		}
		return res;
	}
	
	public int length()
	{
	    return blocks.size();
	}
	
	public RealBlockchain(List<Block> b)
	{
		blocks=b;
	}
}
