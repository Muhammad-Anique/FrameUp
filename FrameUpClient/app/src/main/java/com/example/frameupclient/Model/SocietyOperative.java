package com.example.frameupclient.Model;

public class SocietyOperative {


    private int id;
    String operativeRoll;
    String societyId;
    int operativeType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperativeRoll() {
        return operativeRoll;
    }

    public void setOperativeRoll(String operativeRoll) {
        this.operativeRoll = operativeRoll;
    }

    public String getSocietyId() {
        return societyId;
    }

    public void setSocietyId(String societyId) {
        this.societyId = societyId;
    }

    public int getOperativeType() {
        return operativeType;
    }

    public void setOperativeType(int operativeType) {
        this.operativeType = operativeType;
    }

    @Override
    public String toString() {
        return "SocietyOperative{" +
                "id=" + id +
                ", operativeRoll='" + operativeRoll + '\'' +
                ", societyId='" + societyId + '\'' +
                ", operativeType=" + operativeType +
                '}';
    }
}
