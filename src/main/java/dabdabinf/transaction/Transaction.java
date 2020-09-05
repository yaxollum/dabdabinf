package dabdabinf.transaction;

import dabdabinf.profile.Profile;

public class Transaction
{
    public Profile from,to;
    public int amount,blockNumber,transactionNumber;
    Transaction(Profile f,Profile t,int a,int bn)
    {
        from=f;
        to=t;
        amount=a;
        blockNumber=bn;
    }
    public String getBlockchainFormat()
    {
        return String.format("$%d@%s",amount,to.getPublicKeyBase64());
    }
    public String getReportFormat()
    {
        return String.format("block #%d: %s --> %d dabdabinf --> %s\n",
            blockNumber,
            from.name,
            amount,
            to.name);
    }
}
