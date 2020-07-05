## HDFS Shell Commands

#### Start Hadoop cluster

```shell script
sbin/start-dfs.sh # start HDFS
sbin/start-yarn.sh # start yarn resource manager
```

#### Help
```shell script
hadoop fs -help ${command}

# for example
$ hadoop fs -help rm
-rm [-f] [-r|-R] [-skipTrash] <src> ... :
  Delete all files that match the specified file pattern. Equivalent to the Unix
  command "rm <src>"

  -skipTrash  option bypasses trash, if enabled, and immediately deletes <src>
  -f          If the file does not exist, do not display a diagnostic message or
              modify the exit status to reflect an error.
  -[rR]       Recursively deletes directories
```

## HDFS Java client APIs