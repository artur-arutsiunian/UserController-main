package restservice.pojo.userDelete;

public class DeleteRequest {

    private Integer playerId;

    public Integer getPlayerId() {
        return playerId;
    }

    public static class Builder {
        private DeleteRequest newDeleteRequest;
        public Builder() {
            newDeleteRequest = new DeleteRequest();
        }

        public DeleteRequest.Builder playerId(Integer playerId) {
            newDeleteRequest.playerId = playerId;
            return this;
        }

        public DeleteRequest build() {
            return newDeleteRequest;
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "newDeleteRequest=" + newDeleteRequest +
                    '}';
        }
    }
}