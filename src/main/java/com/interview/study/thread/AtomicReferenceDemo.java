package com.interview.study.thread;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author liwenchang
 * @create 2020-10-31 22:37
 */
@Getter
@ToString
@AllArgsConstructor
class user{
    String username;
    int age;
}
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        user z3 = new user("z3",11);
        user li4 = new user("z3", 11);

        AtomicReference<user> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);

        System.out.println(atomicReference.compareAndSet(z3, li4) + atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3, li4) + atomicReference.get().toString());
    }
}
