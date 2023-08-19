package objects.objets_process;

public class RevocationRequest {
    private final int ID;
    private int finalUserID;
    private String state,detail;

    public RevocationRequest(int ID, int finalUserID, String state, String detail) {
        this.ID = ID;
        this.finalUserID = finalUserID;
        this.state = state;
        this.detail = detail;
    }

    public int getID() {return ID;}
    public int getFinalUserID() {return finalUserID;}
    public void setFinalUserID(int finalUserID) {this.finalUserID = finalUserID;}
    public String getState() {return state;}
    public void setState(String state) {this.state = state;}
    public String getDetail() {return detail;}
    public void setDetail(String detail) {this.detail = detail;}
}
