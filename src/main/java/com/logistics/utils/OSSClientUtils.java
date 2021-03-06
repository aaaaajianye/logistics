package com.logistics.utils;

import com.aliyun.oss.OSSClient;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

public class OSSClientUtils {

    private static String endpoint = "###########################";
    private static String accessKeyId = "#############################";
    private static String accessKeySecret = "###########################";

    public static String putAppImg(String name, byte[] data) {
        String bucket = "###############";
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        InputStream is = new ByteArrayInputStream(data);
        client.putObject(bucket, name, is);
        client.shutdown();
        return "#################" + name;
    }

    public static String putFile(String name, byte[] data) {
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        String bucket = "###############";
        InputStream is = new ByteArrayInputStream(data);
        client.putObject(bucket, name, is);
        client.shutdown();
        return "###############" + name;
    }

    public static String putAppImg(String name, File file) {
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        String bucket = "###############";
        client.putObject(bucket, name, file);
        client.shutdown();
        return "###############" + name;
    }

}