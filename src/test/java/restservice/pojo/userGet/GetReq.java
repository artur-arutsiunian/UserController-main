package restservice.pojo.userGet;

public class GetReq {

    private int playerId;

    public GetReq(int playerId) {
        this.playerId = playerId;
    }

    public GetReq() {
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
