package com.example.frameupclient.Model;

public class Person {

    private int accountId;
    private String name;
    private String email;
    private String rollNo;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }


    public void set_Name_Email_Roll(String name, String email, String roll)
    {
        setEmail(email);
        setName(name);
        setRollNo(roll);
    }

    @Override
    public String toString() {
        return "Person{" +
                "accountId=" + accountId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", rollNo='" + rollNo + '\'' +
                '}';
    }
}
