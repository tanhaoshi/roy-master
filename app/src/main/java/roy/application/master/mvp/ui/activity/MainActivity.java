package roy.application.master.mvp.ui.activity;

import android.os.Bundle;

import butterknife.ButterKnife;
import roy.application.master.R;
import roy.application.master.mvp.ui.activity.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void setupActivityCompenent() {
        super.setupActivityCompenent();
    }
}
