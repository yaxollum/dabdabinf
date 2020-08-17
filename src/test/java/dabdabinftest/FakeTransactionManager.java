package dabdabinftest;

import java.util.List;
import java.util.ArrayList;
import dabdabinf.profile.Profile;
import dabdabinf.transaction.TransactionManager;
import dabdabinf.transaction.TransactionReport;

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
}
