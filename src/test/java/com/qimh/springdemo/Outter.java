package com.qimh.springdemo;

public class Outter {
    private InnerClass innerClass;

    private StaticInnerClass staticInnerClass;

    public StaticInnerClass getStaticInnerClass() {
        return staticInnerClass;
    }

    public void setStaticInnerClass(StaticInnerClass staticInnerClass) {
        this.staticInnerClass = staticInnerClass;
    }

    public InnerClass getInnerClass() {
        return innerClass;
    }

    public void setInnerClass(InnerClass innerClass) {
        this.innerClass = innerClass;
    }

    class InnerClass {
        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    static class StaticInnerClass {
        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static void main(String[] args) {
        Outter myOuter = new Outter();
        Outter.InnerClass myInner = myOuter.new InnerClass();
    }
}
