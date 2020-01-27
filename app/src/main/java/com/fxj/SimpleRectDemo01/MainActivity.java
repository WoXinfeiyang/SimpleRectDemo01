package com.fxj.SimpleRectDemo01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.fxj.simplerect.Emitter;
import com.fxj.simplerect.Function;
import com.fxj.simplerect.Observable;
import com.fxj.simplerect.ObservableOnSubscribe;
import com.fxj.simplerect.Observer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(Emitter<String> emitter) {
                        emitter.onNext("网络连接成功+");
                    }
                }).map(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s+"数据库验证成功+";
                    }
                }).map(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s+"文件系统验证成功+";
                    }
                }).map(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        System.out.println("上游："+Thread.currentThread().getName());
                        return s+"用户名密码正确+";
                    }
                }).subscribeOn()
                        .observeOn()
                        .subscribe(new Observer() {
                            @Override
                            public void onSubscribe() {
                                System.out.println("订阅成功："+Thread.currentThread().getName());
                            }
                            @Override
                            public void onNext(Object o) {
                                System.out.println("下游："+Thread.currentThread().getName());
                                System.out.println("可以登录！"+(String)o);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
        }
    }
}
