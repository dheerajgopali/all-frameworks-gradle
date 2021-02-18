package com.dheeraj.learning.afg.spock;

import com.google.inject.Inject;
import java.util.Date;

public class Service1 {

    Service2 service2;

    @Inject
    public Service1(Service2 service2) {
        this.service2 = service2;
    }

    public Integer add(Integer a, Integer b) {
        return a+b;
    }

    public Integer addv2(Integer a, Integer b) {
        return (a+b) * service2.square(a);
    }

    public Integer addv3(Integer a, Integer b) {
        return (a+b) * service2.square(a) * service2.square(a);
    }
}
