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
        report+="Received dabdabinf\n-----\n";
        for(Transaction t : received)
        {
            report+=t.getReportFormat();
        }
        report+=String.format("Mined blocks (%d dabdabinf reward):",BLOCK_REWARD);
        for(int blockNumber : mined)
        {
            report+=String.format(" #%d,",blockNumber);
        }
        report+='\n';
        report+=String.format("Final balance: %d dabdabinf\n",balance);
        return report;
    }
}
