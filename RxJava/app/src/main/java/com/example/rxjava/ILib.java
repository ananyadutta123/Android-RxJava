package com.example.rxjava;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;


public interface ILib {

     Observable<Element> getElementsObservable();
     Single<List<Item>> queryElementForItems(Element e);

}
