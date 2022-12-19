package request;

import java.io.File;
import java.util.Vector;
import writing.*;
import uses.Functions;

public class Drop {
    String message;
    private Request requested;

    public Drop(Request requested) {
        this.setRequested(requested);
    }

    public void dropDatabase(String request) throws Exception {
        Functions functions = new Functions();
        Vector<String> allDatas = functions.allFilesExistant("DATAS/" + this.getRequested().getUser() + "/");
        String[] requestTable = functions.stringer(request);
        Boolean same = null;
        if (requestTable[0].equalsIgnoreCase("drop")) {
            if (requestTable[1].equalsIgnoreCase("database")) {
                for (int i = 0; i < allDatas.size(); i++) {
                    if (requestTable[2].equalsIgnoreCase(String.valueOf(allDatas.get(i))) == true) {
                        same = true;
                        break;
                    }
                    if (requestTable[2].equalsIgnoreCase(String.valueOf(allDatas.get(i))) == false) {
                        same = false;
                    }
                }
                if (same == false) {
                    this.setMessage(requestTable[2] + " table does not exists");
                }
                if (same == true) {
                    boolean file = functions.isDirectory(requestTable[2], this.getRequested().getUser());
                    File f = new File("DATAS/" + this.getRequested().getUser() + "/" + requestTable[2]);
                    if (file == true) {
                        f.delete();
                    } else {
                        this.setMessage("No such database");
                        System.out.println("Request successfully answered but undone");
                    }
                    this.setMessage("Delete done");
                    System.out.println("Request successfully answered");
                }
            }
        }
    }

    public Request getRequested() {
        return requested;
    }

    public void setRequested(Request requested) {
        this.requested = requested;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
