package dabdabinf.transaction;

import dabdabinf.profile.Profile;

public class Transaction
{
    Profile from,to;
    int amount;
    String signature;
    Transaction(Profile f,Profile t,int a)
    {
        from=f;
        to=t;
        amount=a;
        signature="";
        sign();
    }
    void sign()
    {   
        signature=from.sign(toString());
    }
    public String toString()
    {
        if(signature.length()>0) return String.format("$%s$%d$%s$%s$",from,amount,to,signature);
        else return String.format("$%s$%d$%s$",from,amount,to);
    }
}
