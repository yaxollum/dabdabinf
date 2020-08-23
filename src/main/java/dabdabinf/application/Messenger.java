package dabdabinf.application;

import dabdabinf.profile.ProfileManager;
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
    void prompt(Profile activeProfile);
    void noPrivateKey(Profile p);
    void listProfiles(ProfileManager pm);
    void profileExists(String name);
}
