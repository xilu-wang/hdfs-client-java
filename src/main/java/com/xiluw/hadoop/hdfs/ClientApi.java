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
