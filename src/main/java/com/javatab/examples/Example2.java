package com.javatab.examples;

import io.reactivex.Observable;
import org.springframework.stereotype.Service;

@Service
public class Example2 {

    static class Person {
        enum SEX {
            MALE, FEMALE
        }

        private String name;
        private int age;
        private SEX sex;

        Person(String name, int age, SEX sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }
    }

    public static void main(String[] args) {

        Observable<Person> firstObservable = Observable.defer(() -> Observable.just(new Person("Shayan Tahir", 22, Person.SEX.MALE)));
        Observable<Person> secondObservable = Observable.defer(() -> Observable.just(new Person("Bilal Ahmed", 25, Person.SEX.MALE)));
        secondObservable.mergeWith(firstObservable)
                .startWith(new Person("Jon Doe", 20, Person.SEX.FEMALE))
                .map(person -> person.name)
                .subscribe(name -> System.out.println(name)
                        , Throwable::printStackTrace, () -> System.out.println("Complete"));
    }

}
