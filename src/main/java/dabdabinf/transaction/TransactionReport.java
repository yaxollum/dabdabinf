package dabdabinf.transaction;

import java.util.List;
import dabdabinf.transaction.Transaction;

public class TransactionReport
{
    private List<Transaction> sent,received;
    private int balance;

    public TransactionReport(List<Transaction> s,List<Transaction> r)
    {
        sent=s;
        received=r;
        for(Transaction t : sent)
        {
            balance-=t.amount;
        }
        for(Transaction t : received)
        {
            balance+=t.amount;
        }
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
        report+=String.format("Final balance: %d dabdabinf\n",balance);
        return report;
    }
}
