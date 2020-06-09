package dabdabinf.transaction;

import java.util.List;
import java.util.ArrayList;
import dabdabinf.profile.Profile;

public interface TransactionManager
{
    public void newTransaction(Profile from,Profile to,int amount);
}
