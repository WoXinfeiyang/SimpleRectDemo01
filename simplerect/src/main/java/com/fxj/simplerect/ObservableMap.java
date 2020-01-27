package com.fxj.simplerect;

public class ObservableMap<T,U> extends AbstractObservableWithUpstream<T,U> {

    final Function<T,U> function;

    /**Map操作符*/
    public ObservableMap(ObservableSource<T> source, Function<T, U> function) {
        super(source);
        this.function = function;
    }

    @Override
    protected void subscribeActual(Observer<U> observer) {
        source.subscribe(new MapObserver<>(observer,function));
    }

    static final class MapObserver<T,U> extends BaseicFuseableObserver<T,U>{
        final Function<T,U> mapper;

        public MapObserver(Observer<U> actual, Function<T, U> mapper) {
            super(actual);
            this.mapper = mapper;
        }

        @Override
        public void onNext(T t) {
            U apply=mapper.apply(t);
            actual.onNext(apply);
        }
    }
}
