package com.fxj.simplerect;

/**
 * 用来创建被观察者对象
 */
public class ObservableCreate<T> extends Observable<T>{

    ObservableOnSubscribe<T>  source;

    public ObservableCreate(ObservableOnSubscribe<T> source){
        this.source=source;
    }

    @Override
    public void subscribe(Observer observer) {
        super.subscribe(observer);
    }

    //如果订阅功能要实现，在这里面就应该通知observer消息更新了
    @Override
    protected void subscribeActual(Observer observer) {
        //调用observer.onNext();等方法
        CreateEmitter<T> parent=new CreateEmitter<>(observer);
        observer.onSubscribe();
        //把发射器和被观察者绑定在一起(订阅)
        source.subscribe(parent);

    }

    static final class CreateEmitter<T> implements Emitter<T>{

        Observer<T> observer;

        public CreateEmitter(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void onNext(T t) {
            observer.onNext(t);
        }

        @Override
        public void onError(Throwable throwable) {
            observer.onError(throwable);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }
    }
}
