package com.dingding.kill.util;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author liudingding
 * @ClassName RandomUtil
 * @description
 * @date 2020/4/1 23:47
 * Version 1.0
 */
public class RandomUtil {
    private static final SimpleDateFormat dateFormatOne = new SimpleDateFormat("yyyyMMddHHmmssSS");

    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    /**
     * 生成订单编号
     * @return
     */
    public static String generateOrderCode(){
        return dateFormatOne.format(DateTime.now().toDate()) + generateNumber(4);
    }

    /**
     * 生成 number 位随机数流水号
     * @param number
     * @return
     */
    public static String generateNumber(final int number) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < number; i++) {
            //生成[0,10)的随机数
            stringBuffer.append(random.nextInt(10));
        }
        return stringBuffer.toString();
    }

}
