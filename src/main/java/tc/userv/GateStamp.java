package tc.userv;

import java.util.UUID;

/**
 * Information attached by a processing gate in a pipeline
 */
public class GateStamp {
    private String name;
    private long time;
    private UUID id;
    public GateStamp() {
        // default constructor for JacksonFeature.
    }
    public GateStamp(String label) {
        this.name = label;
        this.time = System.currentTimeMillis();
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return String.format("(name:%s, time:%d, id:%s)", name, time, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
