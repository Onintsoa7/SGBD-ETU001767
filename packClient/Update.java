package request;

import java.util.Vector;
import table.*;
import uses.Functions;
import writing.Request;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
public class Update {
    String message;
    private Request requested;
    
    public Update(Request requested) {
        this.setRequested(requested);
    }

    public void updating(String request){
        Functions functions = new Functions();
        Vector<String> allDatas = functions.allFilesExistant("DATAS/" + this.getRequested().getUser() + "/" +this.getRequested().getDatabase()+"/");
        String[] requestTable = functions.stringer(request);
        boolean a = false;
        boolean b = false;
        for (int i = 0; i < allDatas.size(); i++) {
            if(requestTable[1].equalsIgnoreCase(allDatas.get(i)) == true){
                a = true;
            }
        }
        boolean y = false;
        try {
            Table table = functions.convertTextFromFileIntoTable(requestTable[1], this.getRequested().getDatabase(), this.getRequested().getUser());
            String[] tribus = new String[table.getAttributs().size()];
            int x = 0;
            for (int i = 0; i < table.getAttributs().size(); i++) {
                tribus[x] = table.getAttributs().get(i);
                x++;
            }

            int c = 0;
            String[][] s = new String[tribus.length][2];
            String[] want = new String[tribus.length];
            String[] type = new String[tribus.length];
            for (int i = 0; i < tribus.length; i++) {
                s[c] = new String[tribus.length];
                s[c] = tribus[i].split("_");
                want[c] = s[c][0];
                type[c] = s[c][1];
                c++;
            }
            
            for (int i = 0; i < want.length; i++) {
                if(want[i].equalsIgnoreCase(requestTable[3])== true && want[i].equalsIgnoreCase(requestTable[7])== true){
                    b = true;
                }
            }
            Vector<Vector<Object>> datas = table.getDatabases();
            if(requestTable[0].equalsIgnoreCase("Update") == true && requestTable[2].equalsIgnoreCase("set") == true && requestTable[6].equalsIgnoreCase("where") && requestTable[8].equals("=") == true && requestTable[4].equals("=") == true){
                if(a == true && b == true){
                    for (int i = 0; i < datas.size(); i++) {
                        for (int j = 0; j < datas.get(i).size(); j++) {
                                if(String.valueOf(datas.get(i).get(j)).equalsIgnoreCase(requestTable[9]) ==  true){
                                    File textFile = new File("DATAS/" + this.getRequested().getUser() + "/" +this.getRequested().getDatabase()+"/"+requestTable[1]);
                                    try {
                                    String data = FileUtils.readFileToString(textFile);
                                    data = data.replace(requestTable[9], requestTable[5]);
                                    FileUtils.writeStringToFile(textFile, data);
                                    y = true;
                                    }
                                    catch (IOException e) {
                                        this.setMessage("Update failed");
                                    }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            this.setMessage("Update failed");
        }
        if(y == true){
            this.setMessage("Update successfully done");
            System.out.println("Request successfully answered");
        }
        else{
            this.setMessage("Update failed");
            System.out.println("Request successfully answered but undone");
        }
        
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Request getRequested() {
        return requested;
    }
    public void setRequested(Request requested) {
        this.requested = requested;
    }
    
    
}
