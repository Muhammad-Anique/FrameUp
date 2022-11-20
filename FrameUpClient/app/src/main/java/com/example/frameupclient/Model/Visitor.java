package com.example.frameupclient.Model;
public class Visitor extends Person {

    String OTP;
    String password;
    boolean isVerified;
    String accountStatus;
    String joiningDate;
    String phoneNumber;

    String profileUrl;

    String profileType;


    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getProfileType() {
        return profileType;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    Gender gender;

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender.toString();
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "OTP='" + OTP + '\'' +
                ", password='" + password + '\'' +
                ", isVerified=" + isVerified +
                ", accountStatus='" + accountStatus + '\'' +
                ", joiningDate='" + joiningDate + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                ", profileType='" + profileType + '\'' +
                ", gender=" + gender +
                '}';
    }
}
