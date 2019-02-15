package roy.application.master.mvp.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import roy.application.master.mvp.ui.activity.base.impl.IActivity;

public abstract class BaseActivity extends AppCompatActivity implements IActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(getContentView());
        initView();
    }

    public abstract int getContentView();

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
