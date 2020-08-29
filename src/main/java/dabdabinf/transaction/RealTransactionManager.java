package dabdabinf.transaction;

import java.util.List;
import java.util.ArrayList;
import dabdabinf.profile.Profile;
import dabdabinf.profile.ProfileManager;
import dabdabinf.blockchain.Blockchain;
import dabdabinf.block.Block;
import dabdabinf.block.SplitBlockData;
import dabdabinf.tools.Rsa;

public class RealTransactionManager implements TransactionManager
{
    private TransactionTable processedTransactions;
    private List<Transaction> unprocessedTransactions;
    private Profile activeProfile;
    private Blockchain blockchain;
    private ProfileManager profileManager;
    
    public RealTransactionManager(Profile ap,Blockchain b,ProfileManager pm) throws TransactionDataInvalidException
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
            unprocessedReport+=String.format("Send %d dabdabinf to address %s\n",t.amount,t.to.getPublicKeyBase64());
        }
        return unprocessedReport;
    }

    public void discardUnprocessed()
    {
        unprocessedTransactions.clear();
    }

    private void loadBlockchainTransactions() throws TransactionDataInvalidException
    {
        for(int i=0;i<blockchain.length();++i)
        {
            SplitBlockData splitData=new SplitBlockData(blockchain.getBlock(i).blockData,i);
            String minerPublicKey=splitData.minerPublicKey;
            String transactionData=splitData.transactionData;

            Profile minerProfile=profileManager.findProfileWithPublicKey(minerPublicKey);
            if(minerProfile==null)
            {
                minerProfile=profileManager.createTmpProfile(Rsa.base64ToPublic(minerPublicKey));
            }
            if(transactionData.length()==0) continue;
            if(!(transactionData.charAt(0)=='$'))
            {
                throw new TransactionDataInvalidException(i);
            }
            int start=1;
            while(start<transactionData.length())
            {
                int end=transactionData.indexOf('@',start);
                int amount=Integer.parseInt(transactionData.substring(start,end));
                start=end+1;
                end=transactionData.indexOf('$',start);
                if(end==-1) end=transactionData.length();
                String to=transactionData.substring(start,end);
                Profile toProfile=profileManager.findProfileWithPublicKey(to);
                if(toProfile==null)
                {
                    toProfile=profileManager.createTmpProfile(Rsa.base64ToPublic(to));
                }
                processedTransactions.add(new Transaction(minerProfile,toProfile,amount,i));
                start=end+1;
            }
            
        }
    }
}
