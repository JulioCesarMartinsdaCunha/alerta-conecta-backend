package net.devs404.alerta_conecta_backend.database.occurrence.entity;

import net.devs404.alerta_conecta_backend.database.occurrence.microdata.OccurrenceAddress;
import net.devs404.alerta_conecta_backend.database.occurrence.microdata.OccurrencePriority;
import net.devs404.alerta_conecta_backend.database.occurrence.microdata.OccurrenceStatus;
import net.devs404.alerta_conecta_backend.database.occurrence.microdata.OccurrenceType;

import java.sql.Timestamp;
import java.util.Date;

public class Occurrence
{
    private long id;


    private String titulo;

    private Timestamp data;

    private String envolvidos;
    private String detalhe = "";

    private OccurrenceStatus status;
    private OccurrencePriority prioridade;

    private OccurrenceAddress endereco;

    private OccurrenceType tipo;

    public Occurrence(){}

    public void promoteStatus(OccurrenceStatus newStatus)
    {
        status = newStatus;
    }

    public void promotePriority(OccurrencePriority newPriority)
    {
        prioridade = newPriority;
    }

    /// GETTERS&SETTERS
    public long getId() {return id;}
    public String getTitule() {return titulo;}
    public Timestamp getDate(){return data;}
    public String getVictims(){return envolvidos;}
    public String getDetails(){return detalhe;}

    public OccurrenceStatus getStatus(){return status;}
    public OccurrencePriority getPriority(){return prioridade;}

    public OccurrenceType getType(){return tipo;}
    public OccurrenceAddress getAddress(){return endereco;}

    public void setId(long newId){id = newId;}
    public void setTitle(String newTitle){titulo = newTitle;}
    public void setTimestamp(Timestamp newDate){data = newDate;}
    public void setVictims(String newListVict){envolvidos = newListVict;}
    public void setDetails(String newDetails){detalhe = newDetails;}

    public void setType(OccurrenceType newType){tipo = newType;}
    public void setPriority(String priority){prioridade = Enum.valueOf(OccurrencePriority.class, priority);}
    public void setAddress(OccurrenceAddress newAddress){endereco=newAddress;}
}
