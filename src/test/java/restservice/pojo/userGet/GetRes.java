package restservice.pojo.userGet;

import restservice.pojo.userCreate.Gender;
import restservice.pojo.userCreate.Role;

import java.util.Objects;

public class GetRes {

    private int age;
    private Gender gender;
    private int id;
    private String login;
    private String password;
    private Role role;
    private String screenName;

    public GetRes(int age, Gender gender, int id, String login, String password, Role role, String screenName) {
        this.age = age;
        this.gender = gender;
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.screenName = screenName;
    }

    public GetRes() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetRes getRes = (GetRes) o;
        return age == getRes.age && id == getRes.id && Objects.equals(gender, getRes.gender) && Objects.equals(login, getRes.login) && Objects.equals(password, getRes.password) && Objects.equals(role, getRes.role) && Objects.equals(screenName, getRes.screenName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, gender, id, login, password, role, screenName);
    }

    @Override
    public String toString() {
        return "GetRes{" +
                "age=" + age +
                ", gender='" + gender + '\'' +
                ", id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", screenName='" + screenName + '\'' +
                '}';
    }
}
