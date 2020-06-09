package dabdabinf.application;

import dabdabinf.blockchain.Blockchain;

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
}
