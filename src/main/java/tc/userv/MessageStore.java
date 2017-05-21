package tc.userv;

import java.util.List;
import java.util.Set;

interface MessageStore {
    void deleteTag(String tag);

    void clearTag(String tag);

    void putMessage(String tag, Message m);

    int messageCount(String tag);

    int totalCount();

    int tagCount();

    Set<String> getTags();

    List<Message> getMessages(String tag);
}
