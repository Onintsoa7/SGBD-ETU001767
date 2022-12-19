package writing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Vector;

import table.Table;
import uses.Functions;
import request.*;
import socket.*;

public class Request {
    static String database;
    static String user;

    public Table chooseRequest(String request) {
        Table table = new Table();
        String[] tab = request.split(" ");
        Functions functions = new Functions();
        try {
        Select select = new Select(this);
        if(tab[0].equalsIgnoreCase("create") == true){
            if(tab[1].equalsIgnoreCase("user") == true){
                System.out.println("here");
                Create create = new Create();
                boolean is = functions.isDirectory(tab[0]);
                if(is == true){
                    table.setDataName(create.getMessage());
                    return table;
                }
                if(is == false){
                create.creationUser(request);
                table.setDataName(create.getMessage());
                return table;
                }
            }
        }
        if(tab[0].equalsIgnoreCase("show") && tab[1].equalsIgnoreCase("users")){
            Select select2 = new Select(this);
            Vector<String> selected = select2.allUsers(request);
            for (int i = 0; i < selected.size(); i++) {
                table.setAttributs(selected);
            }
            table.setDataName(null);
            return table;
        }

        if (tab[0].equalsIgnoreCase("Connect")) {
            if (tab[1].equalsIgnoreCase("as") == true) {
                if (tab[2].contains("=")) {
                    if (functions.isDirectory(tab[2]) == true) {
                        this.setUser(tab[2]);
                        table.setDataName("Connected as user " + tab[2]);
                        return table;
                    } else {
                        table.setDataName("Connection as user" + tab[2] + " failed");
                        return table;
                    }
                }
            }
        }
        if (tab[0].equalsIgnoreCase("Create") && this.getUser()!=null) {
            if (tab[1].equalsIgnoreCase("database") == true) {
                Create create = new Create(this);
                create.creationDatabase(request);
                table.setDataName(create.getMessage());
                return table;
            }
        }
        if (this.getUser()!=null) {
            if(tab[0].equalsIgnoreCase("Use")){
                if (functions.isDirectory(tab[1], this.getUser()) == true) {
                    this.setDatabase(tab[1]);
                    table.setDataName(tab[1] + " used");
                    return table;
                } else {
                    System.out.println("No database with such name");
                }
            }
            if(tab[0].equalsIgnoreCase("show") && tab[1].equalsIgnoreCase("databases")){
                Select select2 = new Select(this);
                Vector<String> selected = select2.allDatabases(request);
                for (int i = 0; i < selected.size(); i++) {
                    table.setAttributs(selected);
                }
                table.setDataName(null);
                return table;
            }
        }
        if(this.getDatabase() != null && this.getUser()!=null){
            if(tab[0].equalsIgnoreCase("desc") == true){
                Select select2 = new Select(this);
                Vector<String> selected = select2.describe(request);
                for (int i = 0; i < selected.size(); i++) {
                    table.setAttributs(selected);
                }
                table.setDataName(null);
                return table;
            }
            if(tab[0].equalsIgnoreCase("show") == true){
                Select select2 = new Select(this);
                Vector<String> selected = select2.allTables(request);
                for (int i = 0; i < selected.size(); i++) {
                    table.setAttributs(selected);
                }
                table.setDataName(null);
                return table;
            }
            if (tab[0].equalsIgnoreCase("Insert") == true) {
                Insert insert = new Insert(this);
                insert.insertion(request);
                table.setDataName(insert.getMessage());
                return table;
            }
            if (tab[0].equalsIgnoreCase("Update") == true) {
                Update update = new Update(this);
                update.updating(request);
                table.setDataName(update.getMessage());
                return table;
            }
            if (tab[0].equalsIgnoreCase("Create") == true) {
                if (tab[1].equalsIgnoreCase("table") == true) {
                    Create create = new Create(this);
                    create.creation(request);
                    table.setDataName(create.getMessage());
                    return table;
                }
            }
            if (tab[0].equalsIgnoreCase("delete") == true) {
                Delete delete = new Delete(this);
                delete.deletion(request);
                table.setDataName(delete.getMessage());
                return table;
            }
            
            if (tab[0].equalsIgnoreCase("drop") == true) {
                Drop drop = new Drop(this);
                drop.dropDatabase(request);
                table.setDataName(drop.getMessage());
                return table;
            }
            if (tab.length == 4) { // SELECT (CONDITION) FROM (TABLENAME) : taille = 4
                if (tab[1].equals("*") == true) {
                    table = select.selectAll(request);
                    return table;
                } else {
                    table = select.selectColumn(request);
                    return table;
                }
            }
            if (tab.length == 6) { // SELECT PRODUCT BETWEEN (TABLENAME1) AND (TABLENAME2)
                if (tab[1].equals("product")) {
                    table = select.selectProduct(request);
                    return table;
                }
                if (tab[2].equals("division")) { // SELECT * PRODUCT (TABLENAME1) BY (TABLENAME2)
                    table = select.seletDivision(request);
                    return table;
                }
            }
            if (tab.length == 8) { // SELECT (CONDITION) FROM (TABLENAME) WHERE (CONDITION) = (CHOICE) : Taille
                                   // 8
                if (tab[6].equals("=") == true) {
                    table = select.selectWhere(request);
                    return table;
                }
                if (tab[6].equalsIgnoreCase("like") == true) {
                    table = select.selectLike(request);
                    return table;
                }
                else{
                    table = select.SelectSign(request);
                    return table;
                }
            }
            if (request.contains("not in")) {
                table = select.selectDiffers(request);
                functions.showDatas(table);
                return table;
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e + " chooseRequest");
        }
        String ins = functions.instructionsSt();
        table.setDataName(ins);
        return table;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public  String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }



}
