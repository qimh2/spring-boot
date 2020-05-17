package com.qimh.annonation;

public class PersonDao {
    private Person person;

    public Person getPerson() {
        return person;
    }

    /**
     * 将username为zhongfucheng，age为20的Person对象注入到setPerson方法中
     */
    @InjectPerson(username = "zhongfucheng",age = 20)
    public void setPerson(Person person) {
        this.person = person;
    }
}
