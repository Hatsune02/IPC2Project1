package entities;

public enum TableName {
    ADMINS("admins"), BOOKS("books"), CARRIERS("carriers"),CATEGORIES("categories"),DETAILS_TRANSPORT("details_transport"),
    EXISTING_BOOKS("existing_books"), FINAL_USERS("final_users"), INCIDENTS("incidents"), LIBRARIES("libraries"),
    LOAN_APPLICATIONS("loan_applications"), LOANS("loans"), RECEPTIONISTS("receptionists"), REVOCATION_REQUESTS("revocation_requests"),
    TRANSPORT_BETWEEN_LIBRARIES("transport_between_libraries"), TRANSPORT_USERS("transport_users");

    private String name;
    private TableName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
