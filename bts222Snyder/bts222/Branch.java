import java.io.*;
import java.util.*;

// Class used for branch
public class Branch {

    String employeeID = "";
    String branchName = "";

    public Branch(String employeeID, String branchName) {
        this.employeeID = employeeID;
        this.branchName = branchName;
    }

    public String getEmployeeID () {
        return employeeID;
    }

    public String getBranchName() {
        return branchName;
    }

    // Checks if the branch has a tellar to verify if deposits are valid at this branch
    public boolean hasATeller() {
        if (employeeID == null || employeeID.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

}