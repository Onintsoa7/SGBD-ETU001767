package request;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

import javax.swing.text.AbstractDocument.LeafElement;

import table.Table;
import uses.Functions;
import writing.*;

public class Create {
    String message;
    private Request requested;

    public Create(Request requested) {
        this.setRequested(requested);
    }

    public Create() {

    }

    public void creation(String request) throws Exception {
        Functions functions = new Functions();
        String[] requestTable = functions.stringer(request);
        String[] divise = request.split("[(]");
        divise[1] = divise[1].replace(")", "");
        String[] types = divise[1].split(",");
        String[][] s = new String[types.length][2];
        String[] want = new String[types.length];
        int c = 0;
        int x = 0;
        
        if (requestTable[1].equalsIgnoreCase("table")) {
            Boolean same = null;
           try {
            Vector allDatas = functions.allFilesExistant("DATAS/" + this.getRequested().getUser() + "/" + this.getRequested().getDatabase() + "/");
            if (requestTable[0].equalsIgnoreCase("create")) {
                if(allDatas.size() == 0){
                    same = false;
                }
                for (int i = 0; i < allDatas.size(); i++) {
                    if (requestTable[2].equalsIgnoreCase(String.valueOf(allDatas.get(i))) == true) {
                        same = true;
                        break;
                    }
                    if (requestTable[2].equalsIgnoreCase(String.valueOf(allDatas.get(i))) == false) {
                        same = false;
                    }
                }
                if (same == true) {
                    System.out.println(requestTable[2] + " table already exist");
                    System.out.println("Request successfully answered but undone");
                }
                if (same == false) {
                    if(divise.length == 2){
                        for (int i = 0; i < types.length; i++) {
                            for (int j = 0; j < types[i].length(); j++) {
                                if(types[i].charAt(j) == '_'){
                                    s[c] = new String[types.length];
                                    s[c] = types[i].split("_");
                                    if(s[c][1].equalsIgnoreCase("string") || s[c][1].equalsIgnoreCase("int")){
                                        want[c] = s[c][1];
                                        x=x+1;
                                    }
                                    else{
                                        x=0;
                                    }
                                    c++;
                                }
                            }
                        }
                    }
                    if(x == types.length){
                        File table = new File("DATAS/" + this.getRequested().getUser() + "/" + this.getRequested().getDatabase() + "/" + requestTable[2]);
                        boolean exist = table.createNewFile();
                        FileWriter writer = new FileWriter(table, false);
                        writer.write(requestTable[2] + "::");
                        for (int k = 3; k < requestTable.length; k++) {
                            writer.write(requestTable[k] + "--");
                        }
                        writer.write("::");
                        writer.close();
                        this.setMessage("Table " + requestTable[2] + " created");
                        System.out.println("Request successfully answered");
                    }else{
                        this.setMessage("Types invalid or type unprecised");
                        System.out.println("Request successfully answered but undone");
                    }
                }
            }
           } catch (Exception e) {
            System.out.println(e + " create");
           }
        }
    }
    public void creationDatabase(String request) throws Exception {
        Functions functions = new Functions();
        String[] requestTable = functions.stringer(request);
        Boolean same = null;
        Vector allDatas = functions.allFilesExistant("DATAS/"+this.getRequested().getUser()+"/");
        if (requestTable[0].equalsIgnoreCase("create")) {
            if (requestTable[1].equalsIgnoreCase("database")) {
                if(allDatas.size() == 0){
                same = false;
                }
                for (int i = 0; i < allDatas.size(); i++) {
                    if (requestTable[2].equalsIgnoreCase(String.valueOf(allDatas.get(i))) == true) {
                        same = true;
                        break;
                    }
                    if (requestTable[2].equalsIgnoreCase(String.valueOf(allDatas.get(i))) == false) {
                        same = false;
                    }
                }
                if (same == true) {
                    this.setMessage("Failed to create database! " + requestTable[2] + " database already exist");
                    System.out.println("Request successfully answered but undone");
                }
                if (same == false) {
                    String path = "DATAS/" + this.getRequested().getUser() + "/" + requestTable[2] + "/";
                    File f = new File(path);
                    if (f.mkdirs()){
                        this.setMessage("Database " + requestTable[2] + " created");
                        System.out.println("Request successfully answered");
                    } else {
                        this.setMessage("Failed to create database!");
                        System.out.println("Request successfully answered but undone");
                    }
                }
            }
        }
    }

    public void creationUser(String request) throws Exception {
        Functions functions = new Functions();
        String[] requestTable = functions.stringer(request);
        Boolean same = null;
        Vector allDatas = functions.allFilesExistant("DATAS");
                    if(allDatas.size() == 0){
                        same = false;
                    }
                    for (int i = 0; i < allDatas.size(); i++) {
                    if (requestTable[2].equalsIgnoreCase(String.valueOf(allDatas.get(i))) == true) {
                        same = true;
                        break;
                    }
                    if (requestTable[2].equalsIgnoreCase(String.valueOf(allDatas.get(i))) == false) {
                        same = false;
                    }
                }
                if (same == true) {
                    this.setMessage("Failed to create User! " + requestTable[2] + " user already exist");
                    System.out.println("Request successfully answered");
                }
                if (same == false) {
                    String path = "DATAS/" + requestTable[2];
                    File f = new File(path);
                    if (f.mkdirs()) {
                        this.setMessage("User " + requestTable[2] + " created");
                        System.out.println("Request successfully answered");
                    } else {
                        this.setMessage("Failed to create User!");
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
