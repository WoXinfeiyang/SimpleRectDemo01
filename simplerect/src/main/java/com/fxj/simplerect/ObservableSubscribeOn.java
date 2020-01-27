package com.fxj.simplerect;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObservableSubscribeOn<T> extends AbstractObservableWithUpstream<T,T> {

    private static ExecutorService executorService= Executors.newCachedThreadPool();

    /**被观察者的线程切换*/
    public ObservableSubscribeOn(ObservableSource<T> source) {
        super(source);
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        final SubscribeOnObserver<T> parent=new SubscribeOnObserver<>(observer);
        observer.onSubscribe();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                source.subscribe(parent);
            }
        });
    }

    static final class SubscribeOnObserver<T> implements  Observer<T>{
        final Observer<T> actual;

        public SubscribeOnObserver(Observer<T> actual) {
            this.actual = actual;
        }

        @Override
        public void onSubscribe() {
            actual.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            actual.onNext(t);
        }

        @Override
        public void onError(Throwable e) {
            actual.onError(e);
        }

        @Override
        public void onComplete() {
            actual.onComplete();
        }
    }
}
