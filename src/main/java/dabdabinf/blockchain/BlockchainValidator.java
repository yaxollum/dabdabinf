package dabdabinf.blockchain;

public class BlockchainValidator
{
    static boolean validate(Blockchain blockchain)
	{/*
	    String error1Message=General.readEntireFile(new File("messages/error1Message"));
	    String error2Message=General.readEntireFile(new File("messages/error2Message"));
	    
		for(int i=1;i<blocks.size();++i)
		{
		    if(!blocks.get(i).previousBlockHash.equals(blocks.get(i-1).blockHash)) 
		    {
		        System.out.printf(error1Message,i);
		        return false;
		    }
		}
		for(int i=0;i<blocks.size();++i)
		{
		    if(blocks.get(i).blockData.length()<64||
		    !blocks.get(i).previousBlockHash.equals(blocks.get(i).blockData.substring(0,64)))
		    {
		        System.out.printf(error1Message,i);
		        return false;
		    }
		    
		    if(blocks.get(i).blockNumber!=i)
		    {
		        System.out.printf(error2Message,i);
		        return false;
		    }
		}*/
		return true;
	}
}
