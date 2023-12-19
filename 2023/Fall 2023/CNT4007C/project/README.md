## Title

P2P file sharing, currently at midpoint submission.

## Compilation and testing

To compile, cd into working directory, then run "javac \*.java"
To test, after compiling, run "java Peer id portNum" on two separate command prompts. To run, first create a peer with id 1 on the first command prompt, then create a peer with a different id but same port number on the second command prompt. After this, a connection should be established, and messages should be able to be sent between the two peers.

## Files and their functionalities

CfgManager - N/A

CommonCfgReader - primarily used to read the file and get the specific data to store into variables.

\*\*CompleteFileBool - sets up setters/getters for the socket and whether or not the file is complete.

FileReader - create a new file, uses an inputStream and buffer to add pieces to the piece HashMap, then returns the HashMap. Used in process functionality.

LogWriter - writer that has specified write messages to a file depending on what is occurring functionally.

\*\*Message - N/A

\*\*MsgConfig - could be added to Message, sets up setters/getters for the socket and the message

\*MsgReceiver - takes in the message through an input stream, then handles the proper response depending on the type. Should update bitfields, variables, and call LogWriter to print out everything that is occurring.

\*MsgSender - message sending functionality, effectively just sends a message thrugh an output stream.

\*\*Peer - simply defines the peer itself, including variables that describe the peer, such as the socket, IDs, and if its interested, as well as setters/getters for this data.

\*PeerClient - client functionality, connects to the peer acting as the server, handshakes, adds itself as one of the peers in the process, then performs the message sending, piece requesting, and message receiving processes, before shutting down.

PeerInfoReader - reader to read in ID, IP, portNum, and completedFile boolean. Also has other functions to get additional or all peer info.

PeerManager - N/A

\*\*PeerProcess - Main high level functionality, implements PeerServer/Client, as well as different readers, in order to have the entire process run properly. It gets all the needed data from the files and pieces, and assuming the file isn't complete, starts up the server and client side as threads, then leaves a server thread open after functionality is complete to act as a listener.

\*PeerServer - server functionality, acts as listener and accepts the incoming connections, performs handshake, creates the peer instance, then performs the message sending, piece requesting, and message receiving processes.

\*ReqPiece - used to request pieces assuming a peer doesn't have a complete file but is interested. Adds these pieces and messages in the peerProcess and keeps track of what pieces are added where, then terminates the specified peer's connection once it receives the whole file.

## Additional notes regarding the classes above

No asterisk means its reader/writer classes that simply serve to help the processes running.
1 asterisk means classes that focus on the process, such as the client/server and sending/receiving messages. This is where most of the implementation lies.
2 asterisks means classes that focus on the peer and its attributes, such as the Peer and PeerProcess classes themselves, as well as classes that have to do with the messages.

N/A classes are classes I personally didn't work on so I didn't want to add descriptions and them be incorrect.

PeerInfoReader is added but not implemented properly yet, still waiting on implementation for that class as well as the FileMerger class discussed previously.

Outside of that, only missing the finalized implementation of the Message type classes and their functionality, might need to add some additional code to ensure they actually function properly but unsure where to add yet, so waiting for all code to be pushed before we add and test.

Other than this, most of the code overall logic-wise should be complete, and only some small errors may remain.

javac _.java messages/_.java

## Responsibilties

@MattLee91 - Peer functionality, connection between the client and server, message sending and receiving, piece requesting, testing.

@RyanAveritt - File IO, general debugging, testing, and overall process functionality.

@charlieforward9 - Building Message classes, integration, debugging, testing.
