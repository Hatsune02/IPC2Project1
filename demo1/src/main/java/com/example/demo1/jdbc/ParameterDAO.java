package com.example.demo1.jdbc;
import com.example.demo1.entities.objects_process.Parameter;
import java.sql.*;
import java.util.*;
import static com.example.demo1.jdbc.Conexion.*;

public class ParameterDAO {
    private static final String SQL_SELECT = "select * from parameters";
    private static final String SQL_UPDATE = "update parameters set parameter_name=?,parameter_value=? where id=?";

    public List<Parameter> select(){
        Connection con=null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Parameter> parameters = new ArrayList<>();

        try{
            con = getConnection();
            ps = con.prepareStatement(SQL_SELECT);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String parameterName = rs.getString("parameter_name");
                double parameterValue = rs.getDouble("parameter_value");
                parameters.add(new Parameter(id,parameterName,parameterValue));

            }

        }catch (SQLException e){
            e.printStackTrace(System.out);
        }
        finally {
            try{
                close(rs);
                close(ps);
                close(con);
            }  catch (SQLException e){
                e.printStackTrace(System.out);
            }

        }
        return parameters;
    }
    public int update(Parameter parameter){
        Connection con = null;
        PreparedStatement ps = null;
        int records = 0;
        try {
            con = getConnection();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1,parameter.getName());
            ps.setDouble(2,parameter.getValue());
            ps.setInt(3,parameter.getId());
            records = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        finally {
            try {
                close(ps);
                close(con);
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        return records;
    }
    public Parameter selectOne(Parameter parameter){
        List<Parameter> parameters = select();
        for(Parameter parameter1 : parameters){
            if(parameter1.getId() == parameter.getId()){
                parameter = parameter1;
                break;
            }
        }
        return parameter;
    }
}
