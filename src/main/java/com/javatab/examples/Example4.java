package com.javatab.examples;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.Random;

public class Example4 {
    public static void main(String[] args) throws InterruptedException {

        Observable<String> stringStream = Observable.just("long", "longer", "longest","leo","short");
        stringStream.flatMap(v ->
                        performLongOperation(v)
                                .doOnNext(s -> System.out.println("processing item on thread " + Thread.currentThread().getName()))
                                .subscribeOn(Schedulers.newThread()))
                .subscribe(length -> System.out.println("received item length " + length + " on thread " + Thread.currentThread().getName()));

        Thread.sleep(10000);
    }


    protected static Observable<Integer> performLongOperation(String v) {
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(3) * 1000);
            return Observable.just(v.length());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
