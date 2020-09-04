package dabdabinf.transaction;

import java.util.List;
import java.util.ArrayList;
import dabdabinf.profile.Profile;

public class BlockTransactions
{
    private List<Transaction> transactionList;
    private final Profile miner;

    public BlockTransactions(Profile m)
    {
        miner=m;
        transactionList=new ArrayList<Transaction>();
    }

    public Profile getMiner()
    {
        return miner;
    }

    public void addTransaction(Transaction t)
    {
        transactionList.add(t);
    }

    public List<Transaction> getTransactions()
    {
        return transactionList;
    }

    public String toString()
    {
        String report="";
        report+=String.format("Block miner: %s\n",miner.name);
        for(Transaction t : transactionList)
        {
            report+=String.format("%s sends %d dabdabinf to %s\n",t.from.name,t.amount,t.to.name);
        }
        return report;
    }
}
