package dabdabinf.blockchain;

public class BlockchainValidator
{
    static public void validate(Blockchain blockchain) throws BlockchainInvalidException
	{
        int blockchainLength=blockchain.length();

        if(blockchain.getBlock(0).previousBlockHash!="dabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabd")
        {
            throw new BlockHashInvalidException(0);
        }
		for(int i=1;i<blockchainLength;++i)
		{
		    if(!blockchain.getBlock(i).previousBlockHash.equals(blockchain.getBlock(i-1).blockHash)) 
		    {
                throw new BlockHashInvalidException(i);
		    }
		}
		for(int i=0;i<blockchainLength;++i)
		{
		    if(blockchain.getBlock(i).blockData.length()<64||
		    !blockchain.getBlock(i).previousBlockHash.equals(blockchain.getBlock(i).blockData.substring(0,64)))
		    {
                throw new BlockHashInvalidException(i);
		    }
		    
		    if(blockchain.getBlock(i).blockNumber!=i)
		    {
                throw new BlockNumberInvalidException(i);
		    }
		}
	}
}
