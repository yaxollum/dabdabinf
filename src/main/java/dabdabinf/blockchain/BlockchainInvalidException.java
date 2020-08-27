package dabdabinf.blockchain;

public class BlockchainInvalidException extends Exception
{
    public BlockchainInvalidException(int blockNumber)
    {
        super(String.format("Block %d is invalid",blockNumber));
    }
}
