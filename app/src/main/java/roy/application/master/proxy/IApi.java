package roy.application.master.proxy;


import io.reactivex.Observable;
import roy.application.master.annotation.Dispense;
import roy.application.master.annotation.Param;

public interface IApi {

    //test IApi
    @Dispense("EVENTBUS_TYPE_ACTION_MOVE")
    Observable getNetworkEntrance(@Param("name") String value , @Param("pwd") String pwd);
}
