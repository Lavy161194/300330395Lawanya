package com.example.laboratoryexercise4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Database implements DatabaseInterface {
    Connection con;

    public Database(Connection con) {
        this.con = con;
    }

    @Override
    public void add(Savingstable sat) throws ClassNotFoundException, SQLException {
        String quer1 = "INSERT INTO savingstable VALUES ( ?, ? )";
        PreparedStatement query = con.prepareStatement(quer1);
        query.setString(1, sat.getSatcode());
        query.setString(2, sat.getSatdesc());
        query.executeUpdate();
    }

    @Override
    public Savingstable edit(Savingstable sat, String satcode) throws SQLException, ClassNotFoundException {
        PreparedStatement query;
        query = con.prepareStatement("Update savingstable set satcode=?, satdesc=? where satcode = ?");
        query.setString(1, sat.getSatcode());
        query.setString(2, sat.getSatdesc());
        query.setString(3, satcode);
        query.executeUpdate();
        return sat;
    }

    @Override
    public void delete(String satcode) throws SQLException {
        String quer1 = "Delete from Sategsavingstableory where satcode = ?";
        PreparedStatement query = con.prepareStatement(quer1);
        query.setString(1, satcode);
        query.executeUpdate();
    }

    public Savingstable search(String satcode) throws SQLException, ClassNotFoundException {
        String quer1 = "Select * from savingstable where satcode = ?";
        PreparedStatement query = con.prepareStatement(quer1);
        query.setString(1, satcode);
        ResultSet rs = query.executeQuery();
        if (!rs.first()) {
            System.out.print("Record not existing");
            return null;
        }
        Savingstable obj1 = null;
        obj1 = new Savingstable(rs.getString("satcode"), rs.getString("satdesc"));
        return obj1;
    }

    @Override
    public List<Savingstable> display() throws ClassNotFoundException, SQLException {
        //create an array list that will contain the data recovered
        List<Savingstable> Satlist = new ArrayList<Savingstable>(); String quer1 = "Select * from savingstable";
        PreparedStatement query = con.prepareStatement(quer1);
        ResultSet rs = query.executeQuery();
        Savingstable obj1; //display records if there is data;
        while (rs.next()) {
            obj1 = new Savingstable(rs.getString("satcode"), rs.getString("satdesc"));
            Satlist.add(obj1);
        }
        return Satlist;
    }

    public List<Items> display2(String satcode) throws ClassNotFoundException, SQLException {
        //create an array list that will contain the data recovered
        List<Items> Itemlist = new ArrayList<Items>();
        String quer1 = "Select itemcode,itemdesc from items where satcode=?";
        PreparedStatement query = con.prepareStatement(quer1);
        query.setString(1, satcode);
        ResultSet rs = query.executeQuery();
        Items obj2;
        //display records if there is data;
        while (rs.next()) {
            obj2 = new Items(rs.getString("itemcode"),rs.getString("itemdesc"));
            Itemlist.add(obj2);
        }
        return Itemlist;
    }
}