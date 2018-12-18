package entity;

public class Right {
    private String userName;
    private String roleName;
    private String description;

    public Right(String userName, String roleName, String description) {
        this.userName = userName;
        this.roleName = roleName;
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
