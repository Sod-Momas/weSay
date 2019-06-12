import cc.momas.wesay.polling.Message;
import cc.momas.wesay.polling.MessageList;
import org.junit.Test;

/**
 * @author Sod-Momas
 * @date 2019.06.12
 **/
public class MessageListTest {

    @Test
    public void toJson() {
        MessageList instance = MessageList.getInstance();
        instance.add(new Message("a", "a"));
        instance.add(new Message("b", "b"));
        instance.add(new Message("c", "c"));
        instance.add(new Message("d", "d"));
        System.out.println(instance);
    }
}
