package com.javatab.examples;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Example3 {
    public static void main(String[] args) throws InterruptedException {
        Observable.just("anh", "anhpt", "anhpham")
                .subscribeOn(Schedulers.newThread())
                .doOnNext(c -> System.out.println("subscribeOn: thread " + Thread.currentThread().getName()))
                .map(String::length)
                .observeOn(Schedulers.computation())
                .filter(i -> i > 1)
                .subscribe(i -> System.out.println("observeOn: thread " + Thread.currentThread().getName() + " and item length " + i)
                        , Throwable::printStackTrace,() -> System.out.println("Complete"));
        Thread.sleep(3000);
    }
}
