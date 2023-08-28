package restservice.pojo.userGet.request;

public class GetRequest {

    private Integer playerId;

    public Integer getPlayerId() {
        return playerId;
    }

    public static class Builder {
        private GetRequest newGetRequest;

        public Builder() {
            newGetRequest = new GetRequest();
        }

        public GetRequest.Builder playerId(Integer playerId) {
            newGetRequest.playerId = playerId;
            return this;
        }

        public GetRequest build() {
            return newGetRequest;
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "newGetRequest=" + newGetRequest +
                    '}';
        }
    }
}
