package cc.momas.wesay.netty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sod-Momas
 * @since 2021-03-09
 */
public class JsonUtilTest {
    public static void main(String[] args) {
        toJson();
    }

    private static void toJson() {
        Map<String, Object> response = new HashMap<>();
        response.put("msg", "【a】 上线了");
        response.put("msgType", "sys");
        response.put("length", 122);
        final String json = JsonUtil.stringify(response);
        System.out.println(json);
    }
}
