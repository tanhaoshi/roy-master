package roy.application.master.oksocket;

import java.net.Socket;

public abstract class OkSocketFactory {
    public abstract Socket createSocket(ConnectionInfo info, OkSocketOptions options) throws Exception;
}
