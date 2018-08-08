package com.example.hasee.rxjavademo1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private Button start;
    private Button request;
    private Subscription msubscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button)findViewById(R.id.btn_Start);
        request = (Button)findViewById(R.id.btn_REQUEST);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demo();
            }
        });
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request(1);
            }
        });

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
//        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                for(int i = 0;;i++){
//                    e.onNext(i);
//                }
//            }
//        }).subscribeOn(Schedulers.io());
//
//        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                e.onNext("A");
//            }
//        }).subscribeOn(Schedulers.io());
//
//        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
//            @Override
//            public String apply(Integer integer, String s) throws Exception {
//                return integer+s;
//            }
//        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.d("233", s);
//            }
//
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                Log.w("233",throwable);
//            }
//        });



//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                for(int i = 0;;i++){
//                    e.onNext(i);
//                    Thread.sleep(2000);
//                }
//            }
//        }).subscribeOn(Schedulers.io())
////                .filter(new Predicate<Integer>() {
////                    @Override
////                    public boolean test(Integer integer) throws Exception {
////                        return integer %10 ==0;
////                    }
////                })
//             //   .sample(2, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.d("233",""+integer);
//                    }
//                });



  }
    public  void request(long n) {
        msubscription.request(n); //在外部调用request请求上游
    }

    public void demo(){
      Flowable<Integer> upstream = Flowable.create(new FlowableOnSubscribe<Integer>() {
          @Override
          public void subscribe(FlowableEmitter<Integer> e) throws Exception {
              Log.d("233","emit 1");
              e.onNext(1);
              Log.d("233","emit 2");
              e.onNext(2);
              Log.d("233","emit 3");
              e.onNext(3);
              Log.d("233","emit comlete");
              e.onComplete();
          }
      }, BackpressureStrategy.ERROR)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread());//增加一个参数

      final Subscriber<Integer> downstream = new Subscriber<Integer>() {
          @Override
          public void onSubscribe(Subscription s) {
              Log.d("233","onsubscribe");
               msubscription = s;
          }

          @Override
          public void onNext(Integer integer) {
              Log.d("233", "onNext: " + integer);


          }

          @Override
          public void onError(Throwable t) {
              Log.w("233", "onError: ", t);


          }

          @Override
          public void onComplete() {
              Log.d("233", "onComplete");


          }
      };
      upstream.subscribe(downstream);
  }

}
