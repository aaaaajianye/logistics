package com.logistics.utils;

/**
 * @author shiwen
 * @date 2020/6/10
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 百度地图接口工具类
 */
public class LocationUtils {

    private static double EARTH_RADIUS = 6378.137;

    private static final String BAIDU_APP_KEY = "42b8ececa9cd6fe72ae4cddd77c0da5d";

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 返回输入地址的经纬度坐标 key lng(经度),lat(纬度)
     */
    public static Map<String, String> getLatitude(String address) {
        try {
            // 将地址转换成utf-8的16进制
            address = URLEncoder.encode(address, "UTF-8");
            // 如果有代理，要设置代理，没代理可注释
            // System.setProperty("http.proxyHost","192.168.172.23");
            // System.setProperty("http.proxyPort","3209");

            URL resjson = new URL("http://api.map.baidu.com/geocoder?address="
                    + address + "&output=json&key=" + BAIDU_APP_KEY);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    resjson.openStream()));
            String res;
            StringBuilder sb = new StringBuilder("");
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
            in.close();
            String str = sb.toString();
            //System.out.println("return json:" + str); //输出测试
            if(str!=null&&!str.equals("")){
                Map<String, String> map = null;
                int lngStart = str.indexOf("lng\":");
                int lngEnd = str.indexOf(",\"lat");
                int latEnd = str.indexOf("},\"precise");
                if (lngStart > 0 && lngEnd > 0 && latEnd > 0) {
                    String lng = str.substring(lngStart + 5, lngEnd);
                    String lat = str.substring(lngEnd + 7, latEnd);
                    map = new HashMap<String, String>();
                    map.put("lng", lng);
                    map.put("lat", lat);
                    return map;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return 距离
     */
    public static double getDistanceByMap(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }

    /**
     * 根据两个详细地址（经纬度）计算出直线距离 / 公里
     *
     * @param startAddress
     * @param endAddress
     * @return
     */
    public static Double getDistance(String startAddress, String endAddress) {
        Map<String, String> map1 = LocationUtils.getLatitude(startAddress);
        Map<String, String> map2 = LocationUtils.getLatitude(endAddress);
        return (getDistanceByMap(Double.parseDouble(map1.get("lat")), Double.parseDouble(map1.get("lng")),
                Double.parseDouble(map2.get("lat")), Double.parseDouble(map2.get("lng"))) / 1000.0);
    }

    // 测试测试
    public static void main(String args[]) {
        System.out.println(LocationUtils.getDistance("广东省肇庆四会市四会中学城中校区", "广东省广州市仲恺农业工程学院海珠校区") );
    }

}