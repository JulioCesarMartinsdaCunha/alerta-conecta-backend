package net.devs404.alerta_conecta_backend.database.user.microdata;

import net.devs404.alerta_conecta_backend.database.DataBase;
import net.devs404.alerta_conecta_backend.database.user.microdata.entity.Bairro;
import net.devs404.alerta_conecta_backend.database.user.microdata.entity.Cargo;
import net.devs404.alerta_conecta_backend.database.user.microdata.entity.Cidade;
import net.devs404.alerta_conecta_backend.database.user.microdata.entity.Estado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MDBase
{
    public static List<Cargo> listCargos()
    {
        List<Cargo> list = new ArrayList<>();
        String sql = "SELECT * FROM cargo ORDER BY nome";

        try{
            Connection connect = DataBase.connect();
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Cargo cargo = new Cargo(rs.getInt("id_cargo"), rs.getString("nome"));
                list.add(cargo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public static List<Estado> listEstados()
    {
       List<Estado> list = new ArrayList<>();
       String sql = "SELECT * FROM estado ORDER BY nome";

       try{
           Connection connect = DataBase.connect();
           PreparedStatement ps = connect.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();

           while(rs.next())
           {
               Estado estado = new Estado(rs.getInt("id_estado"), rs.getString("nome"), rs.getString("sigla"));
               list.add(estado);
           }

       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

        return list;
    }

    public static List<Cidade> listCidades(int idEstado)
    {
        List<Cidade> list = new ArrayList<>();
        String sql = String.format("SELECT * FROM cidade WHERE id_estado=%s ORDER BY nome;", idEstado);

        try
        {
            Connection connect = DataBase.connect();
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Cidade cidade = new Cidade(rs.getInt("id_cidade"), rs.getString("nome"), idEstado);
                list.add(cidade);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public static List<Bairro> listBairros(int idCidade) {
        List<Bairro> list = new ArrayList<>();
        String sql = String.format("SELECT * FROM bairro WHERE id_bairro = %s ORDER BY nome;", idCidade);

        try{
            Connection connect = DataBase.connect();
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Bairro bairro = new Bairro(rs.getInt("id_bairro"), rs.getString("nome"), idCidade);
                list.add(bairro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
