package uses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import table.Table;
import writing.Request;

public class Functions {
    private Request requested;

    public Functions() {
    }

    public Functions(Request requested) {
        this.setRequested(requested);
    }

    public String[] splitTypeOrAttr(Table table, int id){
        int c = 0;
        String[][] s = new String[table.getAttributs().size()][2];
        String[] want = new String[table.getAttributs().size()];
        for (int i = 0; i < table.getAttributs().size(); i++) {
            s[c] = new String[table.getAttributs().size()];
            s[c] = table.getAttributs().get(i).split("_");
            want[c] = s[c][id];
            c++;
        }
        return want;
    }

    public void instructions(){
        System.out.println("HERE ARE THE STEPS: ");
        System.out.println("1)You must be connected as an user who exists");
        Vector<String> allFStrings = allFilesExistant("DATAS/");
        if(allFStrings == null){
            System.out.println("No users");
        }
        for (int i = 0; i < allFStrings.size(); i++) {
            System.out.println("User" + (i+1) + ") " + allFStrings.get(i));
        }
        System.out.println("2)You must choose a database that the user you're connected in possess");
        System.out.println("3)You can proceed to do some select/insert/delete table/show tables");
    }
    public String instructionsSt(){
        String a = "HERE ARE THE STEPS: ";
        String b = "1)You must be connected as an user who exists, maybe no user exits yet";
        String c = "2)You must choose a database that the user you're connected in possess";
        String d = "3)You can proceed to do some select/insert/delete table/show tables";
        String e = "Check vocabularies if unsure";
        return a +"\n"+ b +"\n"+ c+"\n" + d +"\n" + e ;
    }
    public String classString(String string) {
        String classes = "";
        for(int i = 0; i < string.length(); i++){
            System.out.println(string.charAt(i));
            if(string.charAt(0) == '{'){
                classes = "string";
                return classes;
            }
        }
        if((string.contains("1")||string.contains("2")||string.contains("3")||string.contains("4")||string.contains("5")||string.contains("6")||string.contains("7")||string.contains("8")||string.contains("9")||string.contains("0")) && string.contains("'") == false){
            classes = "int";
            return classes;
        }
        else{
            classes="null";
        }
        return classes;
    }

    public String[] stringer(String request) {
        String[] value = request.split("[' '(,/:);]");
        return value;
    }

    public String[] stringer1(String request) {
        String[] value = request.split("[' '(/:);]");
        return value;
    }

    public Vector<String> allFilesExistant(String directory) {
        File file = new File(directory);
        File[] files = file.listFiles();
        Vector<String> textx = new Vector<>();
        String[] text = new String[files.length];
        int a = 0;
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                text[a] = files[i].getName();
                textx.add(text[a]);
                a++;
            }
            return textx;
        }
        return null;
    }

    public boolean isDirectory(String filename, String User) {
        File file = new File("DATAS/" + User + "/");
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().equalsIgnoreCase(filename) == true) {
                if (files[i].isDirectory() == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isDirectory(String filename) {
        File file = new File("DATAS/");
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().equalsIgnoreCase(filename) == true) {
                if (files[i].isDirectory() == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public File myFile(String fileName, String user, String database) {
        File file = new File("DATAS/" + user + "/" + database + "/");
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().equalsIgnoreCase(fileName) == true) {
                return files[i];
            }
        }
        return null;
    }

    public String textFromFile(File file) {
        try {
            Scanner sc = new Scanner(file);
            String answer = sc.nextLine();
            sc.close();
            return answer;
        } catch (FileNotFoundException e) {
            System.out.println(e + " textFromFile Functions.java");
        }
        return null;
    }

    public Table convertTextFromFileIntoTable(String tableName, String database, String User) throws Exception {
        File file = new File("DATAS/" + User + "/" + database + "/" + tableName);
        System.out.println(file.getPath() + " the paths");
        Table table = new Table();
        try {
            String textFromFile = textFromFile(file);
            String[] ordre = textFromFile.split("::");
            String nameOfTable = ordre[0];
            String attributs = ordre[1];
            String[] nameOfAttributs = attributs.split("--");
            Vector<String> titles = new Vector<>();
            for (int i = 0; i < nameOfAttributs.length; i++) {
                titles.add(nameOfAttributs[i]);
            }
            if (ordre.length < 3) {
                table.setDataName(nameOfTable);
                table.setAttributs(titles);
                table.setDatabases(null);
            } else {
                String valeurs = ordre[2];
                String[] values = valeurs.split("/");
                Vector<Vector<Object>> eachValue = new Vector<>();
                for (int i = 0; i < values.length; i++) {
                    String[] eachText = values[i].split("--");
                    Vector<Object> temp = new Vector<>();
                    for (int j = 0; j < eachText.length; j++) {
                        temp.add(eachText[j]);
                    }
                    eachValue.add(temp);
                }
                table.setDataName(nameOfTable);
                table.setAttributs(titles);
                table.setDatabases(eachValue);
            }
            return table;
        } catch (Exception e) {
            System.out.println(e + " convertTextFromFileIntoTable in Fuctions.java");
        }
        return table;
    }

    public void showDatas(Table table) throws Exception {
        try {
            
            if(table.getDataName() == null && table.getAttributs() == null && table.getDatabases() == null) {
                table.setDatabases(null);
                System.out.println("Table unknown");
            }
            else if(table.getDataName() != null && table.getAttributs() == null && table.getDatabases() == null){
                System.out.println(table.getDataName());
            }
            else if (table.getDataName() != null && table.getAttributs() != null && table.getDatabases() == null) {
                System.out.println("Empty set");
            }
            else if(table.getDataName() == null && table.getAttributs() != null && table.getDatabases() == null){
                for (int i = 0; i < table.getAttributs().size(); i++) {
                    System.out.println((i+1) + "-" + table.getAttributs().get(i));
                }
            }
            else {
                int c = 0;
                String[][] s = new String[table.getAttributs().size()][2];
                String[] want = new String[table.getAttributs().size()];
                for (int i = 0; i < table.getAttributs().size(); i++) {
                    s[c] = new String[table.getAttributs().size()];
                    s[c] = table.getAttributs().get(i).split("_");
                    want[c] = s[c][0];
                    c++;
                }
                System.out.println(table.getDatabases().size() + " rows selected");
                System.out.println(
                        "-------------------------------------------------------------------------"
                                + table.getDataName()
                                + "-------------------------------------------------------------------------");
                for (int i = 0; i < table.getAttributs().size(); i++) {
                    System.out.print("|             " + want[i].toUpperCase() + "             |");
                }
                System.out.println("");
                System.out.println("");
                for (int i = 0; i < table.getDatabases().size(); i++) {
                    for (int j = 0; j < table.getDatabases().get(i).size(); j++) {
                        System.out.print("|             " + table.getDatabases().get(i).get(j) + "             |");
                    }
                    System.out.println("");
                }
                System.out.println(
                        "------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
        } catch (Exception e) {

        }
    }

    public Request getRequested() {
        return requested;
    }

    public void setRequested(Request requested) {
        this.requested = requested;
    }
}
