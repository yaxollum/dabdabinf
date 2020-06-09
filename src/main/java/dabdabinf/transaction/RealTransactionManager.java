package dabdabinf.transaction;

import java.util.List;
import java.util.ArrayList;
import dabdabinf.profile.Profile;

public class RealTransactionManager implements TransactionManager
{
    private List<Transaction> processedTransactions;
    private List<Transaction> unprocessedTransactions;
    
    public RealTransactionManager()
    {
        processedTransactions=new ArrayList<Transaction>();
        unprocessedTransactions=new ArrayList<Transaction>();
    }
    
    public void newTransaction(Profile from,Profile to,int amount)
    {
        if(amount<=0)
        {
            System.out.println("Transaction amount must be positive!");
            return;
        }
        unprocessedTransactions.add(new Transaction(from,to,amount));
    }
    /*
    void mine(Blockchain blockchain) // doesn't make sense
    {
        //process transactions
        String transactionData="";
        
        for(int i=0;i<unprocessedTransactions.size();++i)
        {   
            transactionData+=unprocessedTransactions.get(i).toString();
        }
        blockchain.mine(transactionData);
        processedTransactions.addAll(unprocessedTransactions);
        unprocessedTransactions.clear();
    }*/
}
