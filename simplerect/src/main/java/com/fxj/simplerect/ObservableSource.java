package com.fxj.simplerect;

/**
 * 抽象被观察者
 */
public interface ObservableSource<T> {
    //notify()  将给定的Observer订阅功能定义出来
    void subscribe(Observer<T> observer);
}
