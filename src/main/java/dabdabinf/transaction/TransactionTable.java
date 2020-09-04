package dabdabinf.transaction;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import dabdabinf.profile.Profile;
import java.security.PublicKey;
import dabdabinf.block.BlockNumberInvalidException;

class TransactionTable
{
    private List<BlockTransactions> transactionsByBlock;
    private Map<PublicKey,List<Transaction>> sent,received;
    private Map<PublicKey,List<Integer>> mined;

    void addMiner(Profile miner,int blockNumber) throws BlockNumberInvalidException
    {
        if(blockNumber==transactionsByBlock.size())
        {
            transactionsByBlock.add(new BlockTransactions(miner));
            mined.computeIfAbsent(miner.keys.getPublic(), k -> new ArrayList<Integer>()).add(blockNumber);
        }
        else
        {
            throw new BlockNumberInvalidException(blockNumber);
        }
    }
    void addTransaction(Transaction t) throws BlockMinerNotAddedException
    {
        if(t.blockNumber<transactionsByBlock.size())
        {
            transactionsByBlock.get(t.blockNumber).addTransaction(t);
        }
        else
        {
            throw new BlockMinerNotAddedException();
        }
        sent.computeIfAbsent(t.from.keys.getPublic(), k -> new ArrayList<Transaction>()).add(t);
        received.computeIfAbsent(t.to.keys.getPublic(), k -> new ArrayList<Transaction>()).add(t);
    }
    List<Transaction> sentTransactions(Profile p)
    {
        return sent.computeIfAbsent(p.keys.getPublic(), k -> new ArrayList<Transaction>());
    }
    List<Transaction> receivedTransactions(Profile p)
    {
        return received.computeIfAbsent(p.keys.getPublic(), k -> new ArrayList<Transaction>());
    }
    List<Integer> minedBlocks(Profile p)
    {
        return mined.computeIfAbsent(p.keys.getPublic(), k -> new ArrayList<Integer>());
    }
    BlockTransactions blockTransactions(int blockNumber)
    {
        return transactionsByBlock.get(blockNumber);
    }
    TransactionTable()
    {
        transactionsByBlock=new ArrayList<BlockTransactions>();
        sent=new HashMap<PublicKey,List<Transaction>>();
        received=new HashMap<PublicKey,List<Transaction>>();
        mined=new HashMap<PublicKey,List<Integer>>();
    }
}
