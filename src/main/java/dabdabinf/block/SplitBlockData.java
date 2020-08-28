public class SplitBlockData
{
    public String previousBlockHash;
    public String minerPublicKey;
    public String minerSignature;
    public String transactionData;
    public String magicWord;

    public SplitBlockData(String blockData,int blockNumber)
    {
        int start=0;
        int end=blockData.indexOf('*',start);
        previousBlockHash=blockData.substring(start,end);

        start=end+1;
        end=blockData.indexOf('!',start);
        minerPublicKey=blockData.substring(start,end);

        start=end+1;
        end=blockData.indexOf('$',start);
        minerSignature=blockData.substring(start,end);

        start=end+1;
        end=blockData.indexOf('?',start);
        transactionData=blockData.substring(start,end);

        start=end+1;
        magicWord=blockData.substring(start);
    }
}
