package com.dheeraj.learning.afg.spock

import spock.lang.Specification
import java.util.Date

/**
 *
 */
class SpockTest extends Specification {
    Service1 service1
    Service2 service2

    def setup() {
        service2 = Mock(Service2)
        service1 = new Service1(service2)
    }

    def 'stubbing 1'() {
        given:

        service2.square(10) >> 50

        expect:
        service1.addv2(10,20) == 1500
    }

    def 'stubbing 2'() {
        given:
        service2 = Mock() //Not necessary to specify the mock type.
        service1 = new Service1(service2)
        service2.square(10) >> 50

        when:
        def result = service1.addv2(10,20)

        then:
        result == 1500
    }

    def 'stubbing 3'() {
        given:
        service2 = Mock(Service2)
        service1 = new Service1(service2)
        service2.square(_) >> 50

        when:
        def result = service1.addv2(10,20)

        then:
        result == 1500
    }

    def 'stubbing 4'() {
        given:
        service2 = Mock(Service2)
        service1 = new Service1(service2)
        service2.square(_) >>> [10, 10]

        when:
        def result = service1.addv2(10,10)

        then:
        result == 200
    }

    def 'verification 1'() {
        given:
        service2 = Mock(Service2)
        service1 = new Service1(service2)
        service2.square(_) >> 10 //If you want to verify the occurrences, put this in then: block.

        when:
        def result = service1.addv2(10,10)

        then:
        result == 200
    }

    def 'verification 2'() {
        given:
        service2 = Mock(Service2)
        service1 = new Service1(service2)

        when:
        def result = service1.addv3(10,10)

        then:
        result == 2000
        //1 * service1.add(_, _) Can't verify an actual instance invocations.
        2 * service2.square(_) >>> [10, 10] //If stubbed method is verified for no of invocations, stubs have to be put
        // here(then:) block and not in (given:) block. If you do, the stubs wont work.
    }


    def 'test addition'() {
        given:
        def a=10
        def b=20

        when:
        def c = service1.add(a,b)

        then:
        c == 30
    }

    def 'test addition expect block'() {
        expect:
        service1.add(10, 20) == 30
    }

    def 'List assertion in groovy syntax'() {
        given:
        def list = [1, 2, 3, 4]

        when:
        list.remove(0)

        then:
        list == [2, 3, 4]
    }

    def 'Exceptions 1'() {
        given:
        def list = [1, 2, 3, 4]

        when:
        list.remove(20)

        then:
        thrown(IndexOutOfBoundsException)
        list.size() == 4
    }

    //Data Driven Testing or Parameterized testing
    def "numbers to the power of two"() {
        expect:
        Math.pow(a, b) == c

        where:
        a | b | c
        1 | 2 | 1
        2 | 2 | 4
        3 | 2 | 9
    }
}
