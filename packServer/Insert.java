package request;

import java.io.FileWriter;
import java.util.Vector;

import table.Table;
import uses.Functions;
import writing.*;

public class Insert {
    String message;
    private Request requested;

    public Insert(Request requested) {
        this.setRequested(requested);
    }
    
    /**
     * @param request
     * @throws Exception
     */
    public void insertion(String request) throws Exception {
        Functions functions = new Functions();
        Vector allDatas = functions.allFilesExistant("DATAS/" + this.getRequested().getUser() + "/" +this.getRequested().getDatabase()+"/");
        String[] requestTable = functions.stringer(request);
        String[] divise = request.split("[(]");
        divise[1] = divise[1].replace(")", "");
        String[] attributsChacun = divise[1].split(",");
        Table table = functions.convertTextFromFileIntoTable(requestTable[2],this.getRequested().getDatabase(),this.getRequested().getUser());
        int b = 0;
        String[] attr = new String[table.getAttributs().size()];
        for (int i = 0; i < table.getAttributs().size(); i++) {
            attr[b] = table.getAttributs().get(i);
            b++;
        }
        int c = 0;
        String[][] s = new String[attr.length][2];
        String[] want = new String[attr.length];
        for (int i = 0; i < attr.length; i++) {
            s[c] = new String[attr.length];
            s[c] = attr[i].split("_");
            want[c] = s[c][1];
            c++;
        }
        String[] valuesType = new String[attributsChacun.length];
        int x = 0;
        for (int i = 0; i < attributsChacun.length; i++) {
            valuesType[x] = functions.classString(attributsChacun[i]);
            System.out.println(valuesType[x] + "x= " + x);
            x ++;
        }
        boolean po = false;
        int count = 0;
        if(valuesType.length == want.length ){
                for (int j = 0; j < want.length; j++) {
                    if(want[j].equalsIgnoreCase(valuesType[j]) == true ){
                        count = count+1;
                    }
            }
        }
        if(count == want.length){
            po=true;
        }else{
            po=false;
        }
        if(requestTable[0].equalsIgnoreCase("insert") == true){
            if (requestTable[1].equalsIgnoreCase("into") == true){
                for (int i = 0; i < allDatas.size(); i++) {
                    if (requestTable[2].equalsIgnoreCase((String) allDatas.get(i)) == true){
                        if (requestTable[3].equalsIgnoreCase("values") == true) {
                            if(po==true){
                                FileWriter writer = new FileWriter("DATAS/" + this.getRequested().getUser() + "/" + this.getRequested().getDatabase() + "/"+ requestTable[2], true);
                            for (int j = 4; j < requestTable.length; j++) {
                                writer.write(requestTable[j] + "--");
                            }
                            writer.write("/");
                            writer.close();
                            this.setMessage("Insertion done");
                            System.out.println("Request successfully answered");
                            }
                            else{
                                this.setMessage("Type invalide ou longueur invalide");
                                System.out.println("Request successfully answered but undone");
                            }
                        }
                    }
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
