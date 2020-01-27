package com.fxj.simplerect;

/**
 * 抽象观察者
 */
public interface Observer<T> {
    //用于订阅成功的回调
    void onSubscribe();
    //udpate()  收到消息
    void onNext(T t);
    //出错的回调
    void onError(Throwable e);
    //从订阅到消息发送全部完成
    void onComplete();
}
