package com.thohao.rxjava_rxandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    Disposable disposable;
    CompositeDisposable compositeDisposable;//quan ly dc tat cả các subdiscriptinon



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*List<String>arrNames=new ArrayList<>();
        //List chỉ xem, kg thể CRUD , là lớp con của ArrayList, kg có khả năng xử lý bất đồng bộ
        arrNames.add("Hao Pham");
        //Log.d("BBB", arrNames.size()+"");
        Iterator<String> iterator = arrNames.iterator();//kiem tra du lieu trong mảng nen dùng Iterator
        while (iterator.hasNext()) {
            Log.d("BBB", iterator.next());
        }*/

//khởi tạo RxJava voi Observable
        Observable<String>observableNames=Observable.just("Hao","Henry","Philipp");
        //subcribe
        /*observableNames.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable=d;

            }

            @Override
            public void onNext(String s) {
                Log.d("BBB", s);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                //Log.d("BBB", disposable.isDisposed()+"");
                disposable.dispose();


            }
        });*/

        observableNames
                .subscribeOn(Schedulers.newThread())//chay xog giu lai thread, dc su dung pho bien nhat ,
                .observeOn(AndroidSchedulers.mainThread())//đặc trưng chính của RxAndroid
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                       // compositeDisposable.add(d);

                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
