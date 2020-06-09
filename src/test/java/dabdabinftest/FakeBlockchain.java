package dabdabinftest;

import org.junit.Assert;
import org.junit.Test;
import dabdabinf.blockchain.Blockchain;

public class FakeBlockchain implements Blockchain
{
    int blockToStringCalled=0;
    int toStringCalled=0;
    int lengthCalled=0;
    public String blockToString(int i)
    {
         ++blockToStringCalled;
         return String.format("blockToString called with %d",i);
    }
    public String toString()
    {
        ++toStringCalled;
        return "toStringCalled";
    }
    public int length()
    {
        ++lengthCalled;
        return -1;
    }
}

