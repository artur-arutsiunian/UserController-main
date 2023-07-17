package restservice.pojo.userPatch;

import restservice.pojo.userCreate.Gender;
import restservice.pojo.userCreate.Role;

import java.util.Objects;

public class PatchRes {

    private int age;
    private Gender gender;
    private int id;
    private String login;
    private Role role;
    private String screenName;

    public PatchRes(int age, Gender gender, int id, String login, Role role, String screenName) {
        this.age = age;
        this.gender = gender;
        this.id = id;
        this.login = login;
        this.role = role;
        this.screenName = screenName;
    }

    public PatchRes() {
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
        PatchRes patchRes = (PatchRes) o;
        return age == patchRes.age && id == patchRes.id && gender == patchRes.gender && Objects.equals(login, patchRes.login) && role == patchRes.role && Objects.equals(screenName, patchRes.screenName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, gender, id, login, role, screenName);
    }

    @Override
    public String toString() {
        return "PatchRes{" +
                "age=" + age +
                ", gender=" + gender +
                ", id=" + id +
                ", login='" + login + '\'' +
                ", role=" + role +
                ", screenName='" + screenName + '\'' +
                '}';
    }
}
