package dabdabinf.block;

public class SplitBlockData
{
    public String previousBlockHash;
    public String minerPublicKey;
    public String minerSignature;
    public String transactionData;
    public String magicWord;

    public SplitBlockData(String blockData,int blockNumber) throws SplitBlockDataException
    {
        int start=0;
        int end=blockData.indexOf('*',start);
        if(end==-1) throw new SplitBlockDataException("asterisk (*)",blockNumber);
        previousBlockHash=blockData.substring(start,end);

        start=end+1;
        end=blockData.indexOf('!',start);
        if(end==-1) throw new SplitBlockDataException("exclamation mark (!)",blockNumber);
        minerPublicKey=blockData.substring(start,end);

        start=end+1;
        end=blockData.indexOf('$',start);
        if(end!=-1)
        {   // Found transaction data: miner signature comes before '$'
            minerSignature=blockData.substring(start,end);
            start=end;
            end=blockData.indexOf('?',start);
            if(end==-1) throw new SplitBlockDataException("question mark (?)",blockNumber);
            // extracting transaction data
            transactionData=blockData.substring(start,end);
        }
        else
        {   // No transaction data: miner signature comes before '?'
            end=blockData.indexOf('?',start);
            if(end==-1) throw new SplitBlockDataException("question mark (?)",blockNumber);
            minerSignature=blockData.substring(start,end);
            transactionData="";
        }

        start=end+1;
        magicWord=blockData.substring(start);
    }
}
