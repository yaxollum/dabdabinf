package dabdabinf.miner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import dabdabinf.tools.General;
import dabdabinf.block.Block;
import dabdabinf.profile.Profile;
import dabdabinf.application.Messenger;

public class Miner
{	
    private Messenger messenger;
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
            // 0xda = -38, 0xbd = -67, 0xab = -85
	        if(newHash[0]==-38&&newHash[1]==-67&&newHash[2]==-85)
	        {
	            return true;
	        }
	    }
	    return false;
	}
	
        /*
        Structure of a new block
        ------------------------
        [hash of previous block]*[public key of miner]![signature of miner][transaction data][10-letter  m a g i c  w o r d]

        What gets signed by the miner
        -----------------------------
        [hash of previous block][transaction data]

        The reason why the hash of the previous block is included is so that signatures cannot be maliciously reused.
        For example, if Alice sends Bob 500 dabdabinf and she sends Carl 30 dabdabinf, the transaction data should look like this:
        $500@[Bob's public key]$30@[Carl's public key]

        Let's suppose that Alice does not sign the hash of the previous block but instead only signs the transaction data shown above.
        Alice then mines a new block (Block 123 in the blockchain) containing the information listed in the section "Structure of a new block".

        Now, if Bob were a malicious user, and he wanted to steal more of Alice's money, he could mine a new block (Block 124) with the following structure:
        [hash of Block 123]*[Alice's public key]![Alice's signature from Block 123][transaction data from Block 123][a new 10-letter magic word computed by Bob]
        When Bob publishes the new Block 124, others will verify it as completely valid. Alice's signature is valid (it matches her public key and the transaction data). Others will have no way of knowing whether this block was mined by a malicious user, or by Alice, who just wanted to send 500 more dabdabinf to Bob and 30 more dabdabinf to Carl.

        This is why Alice needs to sign the hash of the previous block. Including that hash guarentees that all signatures are UNIQUE.
        */

	Block mine(Profile activeProfile,String transactionData,Block lastBlock)
	{
	    long startTime=System.nanoTime();

	    //Block lastBlock=blocks.get(blocks.size()-1);
	    //lastBlock.blockHash="dabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabd";
        ByteArrayOutputStream newDataStream=new ByteArrayOutputStream();
        try
        {
            newDataStream.write(lastBlock.blockHash.getBytes());
            newDataStream.write('*');
            newDataStream.write(activeProfile.publicKeyBase64().getBytes());
            newDataStream.write('!');
            newDataStream.write(activeProfile.sign(lastBlock.blockHash+transactionData).getBytes());
            newDataStream.write(transactionData.getBytes());
            newDataStream.write(new byte[10]);
        }
        catch(IOException e)
        {
            messenger.exceptionCaught(e);
        }

	    byte[] newData=newDataStream.toByteArray();
	    
	    if(!mineLoop(newData,newData.length-10))
	    {
	        System.out.println("Unable to mine a new block.");
	        return null;
	    }
	    
	    Block newBlock=new Block();
        return newBlock;
        /*
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
