## HDFS Shell Commands

#### Start Hadoop cluster

```shell script
# start/stop HDFS on the namenode
sbin/start-dfs.sh 
sbin/stop-dfs.sh 

# start/stop yarn on the resource manager
sbin/start-yarn.sh 
sbin/stop-yarn.sh
```

#### Help
```shell script
hdfs dfs -help <command>
```
```shell script
$ hdfs dfs -help rm
-rm [-f] [-r|-R] [-skipTrash] <src> ... :
  Delete all files that match the specified file pattern. Equivalent to the Unix
  command "rm <src>"

  -skipTrash  option bypasses trash, if enabled, and immediately deletes <src>
  -f          If the file does not exist, do not display a diagnostic message or
              modify the exit status to reflect an error.
  -[rR]       Recursively deletes directories
```

#### Common Commands
```shell script
hdfs dfs -ls /
hdfs dfs -mkdir -p /user/root/input 
hdfs dfs -put wc.input /user/root/input
hdfs dfs -get /user/root/input/wc.input ./
```

## HDFS Java client APIs

```java
package com.xiluw.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ClientApi {

    private FileSystem fileSystem =null;
    private Configuration conf =null;

    @Before
    public void init() throws URISyntaxException, IOException, InterruptedException {
        conf = new Configuration();
        fileSystem = FileSystem.get(new URI("hdfs://namenode:9000"),conf,"admin");
    }

    @After
    public void after() throws IOException {
        fileSystem.close();
    }

    // create directory
    @Test
    public void mkdir() throws IOException {
        fileSystem.mkdirs(new Path("/test"));
    }

    // rename directory
    @Test
    public void rename() throws IOException {
        fileSystem.rename(new Path("/test"),
                new Path("/log"));
    }

    // delete directory
    @Test
    public void delete() throws IOException {
        fileSystem.delete(new Path("/log"), true);
    }

    // upload file
    @Test
    public void upload() throws IOException {
        fileSystem.copyFromLocalFile(new Path("/Users/xingwang/Downloads/test.txt"),
                new Path("/test.txt"));
    }

    // download file
    @Test
    public void download() throws IOException {
        fileSystem.copyToLocalFile(false,
                new Path("/spark-logs"),
                new Path("/Users/xingwang/Downloads/spark-logs"), true);
    }

}

```