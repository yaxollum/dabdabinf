package dabdabinf.block;

public class BlockSignatureInvalidException extends BlockInvalidException
{
    public BlockSignatureInvalidException(int blockNumber)
    {
        super("Miner signature",blockNumber);
    }
}
