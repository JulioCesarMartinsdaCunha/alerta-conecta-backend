package net.devs404.alerta_conecta_backend.database.user;

import net.devs404.alerta_conecta_backend.database.DataBase;
import net.devs404.alerta_conecta_backend.database.user.entity.Funcionario;
import net.devs404.alerta_conecta_backend.database.user.microdata.entity.Cargo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManage
{
    public static List<Funcionario> getAllFuncionario()
    {
        try {
            Connection connect = DataBase.connect();
            PreparedStatement ps = connect.prepareStatement("SELECT * FROM funcionario;");
            ResultSet rs = ps.executeQuery();

            List<Funcionario> list = new ArrayList<>();
            while(rs.next())
            {
                Funcionario func = new Funcionario();
                func.setCpf(rs.getString("cpf"));
                func.setRegistry(rs.getString("matricula"));
                func.setName(rs.getString("nome"));
                func.setEmail(rs.getString("email"));
                func.setPhone(rs.getString("numero_telefone"));
                func.setBornDate(rs.getDate("data_nascimento"));

                list.add(func);
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean registryFuncionario(Funcionario func)
    {
        String sql = String.format("CALL sp_inserir_funcionario('%s', '%s', '%s', '%s', '%s', '%s', '%s', 1);"
        , func.getRegistry(), func.getName(), func.getCpf(), func.getBornDate(), func.getEmail(), func.getPass(),
                func.getPhone());

        try {
            Connection connect = DataBase.connect();
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
            ///return false;
        }

        return true;
    }

    public static Funcionario tryLogin(Funcionario func)
    {
        Funcionario logged = null;
        String sql = String.format("SELECT * FROM funcionario func JOIN cargo c ON func.id_cargo = c.id_cargo WHERE cpf='%s' AND senha='%s';", func.getCpf(), func.getPass());

        try {
            Connection connect = DataBase.connect();
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                logged = new Funcionario();
                logged.setName(rs.getString("nome"));
                logged.setCpf(rs.getString("cpf"));
                logged.setRegistry(rs.getString("matricula"));
                logged.setEmail(rs.getString("email"));
                logged.setPhone(rs.getString("numero_telefone"));

                Cargo cargo = new Cargo(rs.getLong("id_cargo"), rs.getString("nome_cargo"));
                logged.promoteRole(cargo);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return logged;
    }

    public static Funcionario updateFuncionario(String matricula, Funcionario updFunc)
    {
        String sql = String.format("UPDATE funcionario SET nome='%s', email='%s', senha='%s', numero_telefone='%s' WHERE cpf='%s';",
                updFunc.getName(), updFunc.getEmail(), updFunc.getPass(), updFunc.getPhone(), matricula);

        try {
            Connection connect = DataBase.connect();
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static boolean deleteFuncionario(String matricula)
    {
        String sql = String.format("DELETE FROM funcionario WHERE matricula='%s';", matricula);

        try {
            Connection connect = DataBase.connect();
            PreparedStatement ps = connect.prepareStatement(sql);
            return ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
