package dabdabinf.block;

public class BlockHashInvalidException extends BlockInvalidException
{
    public BlockHashInvalidException(int blockNumber)
    {
        super("Hash",blockNumber);
    }
}
