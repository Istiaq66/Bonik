package com.istiaq66.bonik;
public class User {
    private String  profilepic, username, mail, password, userid;




    public User(String profilepic, String username, String mail, String password, String userid) {
        this.profilepic = profilepic;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.userid = userid;
    }

    //default constructor
    public User(){}

    //Signup Constructor
    public User(String username, String mail, String password) {

        this.username = username;
        this.mail = mail;
        this.password = password;

    }


    public String getProfilepic() {
        return profilepic;
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getUserid() {
        return userid;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }


}
