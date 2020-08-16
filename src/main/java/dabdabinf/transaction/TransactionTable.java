package dabdabinf.transaction;

import java.util.List;
import java.util.Map;
import dabdabinf.profile.Profile;

class TransactionTable
{
    private List<List<Transaction>> transactionsByBlock;
    private Map<Profile,List<Transaction>> sent,received;
    void add(Transaction t)
    {
        sent.get(t.from).add(t);
        received.get(t.to).add(t);
        transactionsByBlock.get(t.blockNumber).add(t);
    }
    List<Transaction> sentTransactions(Profile p)
    {
        return sent.get(p);
    }
    List<Transaction> receivedTransactions(Profile p)
    {
        return received.get(p);
    }
    List<Transaction> blockTransactions(int blockNumber)
    {
        return transactionsByBlock.get(blockNumber);
    }
}
