package restservice.pojo.userPatch.request;

public class PatchRequest {

    private Integer age;

    public Integer getAge() {
        return age;
    }

    public static class Builder {
        private PatchRequest newPatchRequest;

        public Builder() {
            newPatchRequest = new PatchRequest();
        }

        public Builder age(Integer age) {
            newPatchRequest.age = age;
            return this;
        }

        public PatchRequest build(){
            return newPatchRequest;
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "newPatchRequest=" + newPatchRequest +
                    '}';
        }
    }
}
