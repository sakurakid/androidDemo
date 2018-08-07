package com.example.hasee.rxjavademo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                Log.d("233","emit 1");
//                e.onNext(1);
//                Log.d("233","emit 2");
//                e.onNext(2);
//                Log.d("233","emit 3");
//                e.onNext(3);
//                e.onComplete();
//            }
//        }).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d("233", "subscribe");
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                Log.d("233","onNext "+value);
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d("233","error");
//
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d("233","complete");
//
//            }
//        });
//        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                Log.d("233", "Observable thread is : " + Thread.currentThread().getName());
//                Log.d("233", "emit 1");
//                e.onNext(1);
//
//            }
//        });
//
//        Consumer<Integer> consumer = new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                Log.d("233", "Observer thread is :" + Thread.currentThread().getName());
//                Log.d("233", "onNext: " + integer);
//            }
//        };
//
//        observable.subscribeOn(Schedulers.newThread())//上游指定的发送事件的线程
//                .observeOn(AndroidSchedulers.mainThread())//下游指定的接收的线程
//                .subscribe(consumer);



//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
//            }
//        }).map(new Function<Integer, String>() {
//            @Override
//            public String apply(Integer integer) throws Exception {
//                return "This is result" + integer;
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.d("233",s);
//            }
//        });




//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
//            }
//        }).concatMap(new Function<Integer, ObservableSource<String>>() {
//            @Override
//            public ObservableSource<String> apply(Integer integer) throws Exception {
//                final List<String> list = new ArrayList<>();
//                for (int i = 0;i < 3;i++){
//                    list.add("value "+integer);
//                }
//                return Observable.fromIterable(list).delay(50, TimeUnit.MICROSECONDS);
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.d("233",s);
//            }
//        });

//        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                Log.d("233","emit 1");
//                e.onNext(1);
//                Thread.sleep(1000);
//
//                Log.d("233","emit 2");
//                e.onNext(2);
//                Thread.sleep(1000);
//                Log.d("233","emit 3");
//                e.onNext(3);
//                Thread.sleep(1000);
//                Log.d("233","emit 4");
//                e.onNext(4);
//                Thread.sleep(1000);
//                Log.d("233","emit complete1");
//                e.onComplete();
//            }
//        }).subscribeOn(Schedulers.io());
//
//        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                Log.d("233","emit A");
//                e.onNext("A");
//                Thread.sleep(1000);
//                Log.d("233","emit B");
//                e.onNext("B");
//                Thread.sleep(1000);
//                Log.d("233","emit C");
//                e.onNext("C");
//                Thread.sleep(1000);
//                Log.d("233","emit complete2");
//                e.onComplete();
//            }
//        }).subscribeOn(Schedulers.io());
//        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
//            @Override
//            public String apply(Integer integer, String s) throws Exception {
//                return integer + s;
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d("233","onSubscribe");
//            }
//
//            @Override
//            public void onNext(String value) {
//                Log.d("233","onNext"+value);
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d("233","OnErron");
//
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d("233","oncomplete");
//
//            }
//        });
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for(int i = 0;;i++){
                    e.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("A");
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer+s;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("233", s);
            }

        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.w("233",throwable);
            }
        });
  }

}
