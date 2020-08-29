package dabdabinf.block;

public class BlockInvalidException extends Exception
{
    public BlockInvalidException(String invalidPart,int blockNumber)
    {
        super(String.format("%s of block %d of blockchain is invalid.",invalidPart,blockNumber));
    }
}
