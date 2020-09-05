## dabdabinf

`dabdabinf` is a basic "cryptocurrency" written in Java.

**dabdabinf is NOT meant to be used as a serious currency. Please treat dabdabinf with the same (or less) amount of seriousness as [Dank Memer](https://dankmemer.lol/) coins.**

## Features
- a (centralized) **blockchain** that uses SHA-256 as its hash function
- **profiles** - unique names that are linked to a RSA public keys
- **mining** - trying to find a string whose hexadecimal SHA-256 hash begins with "dabdab"
- ~~secure~~ **transactions** - signed using RSA

## Limitations
- probably has multiple security holes
- not distributed (only runs on one computer) - this limitation alone makes dabdabinf practically useless
- uses RSA, which has large key sizes, making it unsuitable for blockchains (probably should have used elliptic-curve cryptography instead)
- miner is extremely slow (though you could replace the default miner your own custom native code miner by rewriting `dabdabinf.miner.Miner.mineLoop()`)
- ... and more

## Build/Run instructions

Requires Apache Maven and Java 11 or later.

```bash
git clone https://github.com/yaxollum/dabdabinf
cd dabdabinf
mvn package
```

The packaged JAR file can be found inside the `target` folder. To run it,

```bash
cd target
java -jar dabdabinf-VERSION.NUMBER.jar
```
