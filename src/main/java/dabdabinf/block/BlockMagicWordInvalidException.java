package dabdabinf.block;

public class BlockMagicWordInvalidException extends BlockInvalidException
{
    public BlockMagicWordInvalidException(int blockNumber)
    {
        super("Magic word",blockNumber);
    }
}
