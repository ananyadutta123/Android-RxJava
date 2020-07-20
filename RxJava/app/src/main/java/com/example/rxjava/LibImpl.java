package com.example.rxjava;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class LibImpl implements ILib {


    public static final int NumberOfElements = 10;
    private static final String TAG = "LibImplementation";
    private int NumberOfItems = 5;
    List<Element> elements;
    @Override
    public Observable<Element> getElementsObservable() {

    //Creating list of elements

         elements = createNewList();

        Observable<Element> elementObservable =  Observable.fromIterable(elements)
                .subscribeOn(Schedulers.io()) // Which thread to work on
                .observeOn(AndroidSchedulers.mainThread());

            return elementObservable;
    }

    private List<Element> createNewList() {

        List<Element> elements = new ArrayList<>(NumberOfElements);
        for (int i = 1; i <= NumberOfElements ; i++) {
            Element element = new Element(i,createItems());
            elements.add(element);


        }
        return elements;
    }

    private List<Item> createItems() {

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < NumberOfItems; i++) {
            Item item = new Item(i);
            items.add(item);
        }

        return items;
    }

    @Override
    public Single<List<Item>> queryElementForItems(Element e) {
        for (Element el :
                elements) {
            if (el.getId() == e.getId())
                return new Single<List<Item>>() {
                    @Override
                    protected void subscribeActual(@NonNull SingleObserver<? super List<Item>> observer) {
                                       Log.i(TAG,"Processing element");

        }
    };
        }

        return null;
    }
}
