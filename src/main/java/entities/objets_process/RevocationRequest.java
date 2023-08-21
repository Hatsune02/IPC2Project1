package entities.objets_process;

public class RevocationRequest {
    private int id;
    private int finalUserId;
    private String state,detail;

    public RevocationRequest() {
    }
    public RevocationRequest(int id) {
        this.id = id;
    }
    public RevocationRequest(int finalUserId, String state, String detail) {
        this.finalUserId = finalUserId;
        this.state = state;
        this.detail = detail;
    }
    public RevocationRequest(int id, int finalUserId, String state, String detail) {
        this.id = id;
        this.finalUserId = finalUserId;
        this.state = state;
        this.detail = detail;
    }

    public int getId() {return id;}
    public int getFinalUserId() {return finalUserId;}
    public void setFinalUserId(int finalUserId) {this.finalUserId = finalUserId;}
    public String getState() {return state;}
    public void setState(String state) {this.state = state;}
    public String getDetail() {return detail;}
    public void setDetail(String detail) {this.detail = detail;}

    @Override
    public String toString() {
        return "RevocationRequest{" +
                "id=" + id +
                ", finalUserId=" + finalUserId +
                ", state=" + state +
                ", detail=" + detail +
                '}';
    }
}
