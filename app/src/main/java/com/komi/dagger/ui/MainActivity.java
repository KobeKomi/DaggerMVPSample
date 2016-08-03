package com.komi.dagger.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.komi.dagger.R;
import com.komi.dagger.component.DaggerPresenterComponent;
import com.komi.dagger.entity.Apple;
import com.komi.dagger.entity.Fruit;
import com.komi.dagger.presenter.IPresenter;
import com.komi.dagger.presenter.PresenterA;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import dagger.Lazy;

// View层
public class MainActivity extends AppCompatActivity implements IView{

    private TextView tv_content;
    private Button btn;
    private ProgressDialog pd;
    private static final String TAG = "KOMI";

    @Inject
    public IPresenter presenter;        // 1.使用dagger注入IPresenter，初始化并不在view层，实现了view层和presenter层的解耦
                                        // 如果复用View，即改变presenter，并不用修改view的代码，仅仅注入的时候将PresentA换成PresentB即可。
    @Inject
    public PresenterA presenterA;

    @Named("Apple")
    @Inject
    public Fruit mFruitApple; // 必须同时使用@Named 和 @Inject

    @Inject
    public Apple mHfsApple;

    @Named("Orange")
    @Inject
    public Fruit mOrange;   // 使用@Named区分要注入的对象使用的方法

    @Named("Banana")
    @Inject
    public Fruit mBanana;

    @Named("name")
    @Inject
    public String name;

    @Named("nickname")
    @Inject
    public String nickname;

    @Inject
    public Lazy<String> lazyPassword;    //注入Lazy元素

    @Inject
    public Provider<String> ProviderPassword;//注入Provider元素

    String password1;
    String password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 方法一：在application层初始化注入器的实例，可重复调用注入器来对不同的容器完成注入
//        ((MyApplication)getApplication()).getPresenterComponent().inject(this); // 注入依赖 - IPresenter

        // 方法二：每次创建注入器实例，来完成对容器的注入
        DaggerPresenterComponent.create().inject(this);
        initView();
        initListener();
    }

    private void initView() {

        Log.i(TAG, "name："+name+"    nickname："+nickname);
        Log.i(TAG, "password："+password1+"   password"+password2);


        tv_content = (TextView) findViewById(R.id.tv_content);
        btn = (Button) findViewById(R.id.btn);

        // 1.创建presenter并初始化
        // 若未使用dagger依赖注入，那么view和presenter层之间仍然有较大耦合，
        // 修改presenter的话，view层也会变动
        // 而dagger的使用，可以极大的降低这种耦合。
        //presenter = new PresenterA(this);

        presenter.onCreate(this);

    }

    private void initListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.performOnClick(); // 2.点击响应事件传递给presenter处理
            }
        });
    }

    @Override
    public void setData(Integer result) {
        // 6.将presenter传递过来的数据显示到界面上
        Log.i(TAG, "setData: 设置数据 "+result);
        Fruit fruit;
        switch (result)
        {
            case 0:
                fruit= mFruitApple;
                break;
            case 1:
                fruit=mBanana;
                break;
            case 2:
                fruit=mOrange;
                break;
            default:
                fruit=mHfsApple;
                break;
        }

        tv_content.setText(tv_content.getText()+"\r\n"+fruit.toString());


        //延时加载
        password1 = lazyPassword.get();         // 这个时候才会注入元素password1，以后每次调用都是得到同一个对象
        password2 = ProviderPassword.get();     // 这个时候才会注入元素password2，以后每次调用不一定会得到同一个对象
        //根据Provides方法具体实现的不同，可能返回跟f2是同一个对象，也可能不是。
        // 只有Module的Provide方法每次都创建新实例时，Provider每次get()的对象才不相同
        Log.i(TAG, "password:"+password1+"   password:"+password2);


    }

    @Override
    public void showDialog() {
        // 创建并显示进度对话框
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//设置进度条样式为水平
        pd.setMax(10);//设置进度条最大值
        pd.setCancelable(false);//设置进度条不可以被Back键取消
        pd.setTitle("正在联网下载数据");//设置标题
        pd.setMessage("请耐心等一下，好不好嘛 ~~~");//设置进度条内容部分的文字
        pd.setIndeterminate(false);//设置是否显示进度条里的进度
        pd.show();
    }

    @Override
    public void setDialogProgress(Integer value) {
        // 更改加载进度
        pd.setProgress(value);
    }

    @Override
    public void cancelDialog() {
        pd.dismiss();
    }
}
