MsgParser-CLI
-------------

A command line interface around the msgparser library - allows msg files (outlook) to be decoded from the command line.  This wrapper is not affiliated with the library - see http://auxilii.com/msgparser/ for the library itself.

To get info:
```
$ java -jar msgparse-cli.jar -f filename.msg -i
```

To get the first attachment into a file called output:
```
java -jar msgparse-cli.jar -f filename.msg -a 0 | base64 -d > output
```


