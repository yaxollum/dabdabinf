package dabdabinf.transaction;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import dabdabinf.profile.Profile;
import java.security.PublicKey;

class TransactionTable
{
    private Map<Integer,List<Transaction>> transactionsByBlock;
    private Map<PublicKey,List<Transaction>> sent,received;

    void add(Transaction t)
    {
        sent.computeIfAbsent(t.from.keys.getPublic(), k -> new ArrayList<Transaction>()).add(t);
        received.computeIfAbsent(t.to.keys.getPublic(), k -> new ArrayList<Transaction>()).add(t);
        transactionsByBlock.computeIfAbsent(t.blockNumber, k -> new ArrayList<Transaction>()).add(t);
    }
    List<Transaction> sentTransactions(Profile p)
    {
        return sent.computeIfAbsent(p.keys.getPublic(), k -> new ArrayList<Transaction>());
    }
    List<Transaction> receivedTransactions(Profile p)
    {
        return received.computeIfAbsent(p.keys.getPublic(), k -> new ArrayList<Transaction>());
    }
    List<Transaction> blockTransactions(int blockNumber)
    {
        return transactionsByBlock.computeIfAbsent(blockNumber, k -> new ArrayList<Transaction>());
    }
    TransactionTable()
    {
        transactionsByBlock=new HashMap<Integer,List<Transaction>>();
        sent=new HashMap<PublicKey,List<Transaction>>();
        received=new HashMap<PublicKey,List<Transaction>>();
    }
}
