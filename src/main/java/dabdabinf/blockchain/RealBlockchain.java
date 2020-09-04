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
	public int length()
	{
	    return blocks.size();
	}

    public Block getBlock(int i)
    {
        return blocks.get(i);
    }

    public void addBlock(Block b)
    {
        blocks.add(b);
    }
	
	public RealBlockchain(List<Block> b)
	{
		blocks=b;
	}
}
