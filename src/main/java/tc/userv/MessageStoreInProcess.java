package tc.userv;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Application's store of the captured messages.
 */
public class MessageStoreInProcess implements MessageStore {
    private Map<String,Queue<Message>> store = new ConcurrentHashMap<>();
    public void deleteTag(String tag) {
        if (this.store.containsKey(tag)) {
            this.store.get(tag).clear();
            this.store.remove(tag);
        }
    }
    public void clearTag(String tag) {
        synchronized (this.store) {
            if (this.store.containsKey(tag)) {
                this.store.get(tag).clear();
            }
        }
    }
    public void putMessage(String tag, Message m) {
        synchronized (this.store) {
            if (!this.store.containsKey(tag)) {
                this.store.put(tag, new ConcurrentLinkedQueue<>());
            }
        }
        this.store.get(tag).add(m);
    }
    public int messageCount(String tag) {
        int result = 0;
        if (this.store.containsKey(tag)) {
            result = this.store.get(tag).size();
        }
        return result;
    }
    public int totalCount() {
        int result = 0;
        for (Map.Entry<String, Queue<Message>> kv : this.store.entrySet()) {
            result += kv.getValue().size();
        }
        return result;
    }
    public int tagCount() {
        return this.store.size();
    }
    public Set<String> getTags() {
        return this.store.keySet();
    }
    public List<Message> getMessages(String tag) {
        List<Message> result = new LinkedList<>();
        if (this.store.containsKey(tag)) {
            result.addAll(this.store.get(tag));
        }
        return result;
    }
}
