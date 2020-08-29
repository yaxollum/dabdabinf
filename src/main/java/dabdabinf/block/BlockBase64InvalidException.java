package dabdabinf.block;

public class BlockBase64InvalidException extends BlockInvalidException
{
    public BlockBase64InvalidException(int blockNumber)
    {
        super("Base64 content",blockNumber);
    }
}
