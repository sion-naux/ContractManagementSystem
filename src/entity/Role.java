package entity;

/**
 * @author furuixuan
 * @version V1.0
 * @Project: ContractsManagementSystem
 * @Package PACKAGE_NAME
 * @date 2018/12/10 11:11
 */

public class Role {
    private String name;
    private String description;
    private String functions;

    public Role() {

    }

    public Role(String roleName, String description, String functions) {
        this.name = roleName;
        this.description = description;
        this.functions = functions;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFunctions() {
        return functions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }


}
