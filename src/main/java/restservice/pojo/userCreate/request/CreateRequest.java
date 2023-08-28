package restservice.pojo.userCreate.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import restservice.pojo.userCreate.RequestModel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CreateRequest implements RequestModel {

    private String age;
    private String gender ;
    private String login;
    private String password;
    private String role;
    private String screenName;

    public static class Builder{
        private CreateRequest newCreateRequest;

        public Builder() {
            newCreateRequest = new CreateRequest();
        }

        public Builder age(String age){
            newCreateRequest.age = age;
            return this;
        }

        public Builder gender(String gender){
            newCreateRequest.gender = gender;
            return this;
        }

        public Builder login(String login){
            newCreateRequest.login = login;
            return this;
        }

        public Builder password(String password){
            newCreateRequest.password = password;
            return this;
        }

        public Builder role(String role){
            newCreateRequest.role = role;
            return this;
        }

        public Builder screenName(String screenName){
            newCreateRequest.screenName = screenName;
            return this;
        }

        public CreateRequest build(){
            return newCreateRequest;
        }

        public Builder roleOnly(String role, CreateRequest originalRequest) {
            newCreateRequest = originalRequest;
            newCreateRequest.role = role;
            return this;
        }

        public Builder ageOnly(String age, CreateRequest originalRequest) {
            newCreateRequest = originalRequest;
            newCreateRequest.age = age;
            return this;
        }

        public Builder loginOnly(String login, CreateRequest originalRequest) {
            newCreateRequest = originalRequest;
            newCreateRequest.login = login;
            return this;
        }

        public Builder passwordOnly(String password, CreateRequest originalRequest) {
            newCreateRequest = originalRequest;
            newCreateRequest.password = password;
            return this;
        }

        public Builder screenNameOnly(String screenName, CreateRequest originalRequest) {
            newCreateRequest = originalRequest;
            newCreateRequest.screenName = screenName;
            return this;
        }

        public Builder genderOnly(String gender, CreateRequest originalRequest) {
            newCreateRequest = originalRequest;
            newCreateRequest.gender = gender;
            return this;
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "newCreateReq=" + newCreateRequest +
                    '}';
        }
    }

    @Override
    public Map<String, Object> toMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return objectMapper.convertValue(this, Map.class);
    }
}
