package dabdabinf.transaction;

import dabdabinf.profile.Profile;

public class Transaction
{
    Profile to;
    int amount;
    Transaction(Profile t,int a)
    {
        to=t;
        amount=a;
    }
    public String toString()
    {
        return String.format("$%d@%s",amount,to);
    }
}
