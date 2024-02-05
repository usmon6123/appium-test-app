package org.example.query;

import org.example.database.DatabaseConnection;
import org.example.modul.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestQuery {
    // Bu metot test tablosundan ilk yeni kayıt getirerek Test nesnesi cagirir
    public Test getTest() throws SQLException {

        // Veritabani baglantisi saglanir
        Connection connection = DatabaseConnection.getConnection();

        try {
            // SQL sorgusu tanimlanir
            String sql = "select  * from test where (test.status = 'new') order by 'id' asc limit 1";

            // Sorgu cagirilir
            PreparedStatement stmt = connection.prepareStatement(sql);

            // Sonucu alir
            ResultSet resultSet = stmt.executeQuery();

            // yeni Test nesnesi tanimlanir
            Test test = new Test();

            // Sonucun dongu cagirilanirken
            while (resultSet.next()) {
                // SQL sorgusu tanimlanir
                String sql2 = "update test set status ='wait' where id =?";

                // Sorgu cagirilir ve sqlde current idnin statusini 'wait' olarak guncelleyecektir
                PreparedStatement stmt2 = connection.prepareStatement(sql2);
                stmt2.setInt(1, resultSet.getInt("id"));
                stmt2.executeUpdate();

                // Test nesnesi tanimlanir
                test = new Test(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getDouble("sms"));
            }
            // Test nesnesi cagirilir
            return test;


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            // Veritabani baglantisi kapatilir
            if (connection != null) {
                connection.close();
            }
        }

        // Test nesnesi tanimlanir
        return new Test();
    }

    // Bu metot test tablosundaki kayıt durumunu "success" olarak guncelleyecektir
    public boolean updateStatusSuccess(Integer id) throws SQLException {

        // Veritabani baglantisi saglanir
        Connection connection = DatabaseConnection.getConnection();

        try {
            // SQL sorgusu tanimlanir
            String sql = "update test set status ='success' where id =?";

            // Sorgu cagirilir ve sqlde current idnin statusini 'success' olarak guncelleyecektir
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Hata olusturulursa false cagirilir
            return false;
        } finally {
            // Veritabani baglantisi kapatilir
            if (connection != null) {
                connection.close();
            }
        }
        // Hata olmadiginda true cagirilir
        return true;
    }

    // Bu metot test tablosundan yeni kayıt var mı kontrol ederek, yeni kayıt varsa TRUE yoksa FALSE dondurur
    public boolean newData() throws SQLException {
        // Veritabani baglantisi saglanir
        Connection connection = DatabaseConnection.getConnection();

        try {
            // SQL sorgusu tanimlanir
            String sql = "select  * from test where (test.status = 'new') order by 'id' asc limit 1";

            // Sorgu cagirilir
            PreparedStatement stmt = connection.prepareStatement(sql);

            // Sonucu alir
            ResultSet resultSet = stmt.executeQuery();

            // Sonucun kiymeti varsa true olmassa false
            while (resultSet.next()) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            // Hata olusturulursa false cagirilir
            return false;
        } finally {
            // Veritabani baglantisi kapatilir
            if (connection != null) {
                connection.close();
            }
        }
    }
}
