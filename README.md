

This implementation of a java scheduler (client side) is intended for use only with the ds-sim program provided by Macquarie University.

The standard command required for the client side to be run correctly with no faiures in executing jobs is "./ds-server -c ds-sample-config(some number here).xml -n". This is because the client side requires newlines to be sent after each message from the server is read.

The following defines the new iteration of the assignment, that being Stage 2. The algorithm implemented is a modified version of First Capable and intends to minimise the average turnaround time of each set of jobs provided to the server. 

No flags need to be set in order to run the client, it is simply the ./stage2-test-x86 "java Client" -o tt -n.
