package com.logistics.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具类
 */
public class CommonUtils {

    public static String createIdcode() {
        long time = System.currentTimeMillis();
        String idcode = letterize(time);
        return idcode;
    }

    public static String letterize(long number) {
        String letters = "0123456789abcdefghijklmnopqrstuvwxyz";
        int abcsLen = letters.length();
        StringBuffer buffer = new StringBuffer();
        do {
            long a = number / abcsLen;
            long b = number % abcsLen;
            buffer.append(letters.charAt((int) b));
            number = a;
        } while (number > 0);
        buffer.reverse();
        return buffer.toString();
    }

    public static String getNowString(String ff) {
        SimpleDateFormat format = new SimpleDateFormat(ff);
        Date now = new Date();
        String created = format.format(now);
        return created;
    }

    public static String getFileExtName(String filename) {
        return filename.substring(filename.lastIndexOf(".")).toLowerCase();
    }

    /**
     * 上传文件/图片都可以，最后返回超链接路径 （主用）
     * @param mf
     * @return
     */
    public static String uploadFile(MultipartFile mf) {
        String ofn = mf.getOriginalFilename();
        String ext = getFileExtName(ofn);
        String daystr = getNowString("yyyyMMdd");
        String filename = String.format("athena/%s/%s%s", daystr, createIdcode(), ext);
        try {
            String fileurl = OSSClientUtils.putFile(filename, mf.getBytes());
            return fileurl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将网络图片保存到阿里云oss
     **/
    public static String savePicture(String urlHttp){
        String picPath = null;
        try {
            File tmpFile = File.createTempFile(CommonUtils.createIdcode(), ".png");
            URL url = new URL(urlHttp);
            BufferedImage img = ImageIO.read(url);
            String tempFilePath = tmpFile.getAbsolutePath();
            tmpFile.delete();
            ImageIO.write(img, "png", new File(tempFilePath));
            String daystr = getNowString("yyyyMMdd");
            String ext = ".png";
            String filename = String.format("athena/%s/%s%s", daystr, createIdcode(), ext);
            picPath = OSSClientUtils.putAppImg(filename,tmpFile);
            tmpFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return picPath;
    }

}