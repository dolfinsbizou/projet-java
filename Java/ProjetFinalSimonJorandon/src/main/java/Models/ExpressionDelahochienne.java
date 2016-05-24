/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Clément
 */
public class ExpressionDelahochienne {

    int id;
    String expression;
    String meanning;
    String picture;

    public ExpressionDelahochienne(int ID, String Expression, String Meanning, String Picture) {
        id = ID;
        expression = Expression;
        meanning = Meanning;
        picture = Picture;
    }

    public static ArrayList<ExpressionDelahochienne> RecupALL(Statement statement) throws SQLException {

        ArrayList<ExpressionDelahochienne> Expression = new ArrayList();
        ResultSet result = statement.executeQuery("SELECT * FROM ExpressionDelahochiennes");
        while (result.next()) {
            int ID;
            String ExpressionTrans;
            String Meanning;
            String Picture;

            ID = result.getInt("id");
            ExpressionTrans = result.getString("Expression");
            Meanning = result.getString("Signification");
            Picture = result.getString("Illustration");

            Expression.add(new ExpressionDelahochienne(ID, ExpressionTrans, Meanning, Picture));

        }
        result.close();
        return Expression;
        
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
