package cc.momas.wesay.netty;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * json工具
 *
 * @author Sod-Momas
 * @since 2021-03-08
 */
public class JsonUtil {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final static Logger log = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * 对象转json
     *
     * @param o 对象
     * @return json
     */
    public static String stringify(Object o) {
        try {
            return OBJECT_MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("", e);
            return "null";
        }
    }

    /**
     * json反序列化成json
     *
     * @param json  json
     * @param clazz 对象类型对象
     * @param <T>   对象类型
     * @return 对象
     */
    public static <T> T parse(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("", e);
            return null;
        }
    }
}
