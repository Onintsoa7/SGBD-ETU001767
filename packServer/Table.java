package table;

import uses.Functions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Vector;
import java.util.function.Function;

import javax.sound.sampled.DataLine;

public class Table implements Serializable{
    String dataName;
    Vector<String> attributs;
    Vector<String> types;
    Vector<Vector<Object>> databases;

    public Vector<String> getAttributs() {
        return attributs;
    }

    public void setAttributs(Vector<String> attributs) {
        this.attributs = attributs;
    }

    public Vector<Vector<Object>> getDatabases() {
        return databases;
    }

    public void setDatabases(Vector<Vector<Object>> databases) {
        this.databases = databases;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public Table projectionColum(String... attributs) throws Exception {
        Functions functions = new Functions();
        Table table = new Table();/* 
        functions.convertTextFromFileIntoTable(this.getDataName()); */
        table.setDataName(this.getDataName());
        Vector<Integer> indexisationTable = new Vector<>();
        Vector<String> newAttributs = new Vector<>();
        for (int i = 0; i < attributs.length; i++) {
            for (int j = 0; j < this.getAttributs().size(); j++) {
                if (this.getAttributs().get(j).contains(attributs[i]) == true) {
                    indexisationTable.add(j);
                }
            }
        }
        Vector vectorAttributs = new Vector<>();
        for (int i = 0; i < attributs.length; i++) {
            vectorAttributs.add(attributs[i]);
        }
        if (indexisationTable.size() != attributs.length) {
            throw new Exception("Columns are incompatible");
        }
        table.setAttributs(vectorAttributs);
        Vector<Vector<Object>> databases = new Vector<>();
        for (int j = 0; j < this.getDatabases().size(); j++) {
            Vector<Object> temp = new Vector<>();
            for (int i = 0; i < indexisationTable.size(); i++) {
                temp.add(this.getDatabases().get(j).get(indexisationTable.get(i)));
            }
            databases.add(temp);
        }
        if(table.getAttributs().size() == 1){
            table.setDatabases(databases);
            table.deleteDoublon();
        }
        else{
            table.setDatabases(databases);
        }
        return table;
    }
    public boolean findEgality(Vector<Object> vector1, Vector<Object> vector2) {
        if (vector1.size() == vector2.size()) {
            for (int i = 0; i < vector1.size(); i++) {
                if (vector1.get(i).equals(vector2.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public void deleteDoublon() {
        for (int i = 0; i < this.getDatabases().size() - 1; i++) {
            for (int j = i + 1; j < this.getDatabases().size(); j++) {
                if (this.findEgality(this.getDatabases().get(i), this.getDatabases().get(j)) == true) {
                    this.getDatabases().remove(j);
                    j--;
                }
            }
        }
    }

    public Table product(Table table1, Table table2) {
        Table table = new Table();
        Vector<String> attributs = new Vector<>();
        for (int i = 0; i < table1.getAttributs().size(); i++) {
            attributs.add(table1.getAttributs().get(i));
        }
        for (int i = 0; i < table2.getAttributs().size(); i++) {
            attributs.add(table2.getAttributs().get(i));
        }
        table.setAttributs(attributs);
        
         Vector<Vector<Object>> datas = new Vector<>();
        datas = adding(table1, table2);
        table.setDataName(table1.getDataName());
        table.setDatabases(datas);
        return table;
    }

    public Vector<Vector<Object>> adding(Table table1, Table table2) {
        Vector<Vector<Object>> datas = new Vector<>();
        for (int i = 0; i < table1.getDatabases().size(); i++) {        //loop dans la taille de la database de table1
            for (int j = 0; j < table2.getDatabases().size(); j++) {    //loop dans la taille de la database de table2
                Vector<Object> dataLine = new Vector<>();
                for (int k = 0; k <table1.getAttributs().size(); k++) { //loop dans la taille des attributs sachant que taille vector<Object> 1 = attribut.size()
                    dataLine.add(table1.getDatabases().get(i).get(k));  
                }
                for (int k = 0; k < table2.getAttributs().size(); k++) {
                    dataLine.add(table2.getDatabases().get(j).get(k));
                }
                datas.add(dataLine);
            }
        }
        return datas;
    }

    public Table difference(Table table1,Table table2){
        Table table = new Table();
        if(table1.getAttributs().size() == table2.getAttributs().size()){
            for (int i = 0; i < table1.getDatabases().size(); i++) {
                for (int j = 0; j < table2.getDatabases().size(); j++) {
                    if (table2.getDatabases().get(j).equals(table1.getDatabases().get(i))) {
                        table1.getDatabases().remove(table1.getDatabases().get(i));
                        table = table1;
    
                    } else {
                        table = table1;
                    }
                }
            } 
        }
        return table;
    }
    public Table divisionTable(Table table1, Table table2)throws Exception{
        Table division = new Table();
        String column = null;
        for (int i = 0; i < table1.getAttributs().size(); i++) {
            for (int j = 0; j < table2.getAttributs().size(); j++) {
                if(table1.getAttributs().get(i).equalsIgnoreCase(table2.getAttributs().get(j))){
                    column = table2.getAttributs().get(j);
                }
            }
        }
        Table projectionTable1 = table1.projectionColum(table1.getAttributs().get(0),column);
        Table projectionTable2 = table2.projectionColum(table2.getAttributs().get(0));
        Table projectionTable3 = table1.projectionColum(table1.getAttributs().get(0));
        Table produitAllPossiblities = product(projectionTable2, projectionTable3);
        Table tableNotUsingAll = difference(produitAllPossiblities, projectionTable1);
        Table projectionTableNotUsingAll = tableNotUsingAll.projectionColum(tableNotUsingAll.getAttributs().get(0));
        Table tableUsingAll = difference(projectionTable2, projectionTableNotUsingAll);
        division = tableUsingAll; 

        return division;
    }

    public Vector<String> getTypes() {
        return types;
    }

    public void setTypes(Vector<String> types) {
        this.types = types;
    }

   
}
