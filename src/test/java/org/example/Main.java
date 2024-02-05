package org.example;

import lombok.SneakyThrows;
import org.example.database.DatabaseConnection;
import org.example.modul.Test;
import org.example.query.TestQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, InterruptedException {
        TestQuery query = new TestQuery();
         for (;true;) {
           if (query.newData()){
               Test test = query.getTest();
               Thread.sleep(10000);
               System.out.println(test.toString());
               System.out.println(test.getId()+" ----------id---------");
               query.updateStatusSuccess(test.getId());
               System.out.println("burada appium calisajak");


           }else {
               Thread.sleep(10000);
           }

       }
    }
}
