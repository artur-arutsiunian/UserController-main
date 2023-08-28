package restservice.pojo.userCreate.response;

import java.util.Objects;

public class CreateResponse {
    private Integer age;
    private String gender;
    private Integer id;
    private String login;
    private String password;
    private String role;
    private String screenName;

        public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getScreenName() {
        return screenName;
    }

    public static class Builder {
        private CreateResponse newCreateResponse;

        public Builder() {
            newCreateResponse = new CreateResponse();
        }

        public Builder age(Integer age) {
            newCreateResponse.age = age;
            return this;
        }

        public Builder gender(String gender) {
            newCreateResponse.gender = gender;
            return this;
        }

        public Builder id(Integer id) {
            newCreateResponse.id = id;
            return this;
        }

        public Builder login(String login) {
            newCreateResponse.login = login;
            return this;
        }

        public Builder password(String password) {
            newCreateResponse.password = password;
            return this;
        }

        public Builder role(String role) {
            newCreateResponse.role = role;
            return this;
        }

        public Builder screenName(String screenName) {
            newCreateResponse.screenName = screenName;
            return this;
        }

        public CreateResponse build() {
            return newCreateResponse;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Builder builder = (Builder) o;
            return Objects.equals(newCreateResponse, builder.newCreateResponse);
        }

        @Override
        public int hashCode() {
            return Objects.hash(newCreateResponse);
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "newCreateResponse=" + newCreateResponse +
                    '}';
        }
    }
}
