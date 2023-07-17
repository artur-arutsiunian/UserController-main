package restservice.pojo.userCreate;

import java.util.Objects;

public class CreateRes {
    private int age;
    private Gender gender;
    private int id;
    private String login;
    private String password;
    private Role role;
    private String screenName;

    public CreateRes(int age, Gender gender, int id, String login, String password, Role role, String screenName) {
        this.age = age;
        this.gender = gender;
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.screenName = screenName;
    }

    public CreateRes() {
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
        CreateRes createRes = (CreateRes) o;
        return age == createRes.age && id == createRes.id && gender == createRes.gender && Objects.equals(login, createRes.login) && Objects.equals(password, createRes.password) && role == createRes.role && Objects.equals(screenName, createRes.screenName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, gender, id, login, password, role, screenName);
    }

    @Override
    public String toString() {
        return "CreateRes{" +
                "age=" + age +
                ", gender=" + gender +
                ", id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", screenName='" + screenName + '\'' +
                '}';
    }
}
