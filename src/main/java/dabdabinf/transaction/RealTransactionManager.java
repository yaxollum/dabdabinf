package dabdabinf.transaction;

import java.util.List;
import java.util.ArrayList;
import dabdabinf.profile.Profile;
import dabdabinf.blockchain.Blockchain;

public class RealTransactionManager implements TransactionManager
{
    private TransactionTable processedTransactions;
    private List<Transaction> unprocessedTransactions;
    private Profile activeProfile;
    private Blockchain blockchain;
    
    public RealTransactionManager(Profile ap,Blockchain b)
    {
        processedTransactions=new TransactionTable();
        unprocessedTransactions=new ArrayList<Transaction>();
        activeProfile=ap;
        blockchain=b;
    }
    
    public void newTransaction(Profile to,int amount)
    {
        if(amount<=0)
        {
            System.out.println("Transaction amount must be positive!");
            return;
        }
        unprocessedTransactions.add(new Transaction(activeProfile,to,amount,blockchain.length()));
    }

    public void processAll()
    {
        for(Transaction ut : unprocessedTransactions)
        {
            processedTransactions.add(ut);
        }
        unprocessedTransactions.clear();
    }
    public TransactionReport getTransactionReport(Profile lookup)
    {
        return new TransactionReport(processedTransactions.sentTransactions(lookup),
            processedTransactions.receivedTransactions(lookup));
    }

    public String getTransactionData()
    {
        String transactionData="";
        
        for(Transaction t : unprocessedTransactions)
        {   
            transactionData+=t.toString();
        }
        return transactionData;
    }
}
