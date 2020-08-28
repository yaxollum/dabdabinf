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
            if(!blockchain.getBlock(i).blockHash.startsWith("dabdab")
            {
                throw new BlockHashInvalidException(i);
            }
		}
		for(int i=0;i<blockchainLength;++i)
		{
            Block block=blockchain.getBlock(i);
            SplitBlockData splitData=new SplitBlockData(block.blockData,block.blockNumber);
            if(block.previousBlockHash!=splitData.previousBlockHash) 
            {
                throw new BlockHashInvalidException(i);
            }
            if(!General.isValidBase64(minerPublicKey)||
                !General.isValidBase64(minerSignature))
            {
                throw new BlockBase64InvalidException(i);
            }
            if(!Rsa.verify(SplitBlockData.previousBlockHash+SplitBlockData.transactionData,minerSignature,Rsa.base64ToPublic(minerPublicKey)))
            {
                throw new BlockSignatureInvalidException(i);
            }
		    if(blockchain.getBlock(i).blockNumber!=i)
		    {
                throw new BlockNumberInvalidException(i);
		    }
            if(!General.isValidMagicWord(splitData.magicWord))
            {
                throw new BlockMagicWordInvalidException(i);
            }
		}
	}
}
