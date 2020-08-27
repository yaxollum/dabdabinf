package dabdabinf.transaction;

import java.util.List;
import java.util.ArrayList;
import dabdabinf.profile.Profile;
import dabdabinf.blockchain.Blockchain;
import dabdabinf.block.Block;

public class RealTransactionManager implements TransactionManager
{
    private TransactionTable processedTransactions;
    private List<Transaction> unprocessedTransactions;
    private Profile activeProfile;
    private Blockchain blockchain;
    private ProfileManager profileManager;
    
    public RealTransactionManager(Profile ap,Blockchain b,ProfileManager pm)
    {
        processedTransactions=new TransactionTable();
        unprocessedTransactions=new ArrayList<Transaction>();
        activeProfile=ap;
        blockchain=b;
        profileManager=pm;
        loadBlockchainTransactions();
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

    public String getUnprocessed()
    {
        String unprocessedReport="";
        for(Transaction t : unprocessedTransactions)
        {
            unprocessedReport+=String.format("Send %d dabdabinf to address %s\n",t.amount,t.to.publicKeyBase64());
        }
        return unprocessedReport;
    }

    public void discardUnprocessed()
    {
        unprocessedTransactions.clear();
    }

    private void loadBlockchainTransactions()
    {
        for(int i=0;i<blockchain.length();++i)
        {
            Block b=blockchain.getBlock(i);
            
        }
    }
}
