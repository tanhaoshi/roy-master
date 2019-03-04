package roy.application.master.netty;


public interface CallNetty<T> extends Cloneable{

    void cancel();

    CallNetty<T> clone();

    boolean isCanceled();

    void execute();
}
