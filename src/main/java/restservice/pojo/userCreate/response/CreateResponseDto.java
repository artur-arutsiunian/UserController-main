package restservice.pojo.userCreate.response;

import java.util.List;
import java.util.Objects;

public class CreateResponseDto {

    private List<Files> files;

    public CreateResponseDto(CreateResponse res) {
            this.files = List.of(new Files(
                res.getAge(),
                res.getGender(),
                res.getLogin(),
                res.getPassword(),
                res.getRole(),
                res.getScreenName()));
    }

    public CreateResponseDto(List<Files> files) {
        this.files = files;
    }

    public CreateResponseDto() {
    }

    public List<Files> getFiles() {
        return files;
    }

    public void setFiles(List<Files> files) {
        this.files = files;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateResponseDto that = (CreateResponseDto) o;
        return Objects.equals(files, that.files);
    }

    @Override
    public int hashCode() {
        return Objects.hash(files);
    }

    @Override
    public String toString() {
        return "CreateResponseDto{" +
                "files=" + files +
                '}';
    }

    public static class Files{

        private Integer age;
        private String gender;
        private String login;
        private String password;
        private String role;
        private String screenName;


        public Files(Integer age, String gender, String login, String password, String role, String screenName) {
            this.age = age;
            this.gender = gender;
            this.login = login;
            this.password = password;
            this.role = role;
            this.screenName = screenName;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
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

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
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
            Files files = (Files) o;
            return age == files.age && Objects.equals(gender, files.gender) && Objects.equals(login, files.login) && Objects.equals(password, files.password) && Objects.equals(role, files.role) && Objects.equals(screenName, files.screenName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, gender, login, password, role, screenName);
        }

        @Override
        public String toString() {
            return "Files{" +
                    "age=" + age +
                    ", gender='" + gender + '\'' +
                    ", login='" + login + '\'' +
                    ", password='" + password + '\'' +
                    ", role='" + role + '\'' +
                    ", screenName='" + screenName + '\'' +
                    '}';
        }
    }

}
