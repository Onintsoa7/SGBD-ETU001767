package request;

import java.io.Serializable;
import java.net.Socket;
import java.util.Vector;
import java.util.function.Function;
import writing.*;
import table.Table;
import uses.Functions;
import writing.Request;
import writing.*;

public class Select implements Serializable{
    String message;
    private Request requested;

    public Select(Request requested) {
        this.setRequested(requested);
    } 
    public Vector<String> allTables(String request){
        Functions functions = new Functions();
        String[] phrases = functions.stringer(request);
        Vector<String> allDatas = functions.allFilesExistant( "DATAS/" + this.getRequested().getUser() + "/" +this.getRequested().getDatabase()+"/");
        if(phrases[0].equalsIgnoreCase("show") == true){
            if(phrases[1].equalsIgnoreCase("tables")){
                this.setMessage("All tables in " + this.getRequested().getDatabase() + " database");
                return allDatas;
            }
        }
        this.setMessage("Verify your syntax");
        return null;
    } 
    public Table selectAll(String request) throws Exception {
        Table table = new Table();
         Functions functions = new Functions();
        Vector<String> allDatas = functions.allFilesExistant( "DATAS/" + this.getRequested().getUser() + "/" +this.getRequested().getDatabase()+"/");
    
        String[] phrases = functions.stringer(request);
        if (phrases[0].equalsIgnoreCase("select") == true) {
            if (phrases[1].equals("*") == true) {
                if (phrases[2].equalsIgnoreCase("from")) {
                    for (int i = 0; i < allDatas.size(); i++) {
                        if (phrases[3].equalsIgnoreCase(allDatas.get(i)) == true) {
                            table = functions.convertTextFromFileIntoTable(phrases[3],this.getRequested().getDatabase(),this.getRequested().getUser());
                        } else {

                        }
                    }
                }
            }
        }
        return table;
    }
    public Table selectLike(String request) throws Exception {
        Table table = new Table();
        Functions functions = new Functions();
       Vector<String> allDatas = functions.allFilesExistant( "DATAS/" + this.getRequested().getUser() + "/" +this.getRequested().getDatabase()+"/");
        String[] phrases = functions.stringer1(request);
        Table table1 = functions.convertTextFromFileIntoTable(phrases[3],this.getRequested().getDatabase(),this.getRequested().getUser());
        String attributs[] = phrases[1].split(",");
        if (phrases[0].equalsIgnoreCase("select") == true) {
            if (phrases[1].equals("*") == true) {
                if (phrases[2].equalsIgnoreCase("from")) {
                    for (int i = 0; i < allDatas.size(); i++) {
                        if (phrases[3].equalsIgnoreCase(allDatas.get(i)) == true) {
                            if (phrases[4].equalsIgnoreCase("where") == true) {
                                for (int j = 0; j < table1.getAttributs().size(); j++) {
                                    if (phrases[5].equalsIgnoreCase(table1.getAttributs().get(j)) == true) {
                                        if (phrases[6].equalsIgnoreCase("like") == true) {
                                            Vector<Vector<Object>> vector2 = new Vector<>();
                                            for (int j2 = 0; j2 < table1.getDatabases().size(); j2++) {
                                                for (int k = 0; k < table1.getDatabases().get(j2).size(); k++) {

                                                    table.setDataName(phrases[3]);
                                                    int a = 0;
                                                    if (String.valueOf(table1.getDatabases().get(j2).get(k)).contains(phrases[7]) == true) {
                                                        a = j2;
                                                        Vector<Object> vector = table1.getDatabases().get(a);
                                                        vector2.add(vector);
                                                        a++;
                                                    } else {
                                                    }
                                                }
                                            }
                                            table.setDatabases(vector2);
                                            table.setAttributs(table1.getAttributs());
                                        } else {
                                            System.out.println("No compatible column");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return table;
    }

    public Table selectWhere(String request) throws Exception {
        Table table = new Table();
        Functions functions = new Functions();
       Vector<String> allDatas = functions.allFilesExistant( "DATAS/" + this.getRequested().getUser() + "/" +this.getRequested().getDatabase()+"/");
        String[] phrases = functions.stringer1(request);
        Table table1 = functions.convertTextFromFileIntoTable(phrases[3],this.getRequested().getDatabase(),this.getRequested().getUser());
        String attributs[] = phrases[1].split(",");
        if (phrases[0].equalsIgnoreCase("select") == true) {
            if (phrases[1].equals("*") == true) {
                if (phrases[2].equalsIgnoreCase("from")) {
                    for (int i = 0; i < allDatas.size(); i++) {
                        if (phrases[3].equalsIgnoreCase(allDatas.get(i)) == true) {
                            if (phrases[4].equalsIgnoreCase("where") == true) {
                                for (int j = 0; j < table1.getAttributs().size(); j++) {
                                    if (phrases[5].equalsIgnoreCase(table1.getAttributs().get(j)) == true) {
                                        if (phrases[6].equals("=")) {
                                            Vector<Vector<Object>> vector2 = new Vector<>();
                                            for (int j2 = 0; j2 < table1.getDatabases().size(); j2++) {
                                                for (int k = 0; k < table1.getDatabases().get(j2).size(); k++) {

                                                    table.setDataName(phrases[3]);
                                                    int a = 0;
                                                    if (phrases[7].equalsIgnoreCase(String
                                                            .valueOf(table1.getDatabases().get(j2).get(k))) == true) {
                                                        a = j2;
                                                        Vector<Object> vector = table1.getDatabases().get(a);
                                                        vector2.add(vector);
                                                        a++;
                                                    } else {
                                                    }
                                                }
                                            }
                                            table.setDatabases(vector2);
                                            table.setAttributs(table1.getAttributs());
                                        } else {
                                            System.out.println("No compatible column");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (phrases[1].equals("*") == false) {
                 if (phrases[1].contains(",")) {
                    String[] attributsList = phrases[1].split(",");
                    for (int j1 = 0; j1 < table1.getAttributs().size(); j1++) {
                        for (int i = 0; i < attributsList.length; i++) {
                            if (table1.getAttributs().get(j1).equalsIgnoreCase(attributsList[i]) == true) {
                                for (int j = 0; j < table1.getAttributs().size(); j++) {
                                    if (table1.getAttributs().get(j).equalsIgnoreCase(phrases[5]) == true) {
                                        for (int j2 = 0; j2 < table1.getDatabases().size(); j2++) {
                                            if (String.valueOf(table1.getDatabases().get(j2).get(j))
                                            .equalsIgnoreCase(phrases[7]) == false) {
                                                String a = phrases[7];
                                                table1.getDatabases().remove(table1.getDatabases().get(j2));
                                                table = table1.projectionColum(attributsList);
                                            }
                                        }
                                    }
                                }
                            }

                        }
                    }
                } 
                if (phrases[2].equalsIgnoreCase("from") == true) {
                    for (int i = 0; i < allDatas.size(); i++) {
                        if (phrases[3].equalsIgnoreCase(allDatas.get(i)) == true) {
                            if (phrases[4].equalsIgnoreCase("where") == true) {
                                if (phrases[6].equals("=") == true) {
                                    for (int j1 = 0; j1 < table1.getAttributs().size(); j1++) {
                                        if (table1.getAttributs().get(j1).equalsIgnoreCase(phrases[1]) == true) {
                                            for (int j = 0; j < table1.getAttributs().size(); j++) {
                                                if (table1.getAttributs().get(j).equalsIgnoreCase(phrases[5]) == true) {
                                                    for (int j2 = 0; j2 < table1.getDatabases().size(); j2++) {
                                                        Vector<Object> vector = new Vector<>();
                                                        if (String.valueOf(table1.getDatabases().get(j2).get(j))
                                                                .equalsIgnoreCase(phrases[7]) == false) {
                                                                    table1.getDatabases().remove(table1.getDatabases().get(j2));
                                                                    table = table1.projectionColum(phrases[1]);
                                                         
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
        return table;
    }

    public Table selectDiffers(String request) throws Exception {
        Table table = new Table();
        Table table1 = new Table();
        Table table2 = new Table();

        Functions functions = new Functions();
        String[] separator = request.split(" not in ");

        String firtRequest = separator[0];
        String secondRequest = separator[1];

        String[] firstRequestDivision = functions.stringer(firtRequest);
        if (firstRequestDivision.length == 4) {
            table1 = selectAll(firtRequest);
        }
        if (firstRequestDivision.length == 8) {
            table1 = selectWhere(firtRequest);
        }

        String[] secondRequestDivision = functions.stringer(secondRequest);
        if (secondRequestDivision.length == 4) {
            table2 = selectAll(secondRequest);
        }
        if (secondRequestDivision.length == 8) {
            table2 = selectWhere(secondRequest);
        }
        table = table.difference(table1, table2);
        return table;
    }

    public Table selectColumn(String request) throws Exception {
        Table table = new Table();
        Table table1 = new Table();
        String[] req = request.split(" ");
        String[] attr = req[1].split(",");
        Functions functions = new Functions();
        Vector<String> allDatas = functions.allFilesExistant("DATAS/" + this.getRequested().getUser() + "/" +this.getRequested().getDatabase()+"/");
        table = functions.convertTextFromFileIntoTable(req[3],this.getRequested().getDatabase(),this.getRequested().getUser());
        if (req[0].equalsIgnoreCase("select")) {
            if (req[1] != "*") {
                if (table.getDataName().equalsIgnoreCase(req[3]) == true) {
                    for (int i = 0; i < table.getAttributs().size(); i++) {
                        for (int j = 0; j < attr.length; j++) {
                            if (table.getAttributs().get(i).equalsIgnoreCase(attr[j]) == true) {
                                if (req[2].equalsIgnoreCase("from") == true) {
                                    table1 = table.projectionColum(attr);
                                }
                            }
                        }
                    }
                }
            }
        }
        return table1;
    }

    public Table selectProduct(String request) throws Exception {
        Table table = new Table();
        Functions functions = new Functions();
       Vector<String> allDatas = functions.allFilesExistant("DATAS/" + this.getRequested().getUser() + "/" +this.getRequested().getDatabase()+"/");
        String[] req = request.split(" ");
        Table table1 = functions.convertTextFromFileIntoTable(req[3],this.getRequested().getDatabase(),this.getRequested().getUser());
        Table table2 = functions.convertTextFromFileIntoTable(req[5],this.getRequested().getDatabase(),this.getRequested().getUser());
        if (req[0].equalsIgnoreCase("select") == true) {
            if (req[1].equalsIgnoreCase("product") == true) {
                if (req[2].equalsIgnoreCase("between")) {
                    if (req[4].equalsIgnoreCase("and")) {
                        for (int i = 0; i < allDatas.size(); i++) {
                            if (req[3].equalsIgnoreCase(allDatas.get(i)) == true) {
                                for (int j = 0; j < allDatas.size(); j++) {
                                    if (req[5].equalsIgnoreCase(allDatas.get(j)) == true) {
                                        table = table.product(table1, table2);
                                    }
                                }
                            }
                        }

                    }

                }

            }
        }
        return table;
    }

    public Table seletDivision(String request) throws Exception {
        Table table = new Table();
        String[] req = request.split(" ");
        Functions functions = new Functions();
       Vector<String> allDatas = functions.allFilesExistant("DATAS/" + this.getRequested().getUser() + "/" +this.getRequested().getDatabase()+"/");
        Table table1 = functions.convertTextFromFileIntoTable(req[3],this.getRequested().getDatabase(),this.getRequested().getUser());
        Table table2 = functions.convertTextFromFileIntoTable(req[5],this.getRequested().getDatabase(),this.getRequested().getUser());
        if (req[0].equalsIgnoreCase("select") == true) {
            if (req[1].equalsIgnoreCase("*") == true) {
                if (req[2].equalsIgnoreCase("division") == true) {
                    if (req[4].equalsIgnoreCase("by") == true) {
                        for (int i = 0; i < allDatas.size(); i++) {
                            if (req[3].equalsIgnoreCase(allDatas.get(i))) {
                                for (int j = 0; j < allDatas.size(); j++) {
                                    if (req[5].equalsIgnoreCase(allDatas.get(j))) {
                                        table = table.divisionTable(table1, table2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return table;
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
