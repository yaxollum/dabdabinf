package dabdabinf.block;

public class BlockNumberInvalidException extends BlockInvalidException
{
    public BlockNumberInvalidException(int blockNumber)
    {
        super("Block number",blockNumber);
    }
}
