package dabdabinf.transaction;

import java.util.List;
import dabdabinf.transaction.Transaction;

public class TransactionReport
{
    private List<Transaction> sent,received;
    private List<Integer> mined;
    private int balance;
    private final int BLOCK_REWARD=100;

    public TransactionReport(List<Transaction> s,List<Transaction> r,List<Integer> m)
    {
        sent=s;
        received=r;
        mined=m;
        for(Transaction t : sent)
        {
            balance-=t.amount;
        }
        for(Transaction t : received)
        {
            balance+=t.amount;
        }
        balance+=m.size()*BLOCK_REWARD;
    }
    public String toString()
    {
        String report="";
        report+="Sent dabdabinf\n-----\n";
        for(Transaction t : sent)
        {
            report+=t.getReportFormat();
        }
        if(sent.isEmpty()) report+="(empty)\n";
        report+="Received dabdabinf\n-----\n";
        for(Transaction t : received)
        {
            report+=t.getReportFormat();
        }
        if(received.isEmpty()) report+="(empty)\n";
        report+=String.format("Mined blocks (%d dabdabinf reward):",BLOCK_REWARD);
        for(int blockNumber : mined)
        {
            report+=String.format(" #%d,",blockNumber);
        }
        if(mined.isEmpty()) report+=" (none)";
        report+='\n';
        report+=String.format("Final balance: %d dabdabinf\n",balance);
        return report;
    }
}
