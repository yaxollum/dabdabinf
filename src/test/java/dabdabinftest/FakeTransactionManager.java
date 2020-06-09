package dabdabinftest;

import java.util.List;
import java.util.ArrayList;
import dabdabinf.profile.Profile;
import dabdabinf.transaction.TransactionManager;

public class FakeTransactionManager implements TransactionManager
{
    int newTransactionCalled;
    public void newTransaction(Profile from,Profile to,int amount)
    {
        ++newTransactionCalled;
    }
}
