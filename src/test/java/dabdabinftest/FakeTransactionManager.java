package dabdabinftest;

import java.util.List;
import java.util.ArrayList;
import dabdabinf.profile.Profile;
import dabdabinf.transaction.TransactionManager;
import dabdabinf.transaction.TransactionReport;
import dabdabinf.transaction.BlockTransactions;

public class FakeTransactionManager implements TransactionManager
{
    int newTransactionCalled;
    public void newTransaction(Profile to,int amount)
    {
        ++newTransactionCalled;
    }
    public TransactionReport getTransactionReport(Profile lp)
    {
        return null;
    }
    public BlockTransactions getBlockTransactions(int blockNumber)
    {
        return null;
    }
    public void processAll() {}
    public String getTransactionData()
    {
        return "";
    }
    public void discardUnprocessed() {}
    public String getUnprocessed()
    {
        return "";
    }
}
