package net.devs404.alerta_conecta_backend.database;

import net.devs404.alerta_conecta_backend.database.entity.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserManage
{
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
}
