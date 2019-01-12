package roy.application.master.mvp.ui.activity;

import roy.application.master.R;
import roy.application.master.mvp.ui.activity.base.BaseActivity;
import roy.application.master.proxy.IApi;
import roy.application.master.proxy.ProxyImplTest;

public class MainActivity extends BaseActivity {

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void setupActivityCompenent() {
        super.setupActivityCompenent();
    }

    @Override
    public void initView() {
        super.initView();
        ProxyImplTest.getInstance().createService(IApi.class)
                .getNetworkEntrance("TANHAOSHI","15367257793");
    }
}
