package roy.application.master.mvp.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import roy.application.master.mvp.ui.activity.base.impl.IActivity;

public class BaseActivity extends AppCompatActivity implements IActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setupActivityCompenent() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
