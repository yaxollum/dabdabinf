public class SplitBlockData
{
    public String previousBlockHash;
    public String minerPublicKey;
    public String minerSignature;
    public String transactionData;
    public String magicWord;

    public SplitBlockData(String blockData)
    {
        int start=0;
        int end=blockData.indexOf('*',start);
        previousBlockHash=blockData.substring(start,end);
    }
}
