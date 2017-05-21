package tc.userv;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MessageStoreFactory {
    private static Map<String, MessageStore> instances = new ConcurrentHashMap<>();
    public static MessageStore newStore(String specification) {
        synchronized (instances) {
            if (!instances.containsKey(specification)) {
                instances.put(specification, new MessageStoreInProcess());
            }
        }
        return instances.get(specification);
    }
}
