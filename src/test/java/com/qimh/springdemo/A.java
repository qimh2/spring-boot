package com.qimh.springdemo;

public class A {

}

class

Dervied extends Base {

    private String name = "Java3y";


    public Dervied() {
        tellName2();
        printName();
    }


    public void tellName2() {

        System.out.println("Dervied tell name: "+ name );

    }


    public void printName() {
        System.out.println("Dervied print name: "+name);

    }


    public

    static void
    main
        (
            String
                []
                args
        ) {

        new

            Dervied
            ();

    }
}

class

Base {


    private

    String
        name
        =

        "公众号";


    public Base
        () {
        tellName
            ();
        printName
            ();

    }


    public void
    tellName
        () {

        System
            .
                out
            .
                println
                    (
                        "Base tell name: "

                            +
                            name
                    );

    }


    public void
    printName
        () {

        System
            .
                out
            .
                println
                    (
                        "Base print name: "

                            +
                            name
                    );

    }
}
