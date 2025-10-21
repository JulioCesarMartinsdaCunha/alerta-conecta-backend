package net.devs404.alerta_conecta_backend.database.occurrence;

import net.devs404.alerta_conecta_backend.database.DataBase;
import net.devs404.alerta_conecta_backend.database.occurrence.entity.Occurrence;
import net.devs404.alerta_conecta_backend.database.occurrence.microdata.OccurrenceAddress;
import net.devs404.alerta_conecta_backend.database.occurrence.microdata.OccurrencePriority;
import net.devs404.alerta_conecta_backend.database.occurrence.microdata.OccurrenceStatus;
import net.devs404.alerta_conecta_backend.database.occurrence.microdata.OccurrenceType;
import net.devs404.alerta_conecta_backend.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OccurrenceManage
{
    public static List<Occurrence> getAllOccurrences()
    {
        List<Occurrence> list = new ArrayList<>();
        String sql = """
                SELECT
                id_ocorrencia,
                titulo,
                data_hora,
                envolvidos,
                detalhes,
                status_atual,
                prioridade,
                t.id_tipo_ocorrencia as id_tipo,
                nome_tipo,
                descricao_tipo\s
                FROM\s
                ocorrencia o JOIN tipo_ocorrencia t ON o.id_tipo_ocorrencia = t.id_tipo_ocorrencia;""";

        try {
            Connection connect = DataBase.connect();
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                Occurrence newOccurrence = new Occurrence();
                newOccurrence.setId(rs.getInt("id_ocorrencia"));
                newOccurrence.setTimestamp(rs.getTimestamp("data_hora"));
                newOccurrence.setVictims(rs.getString("envolvidos"));
                newOccurrence.setDetails(rs.getString("detalhes"));

                newOccurrence.promoteStatus(OccurrenceStatus.Em_andamento);
                newOccurrence.promotePriority(Enum.valueOf(OccurrencePriority.class, rs.getString("prioridade")));

                newOccurrence.setType(new OccurrenceType(rs.getInt("id_tipo"), rs.getString("nome_tipo")));

                list.add(newOccurrence);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static boolean registryOccurrence(Occurrence newOccurrence)
    {
        OccurrenceAddress adr = newOccurrence.getAddress();
        String sql = "CALL sp_inserir_ocorrencia_completa(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            Connection connect = DataBase.connect();
            PreparedStatement ps = connect.prepareStatement(sql);

            ps.setString(1, newOccurrence.getTitule());
            ps.setTimestamp(2, newOccurrence.getDate());
            ps.setString(3, newOccurrence.getVictims());
            ps.setString(4, newOccurrence.getDetails());
            ps.setString(5, "Em andamento");
            ps.setString(6, StringUtils.capitalize(newOccurrence.getPriority().name()));
            ps.setLong(7, newOccurrence.getType().getId());
            ps.setString(8, adr.getStreet());
            ps.setString(9, adr.getNumber());
            ps.setString(10, adr.getComplement());
            ps.setLong(11, adr.getIdDistrict());

            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateOccurrence(Occurrence updOccurrence)
    {
        String sql = String.format("UPDATE ocorrencia SET titulo = '%s', envolvidos = '%s', detalhes = '%s', status_atual = '%s', prioridade = '%s' WHERE id_ocorrencia = %s;",
                updOccurrence.getTitule(), updOccurrence.getVictims(), updOccurrence.getDetails(), updOccurrence.getStatus().name(), updOccurrence.getPriority().name(), updOccurrence.getId());

        try {
            Connection connect = DataBase.connect();
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean archiveOccurrence(long id)
    {
        String sql = String.format("UPDATE ocorrencia SET status_atual = 'Cancelada' WHERE id_ocorrencia = %s", id);
        try {
            Connection connect = DataBase.connect();
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
