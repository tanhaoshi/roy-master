package roy.application.master.netty.adapter;

import android.telecom.Call;

public interface CallNetty<T> extends Cloneable{

    void cancel();

    CallNetty<T> clone();

    boolean isCanceled();
}
