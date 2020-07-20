package com.example.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;

public class MainActivity extends Activity {

    private final static String TAG="MyApp";
    //private String greeting="Hello from RxJava";
     Observable<Element> elementObservable;
    //    private Observer<String> myObserver;
    private TextView textView;
    Disposable disposable;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tvGreeting);
        final LibImpl li = new LibImpl();

        elementObservable = li.getElementsObservable();

        Toast.makeText(this, "Entered here", Toast.LENGTH_SHORT).show();
        elementObservable.subscribe(new Observer<Element>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG,"onSubscribe invoked");

            }



            @Override
            public void onNext(@NonNull Element element) {

                Log.i(TAG, "Entered on next.....");
                Toast.makeText(MainActivity.this, "Entered on Next", Toast.LENGTH_SHORT).show();
                Single<List<Item>> items = li.queryElementForItems(element);

                 disposable = items
                        .subscribeWith(new DisposableSingleObserver<List<Item>>() {
                                           @Override
                                           public void onSuccess(@NonNull List<Item> items) {
                                               for (Item item :
                                                       items) {
                                                   try {
                                                       item.handle();
                                                   } catch (InterruptedException e) {
                                                       e.printStackTrace();
                                                   }
                                               }
                                           }

                                           @Override
                                           public void onError(@NonNull Throwable e) {

                                           }
                                       });


//                items.subscribe(new Disposos)



        }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
//        myObservable=Observable.just(greeting);

//        //receive elements here-TODO
//        Lib.LibImpl instance = new Lib.LibImpl();//constructing LibImpl inside  onCreate
//        Log.d("myTag", "Elements received");//log messages when elements received
//
//        //receive items here
//        Log.d("myTag", "Items begins processing...");//log messages when items received
//        Log.d("myTag", "Items finished processing. Cheers!");
//
//
//        myObserver=new Observer<String>(){
//
//
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.i(TAG, "OnSubscribe invoked");
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.i(TAG, "OnNext invoked");
//                textView.setText(s);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.i(TAG, "OnError invoked");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.i(TAG, "OnComplete invoked");
//
//            }
//        };
//        myObservable.subscribe(myObserver);
//

        }
        //OnDestroy
    private void onDestroy(int myTag){

        if(disposable != null && !disposable.isDisposed()) {
            Log.i(TAG, "Cleaning up , exiting...");
            disposable.dispose();
            Toast.makeText(MainActivity.this,"Cleaning up and exiting...", Toast.LENGTH_SHORT).show();
        }
//        onDestroy(
//                Log.d("myTag", "exiting...")
//                //stop processing Items immediately and clean up any Rx objects-TODO
//        );


    }

}