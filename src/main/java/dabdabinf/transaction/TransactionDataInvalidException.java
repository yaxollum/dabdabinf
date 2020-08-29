package dabdabinf.transaction;

import dabdabinf.block.BlockInvalidException;

public class TransactionDataInvalidException extends BlockInvalidException
{
    public TransactionDataInvalidException(int blockNumber)
    {
        super("Transaction data",blockNumber);
    }
}
