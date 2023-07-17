package restservice.pojo.userPatch;

public class PatchReq {

    private int age;

    public PatchReq(int age) {
        this.age = age;
    }

    public PatchReq() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
