package com.fxj.simplerect;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;


public class ObservableObserveOn<T> extends AbstractObservableWithUpstream<T, T> {
    /**观察者的线程切换*/
    public ObservableObserveOn(ObservableSource<T> source) {
        super(source);
    }

    @Override
    protected void subscribeActual(final Observer<T> observer) {
        final ObserverOnObserver<T> parent = new ObserverOnObserver<T>(observer);
        source.subscribe(parent);
    }

    static final class ObserverOnObserver<T> implements Observer<T> {
        final Observer< T> actual;
        private Handler handler;

        public ObserverOnObserver(Observer<T> actual) {
            this.actual = actual;
            handler = new Handler(Looper.getMainLooper());
        }

        @Override
        public void onSubscribe( ) {
            actual.onSubscribe();
        }

        @Override
        public void onNext( final T t) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Log.i("MainActivity", "ObserverOnObserver: " + Thread.currentThread().getName());
                    actual.onNext(t);
                }
            });
        }

        @Override
        public void onError( Throwable e) {
            actual.onError(e);
        }

        @Override
        public void onComplete() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    actual.onComplete();
                }
            });
        }


    }
}
