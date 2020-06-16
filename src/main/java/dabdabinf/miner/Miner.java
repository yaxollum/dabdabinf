package dabdabinf.miner;

import dabdabinf.tools.General;
import dabdabinf.block.Block;

public class Miner
{	
	private boolean mineLoop(byte[] newData,int start)
	{
	    for(byte n1='a';n1<='z';++n1)
	    for(byte n2='a';n2<='z';++n2)
	    for(byte n3='a';n3<='z';++n3)
	    for(byte n4='a';n4<='z';++n4)
	    for(byte n5='a';n5<='z';++n5)
	    for(byte n6='a';n6<='z';++n6)
	    for(byte n7='a';n7<='z';++n7)
	    for(byte n8='a';n8<='z';++n8)
	    for(byte n9='a';n9<='z';++n9)
	    for(byte n10='a';n10<='z';++n10)
	    {
	        newData[start]=n1;
	        newData[start+1]=n2;
	        newData[start+2]=n3;
	        newData[start+3]=n4;
	        newData[start+4]=n5;
	        newData[start+5]=n6;
	        newData[start+6]=n7;
	        newData[start+7]=n8;
	        newData[start+8]=n9;
	        newData[start+9]=n10;
	        
	        byte[] newHash=General.sha256(newData);
	        if(newHash[0]==-38&&newHash[1]==-67&&newHash[2]==-85)
	        {
	            return true;
	        }
	    }
	    return false;
	}
	
	Block mine(Profile activeProfile,String transactionData,Block lastBlock)
	{
	    long startTime=System.nanoTime();

	    //Block lastBlock=blocks.get(blocks.size()-1);
	    //lastBlock.blockHash="dabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabd";

        ByteArrayOutputStream newDataStream=new ByteArrayOutputStream();

        newDataStream.write(lastBlock.blockHash.getBytes());
        newDataStream.write('*');
        newDataStream.write(activeProfile.getPublicKey().getBytes());
        newDataStream.write('!');
        newDataStream.write(activeProfile.sign(transactionData).getBytes());
        newDataStream.write(transactionData.getBytes());
        newDataStream.write(new byte[10]);

	    byte[] newData=newDataStream.toByteArray();
	    
	    if(!mineLoop(newData))
	    {
	        System.out.println("Unable to mine a new block.");
	        return;
	    }
	    
	    Block newBlock=new Block();/*
	    newBlock.blockNumber=lastBlock.blockNumber+1;
	    newBlock.previousBlockHash=lastBlock.blockHash;
	    newBlock.blockData=new String(newData);
	    newBlock.generateHash();
	    
	    blocks.add(newBlock);
	    
	    long endTime=System.nanoTime();
	    System.out.printf("New block successfully mined and added to blockchain in %,d nanoseconds.\n",endTime-startTime);
	    if(verify())
	    {
	        System.out.println("The new block has been verified!");
	        newBlock.export();
	        System.out.println("The new block has been exported to a file!");
	    }
	    else
	    {
	        System.out.println("ERROR: Unable to successfully verify new block!");
	    }*/
	}
}
