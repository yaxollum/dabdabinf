package dabdabinf.application;

import dabdabinf.profile.ProfileManager;
import dabdabinf.block.Block;
import dabdabinf.profile.Profile;
import dabdabinf.transaction.TransactionManager;
import dabdabinf.transaction.BlockTransactions;

public interface Messenger
{
    void help();
    void exit();
    void printBlock(Block block,BlockTransactions bt);
    void expectedNumber();
    void cmdNotFound(String cmd);
    void blockOutOfRange();
    void profileNotFound(String name);
    void profileCreated(String name);
    void printTransactionReport(Profile lookupProfile,TransactionManager tm);
    void exceptionCaught(Exception e);
    void prompt(Profile activeProfile);
    void noPrivateKey(Profile p);
    void printProfile(Profile p);
    void listProfiles(ProfileManager pm);
    void profileExists(String name);
    void failedToMine();
    void successfullyMined(double timeInSeconds);
    void currentlyMining();
    void blockExported();
    void notEnoughArguments();
    void listUnprocessed(TransactionManager tm);
    void printDashedLine();
}
