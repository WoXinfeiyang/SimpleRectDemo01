package com.fxj.simplerect;

public abstract class AbstractObservableWithUpstream<T,R> extends Observable<R>{

    protected  final ObservableSource<T> source;

    public AbstractObservableWithUpstream(ObservableSource<T> source) {
        this.source = source;
    }
}
