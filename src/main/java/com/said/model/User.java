package com.said.model;


public class User {
    private long id;
    private String name;
    private String lastName;
    private byte age;

    public User() {}

    public User(String name, String lastName, byte age) {
        this.age = age;
        this.lastName = lastName;
        this.name = name;
    }

    public User(long id, String name, String lastName, byte age) {
        this.id = id;
        this.age = age;
        this.lastName = lastName;
        this.name = name;
    }

    public long getId() { return id;}
    public void setId(long id) { this.id = id;}

    public String getName() { return name;}
    public void setName(String name) {this.name = name;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) { this.lastName = lastName;}

    public byte getAge() { return age;}
    public void setAge(byte age) { this.age = age;}

    @Override
    public boolean equals(Object obj) {
        if( this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User that = (User) obj;
        return (this.getName() == that.getName()) &&
                (this.getLastName() == that.getLastName()) &&
                (this.getAge() == that.getAge());
    }
}
