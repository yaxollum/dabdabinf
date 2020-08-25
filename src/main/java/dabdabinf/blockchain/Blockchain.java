package dabdabinf.blockchain;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.File;
import java.util.Collections;
import dabdabinf.block.Block;
import dabdabinf.application.Messenger;

public interface Blockchain
{
	public String blockToString(int i);
	public String toString();
	public int length();
    public Block getBlock(int i);
    public void addBlock(Block b);
}
