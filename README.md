# SecXchange-Java
SecXchange project developped in Java language, previously performed in C++.
Developped with IntelliJ IDEA.


### Description

Encrypted messages exchange application on local network.
Several cryptographic protocols are used, such as RSA (asymmetric) or AES (symmetrical).


### Additions since the first version (C++)

A graphic interface has been added in order to limitate the interactions with a terminal. It's a better way to interact with users.

Java allows a better exploitation of the different cryptographic protocols (already existing classes for keys, RSA, encryption/decryption, ...). The different already existing classes and librairies improves the security part of the project (bigger key length, faster encryption/decryption).


### How to run it ?

First, make sure your "PATH" environnement variable is set with the path of your java's jdk (by preference jdk 13).

In the folder "out/artifacts" you will find two folders ("Client" and "Server") corresponding of two executables, depending on how you want to run the project.

By running it as a Server, you have to refer a port connection, where a Client will be able to join the discussion.

By running it as a Client, you have to refer the ip and port of the Server in order to connect.

An id and a password are obviously requested to make sure you got the right of using the account, and to make sure you're discussing with a legitimate user. 

### Future improvements

A mobile version is currently in development.

The adding of medias links such as photos or videos and displaying them in the messages discussion history is a serious possibility.

Developping an API available online in order to avoid using local files to authenticate and improve security part and new users adding is one next step.
