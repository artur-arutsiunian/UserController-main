package restservice.pojo.userDelete;

public class DeleteReq {

    private int playerId;

    public DeleteReq(int playerId) {
        this.playerId = playerId;
    }

    public DeleteReq() {
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
