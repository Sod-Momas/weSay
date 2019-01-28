package cc.momas.wesay.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Sod-Momas
 */
public class CommonHelper {
    /**
     * 用于统计在线人数
     */
    private volatile static int onlineCount = 0;

    public static boolean isNullOrEmpty(String... strings) {
        for (String string : strings) {
            if (string == null || string.trim().length() < 1) {
                return true;
            }
        }
        return false;
    }

    public static int getOnlineCount() {
        return CommonHelper.onlineCount;
    }

    /**
     * 用户上线,用于统计在线人数
     */
    public synchronized static void userLogin() {
        CommonHelper.onlineCount++;
    }

    /**
     * 用户下线,用于统计在线人数
     */
    public synchronized static void userLogout() {
        CommonHelper.onlineCount--;
    }

    public static String urlEncode(String content) {
        try {
            return URLEncoder.encode(content, StandardCharsets.UTF_8.displayName());
        } catch (UnsupportedEncodingException ignore) {
        }
        return content;
    }

    public static String urlDecode(String content) {
        try {
            return URLDecoder.decode(content, StandardCharsets.UTF_8.displayName());
        } catch (UnsupportedEncodingException ignore) {
        }
        return content;
    }
}
