package dabdabinf.transaction;

class TransactionTable
{
    private Map<Profile,List<Transaction>> sent,received;
    void add(Transaction t)
    {
        sent.get(t.from).add(t);
        received.get(t.to).add(t);
    }
    List<Transaction> sentTransactions(Profile p)
    {
        return sent.get(p);
    }
    List<Transaction> receivedTransactions(Profile p)
    {
        return received.get(p);
    }
}
