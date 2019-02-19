package roy.application.master.oksocket;

import java.io.Serializable;

public interface IStateSender {
    void sendBroadcast(String action, Serializable serializable);

    void sendBroadcast(String action);
}
