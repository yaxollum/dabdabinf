package dabdabinf.transaction;

import java.util.List;
import java.util.ArrayList;
import dabdabinf.profile.Profile;
import dabdabinf.block.BlockNumberInvalidException;

public interface TransactionManager
{
    void newTransaction(Profile to,int amount);
    BlockTransactions getBlockTransactions(int blockNumber);
    TransactionReport getTransactionReport(Profile lookup);
    String getTransactionData();
    void processAll() throws BlockMinerNotAddedException,BlockNumberInvalidException;
    String getUnprocessed();
    void discardUnprocessed();
}
