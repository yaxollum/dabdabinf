package dabdabinf.application;

import dabdabinf.blockchain.Blockchain;
import dabdabinf.profile.Profile;
import dabdabinf.transaction.TransactionManager;

public interface Messenger
{
    void help();
    void exit();
    void printBlock(Blockchain blockchain,int i);
    void printBlocks(Blockchain blockchain);
    void expectedNumber();
    void cmdNotFound(String cmd);
    void blockOutOfRange();
    void profileNotFound(String name);
    void profileCreated(String name);
    void printTransactionReport(Profile lookupProfile,TransactionManager tm);
    void exceptionCaught(Exception e);
}
