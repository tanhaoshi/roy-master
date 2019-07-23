package roy.application.master.mvp.ui.activity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import roy.application.master.R;
import roy.application.master.mvp.ui.activity.base.BaseActivity;
import roy.application.master.widget.MaterialDialog;

public class MainActivity extends BaseActivity {

    @BindView(R.id.roy_text)
    TextView roy_text;

    @Override
    public int getContentView() {
        //循环了四次 将里面的值都赋值为空，然后返回那个不为空的。 如果里面全为空的话 返回一个对象
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        super.initView();
        roy_text.setText("roy handsome");
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                Log.i("MainActivity", "i == 5");
                //验证结果  for 循环里面的 break 会终止循环
                break;
            }
            Log.i("MainActivity", "i = " + String.valueOf(i));
        }

        int j = 0;
        while (j < 20) {
            if (j == 10) {
                Log.i("MainActivity", "j = 10");
                // 同理 while 循环里面的 break 名词 代表的也是终止循环.
                break;
            }
            j++;
            Log.i("MainActivity", "j = " + String.valueOf(j));
        }
        /** 这个过程封装数据返回 如何才能将数据返回至next层 */
        //        NettyProxyAccess.getInstance().
        //                createService(IApi.class).
        //                getNetworkEntrance(getString(R.string.testName),getString(R.string.testPwd))
        //                .subscribe(new Observer() {
        //                    @Override
        //                    public void onSubscribe(Disposable d) {
        //
        //                    }
        //
        //                    @Override
        //                    public void onNext(Object value) {
        //                        KLog.i("The process is running");
        //                    }
        //
        //                    @Override
        //                    public void onError(Throwable e) {
        //
        //                    }
        //
        //                    @Override
        //                    public void onComplete() {
        //
        //                    }
        //                });
        try{
            Log.i("MainActivity","try statement block");
        }finally {
            Log.i("MainActivity","finally statement block");
        }

        boolean isPrintln = false;

        boolean simulation = true;

        isPrintln |= simulation;

        Log.i("MainActivity","look '|=' value = " + isPrintln);

        int a = 40;

        int b = 30;

        a |= b;

        Log.i("MainActivity","a |= b value = " + a);
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

    public void quickSort(int[] array) {
        if (array.length > 0) {
            doQuickSort(array, 0, array.length - 1);
        }
    }

    private void doQuickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int low = left;
        int high = right;
        int temp = array[low];
        while (low != high) {
            while (low < high && array[high] > temp) {
                high--;
            }
            array[low] = array[high];
            while (low < high && array[low] < temp) {
                low++;
            }
            array[high] = array[low];
        }
        array[high] = temp;
        printArray(array);
        doQuickSort(array, left,  low - 1);
        doQuickSort(array, high + 1, right);
    }

    private void printArray(int[] array){

    }

}
