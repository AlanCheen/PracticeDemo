package yifeiyuan.practice.yutils;

import android.net.TrafficStats;

/**
 * Created by alanchen on 15/8/12.
 */
public class DeviceUtils {

    //todo
    public static long getTotalRxBytes(){

        return TrafficStats.getTotalRxBytes();
    }
}
