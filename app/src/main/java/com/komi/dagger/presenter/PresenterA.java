package com.komi.dagger.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.komi.dagger.model.IModel;
import com.komi.dagger.model.Model;
import com.komi.dagger.ui.IView;

import java.util.Random;


public class PresenterA implements IPresenter {

    IView view;     // 持有view的接口，用于显示数据
    private IModel model;   // 持有model的接口，用于获取数据

    public PresenterA(){
        model = new Model();
    }


    @Override
    public void onCreate(IView view) {
        Log.i(this.getClass().getSimpleName(), "===== onCreate =====");
        this.view = view;
//        MyApplication.getPresenterComponent().inject();
    }

    @Override
    public void performOnClick() {

        // 3.响应按钮点击事件，开启子线程加载数据

        //     将分配线程的职责全部交给Presenter，便于维护管理
        //      -开启子线程通过model下载数据
        //      -通过runOnUiThread或者handler，调用view的方法修改界面

        new AsyncTask<Void,Integer,Integer>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                view.showDialog();
                Log.i("开始下载数据...", "onPreExecute");
            }

            @Override
            protected Integer doInBackground(Void... params) {
                Integer result;
                for (int i = 1; i < 11; i++) {
                    publishProgress(i);
                    model.getDataFromNet();
                    Log.i("模拟下载数据...", "doInBackground: "+i);
                }
                Random random=new Random();
                result=random.nextInt(4);
                return result;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                view.setDialogProgress(values[0]);
            }


            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                // 5.将数据返回给presenter，然后将数据传递给view显示数据
                view.cancelDialog();
                view.setData(integer);
            }

        }.execute();

    }

}
