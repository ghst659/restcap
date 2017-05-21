package tc.userv;

import java.util.LinkedList;
import java.util.List;

public class Message {
    private List<GateStamp> history = new LinkedList<>();
    private String data = null;

    @Override
    public String toString() {
        return String.format("{\"%s\", %s}", data, history);
    }

    public Message() {
        // blank constructor required by JacksonFeature
    }

    public List<GateStamp> getHistory() {
        return history;
    }

    public void addStamp(GateStamp mark) {
        this.history.add(mark);
    }

    public void setHistory(List<GateStamp> history) {
        this.history = history;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
