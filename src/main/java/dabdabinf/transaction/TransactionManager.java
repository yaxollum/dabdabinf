package dabdabinf.transaction;

import java.util.List;
import java.util.ArrayList;
import dabdabinf.profile.Profile;

public interface TransactionManager
{
    void newTransaction(Profile to,int amount);
    TransactionReport getTransactionReport(Profile lookup);
    String getTransactionData();
    void processAll();
}
