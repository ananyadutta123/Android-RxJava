# Android-RxJava

Android studio implementation of RxJava

You are presented with the included library project1library.aar with definitions for two basic classes, Element and Item, and a Lib interface (Implemented with included class LibImpl which starts emitting Elements once constructed) containing two asynchronous functions:  
•	Observable<Element> getElementsObservable() 
o	Gets an Rx stream of Element objects with increasing ids starting with 1. 
•	Single<List<Item>> queryElementForItems(Element e) 
o	Gets a list of Items for a specific Element with id fields equal. 
 
The Item has a handle() function that takes a random amount of seconds between 10 and 30 to process (blocking call). The goal is to process each Item in an Element until a new Element is delivered in which case you need to fully process the Item you're on and then switch to the Items from the new Element. Note: it is important to make sure that the current Item being processed is not aborted when a new Element is emitted, but rather that the processing finishes fully BEFORE starting to process another Item. 
 
Create a MainActivity that constructs a LibImpl class in onCreate(). Display some logs to the screen that indicate when Elements are received, and when Items are begun and finished processing. Don’t worry about processing onPause/onStop/onResume lifecycle events. In onDestroy() stop processing Items immediately and clean up any Rx objects. 
