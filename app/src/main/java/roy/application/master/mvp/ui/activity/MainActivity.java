package roy.application.master.mvp.ui.activity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.socks.library.KLog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import roy.application.master.R;
import roy.application.master.mvp.ui.activity.base.BaseActivity;
import roy.application.master.netty.NettyProxyAccess;
import roy.application.master.proxy.IApi;
import roy.application.master.widget.MaterialDialog;

public class MainActivity extends BaseActivity {

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        /** 这个过程封装数据返回 如何才能将数据返回至next层 */
        NettyProxyAccess.getInstance().
                createService(IApi.class).
                getNetworkEntrance(getString(R.string.testName),getString(R.string.testPwd))
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object value) {
                        KLog.i("The process is running");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.about){
            new MaterialDialog.Builder(this)
                    .title(R.string.singleChoiceTitle)
                    .items(R.array.perference_values)
                    .itemsCallbackSingleChoice(5, new MaterialDialog.ListCallbackSingleChoice() {
                        @Override
                        public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                            Toast.makeText(MainActivity.this,which + ": "+text,Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    })
                    .positiveText(R.string.md_choose_label)
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
