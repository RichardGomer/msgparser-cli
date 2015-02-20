MsgParser-CLI
-------------

A command line interface around the msgparser library - allows msg files (outlook) to be decoded from the command line.  This wrapper is not affiliated with the library - see http://auxilii.com/msgparser/ for the library itself.

The bundled version of the msgparser library includes some modifications - In particular support for FILETIME fields like SentOn, which is necessary to get a date from sent (not 
received) messages.

To get info:
```
$ java -jar msgparse-cli.jar -f filename.msg -i
```

To get the first attachment into a file called output:
```
java -jar msgparse-cli.jar -f filename.msg -a 0 | base64 -d > output
```


