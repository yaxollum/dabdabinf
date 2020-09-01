package dabdabinf.blockchain;

import dabdabinf.block.*;
import dabdabinf.tools.*;

public class BlockchainValidator
{
    static public void validate(Blockchain blockchain) throws BlockInvalidException,SplitBlockDataException
	{
        int blockchainLength=blockchain.length();

        if(!blockchain.getBlock(0).previousBlockHash.equals("dabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabdabd"))
        {
            throw new BlockHashInvalidException(0);
        }
		for(int i=1;i<blockchainLength;++i)
		{
		    if(!blockchain.getBlock(i).previousBlockHash.equals(blockchain.getBlock(i-1).blockHash)) 
		    {
                throw new BlockHashInvalidException(i);
		    }
            if(!blockchain.getBlock(i).blockHash.startsWith("dabdab"))
            {
                throw new BlockHashInvalidException(i);
            }
		}
		for(int i=0;i<blockchainLength;++i)
		{
            Block block=blockchain.getBlock(i);
            SplitBlockData splitData=new SplitBlockData(block.blockData,block.blockNumber);
            if(!block.previousBlockHash.equals(splitData.previousBlockHash))
            {
                throw new BlockHashInvalidException(i);
            }
            if(!General.isValidBase64(splitData.minerPublicKey)||
                !General.isValidBase64(splitData.minerSignature))
            {
                throw new BlockBase64InvalidException(i);
            }
            if(!Rsa.verify(splitData.previousBlockHash+splitData.transactionData,
                splitData.minerSignature,
                Rsa.base64ToPublic(splitData.minerPublicKey)))
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
