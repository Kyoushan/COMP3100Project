

This implementation of a java scheduler (client side) is intended for use only with the ds-sim program provided by Macquarie University.

The standard command required for the client side to be run correctly with no faiures in executing jobs is "./ds-server -c ds-sample-config(some number here).xml -n". This is because the client side requires newlines to be sent after each message from the server is read.

