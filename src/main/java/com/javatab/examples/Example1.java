package com.javatab.examples;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.observers.DefaultObserver;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class Example1 {

    public static void main(String[] args) {

        Observer<Integer> observer = new DefaultObserver<Integer>() {
            @Override
            public void onNext(Integer i) {
                System.out.println(i);
                System.out.println(Thread.currentThread());
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Completes");
            }

        };

        List<Integer> intergerList = Arrays.asList(54, 12, 10, 78, 69, 33, 66, 99, 84);
        Observable.fromIterable(intergerList)
                .filter(i -> i % 2 == 0)
                .sorted()
                .subscribe(observer);


    }
}
