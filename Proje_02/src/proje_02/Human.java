package proje_02;

import java.util.UUID;

public class Human {

    private String name, surname, job_description;
    private String id;
    private int age;

   
    public Human(){
    
}

    public Human(String name, String surname, String job, int age) {
        this.id = UUID.randomUUID().toString(); //Unique Identifier
        this.name = name;
        this.surname = surname;
        this.job_description = job;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
