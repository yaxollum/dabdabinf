package dabdabinf.block;

public class SplitBlockDataException extends Exception
{
    public SplitBlockDataException(String missingSymbol,int blockNumber)
    {
        super(String.format("Missing %s in block data of block %d of blockchain.",missingSymbol,blockNumber));
    }
}
