package request;
import table.Table;
import uses.Functions;
import writing.*;

import java.io.File;
import java.util.Vector;
public class Delete {
    private Request requested;
    String message;
    public Delete(Request requested) {
        this.setRequested(requested);
    }
    
    public void deletion(String request)throws Exception{
    Functions functions = new Functions();
    Vector allDatas = functions.allFilesExistant("DATAS/"+this.getRequested().getUser() + "/" +requested.getDatabase()+"/");
        String[] requestTable = functions.stringer(request);
        Boolean same =  null;
        if (requestTable[0].equalsIgnoreCase("delete")) {
            if (requestTable[1].equalsIgnoreCase("table")) {
                for (int i = 0; i < allDatas.size(); i++) {
                    if (requestTable[2].equalsIgnoreCase(String.valueOf(allDatas.get(i))) == true) {
                        same = true;
                        break;
                    } if (requestTable[2].equalsIgnoreCase(String.valueOf(allDatas.get(i))) == false) {
                        same = false;
                    }
                }
                 if(same == false){
                    this.setMessage(requestTable[2] + " Table does not exists");
                    System.out.println("Request successfully answered but undone");
                }
                if(same == true){
                    File file = functions.myFile(requestTable[2],this.getRequested().getUser(),this.getRequested().getDatabase());
                    file.delete();
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
