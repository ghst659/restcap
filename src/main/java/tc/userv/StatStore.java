package tc.userv;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class StatStore {
    private static StatStore theInstance = new StatStore();
    public static StatStore getInstance() {
        return theInstance;
    }
    private Map<String, AtomicInteger> stats = new ConcurrentHashMap<>();
    public Map<String, Integer> dump() {
        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<String, AtomicInteger> kv: this.stats.entrySet()) {
            result.put(kv.getKey(), kv.getValue().get());
        }
        return result;
    }
    public int get(String key) {
        int result = 0;
        if (this.stats.containsKey(key)) {
            result = this.stats.get(key).get();
        }
        return result;
    }
    public void put(String key, int value) {
        if (! this.stats.containsKey(key)) {
            this.stats.put(key, new AtomicInteger(value));
        } else {
            this.stats.get(key).set(value);
        }
    }
    public int inc(String key) {
        if (! this.stats.containsKey(key)) {
            this.stats.put(key, new AtomicInteger(0));
        }
        return this.stats.get(key).incrementAndGet();
    }
    public int dec(String key) {
        if (! this.stats.containsKey(key)) {
            this.stats.put(key, new AtomicInteger(0));
        }
        return this.stats.get(key).decrementAndGet();
    }
}
