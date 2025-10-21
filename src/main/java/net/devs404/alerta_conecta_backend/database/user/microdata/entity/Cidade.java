package net.devs404.alerta_conecta_backend.database.user.microdata.entity;

public class Cidade
{
    private final long id;
    private final String nome;
    private final long estadoId;

    public Cidade(long id, String name, long estadoId)
    {
        this.id = id;
        this.nome = name;
        this.estadoId = estadoId;
    }

    public long getId(){return id;}
    public String getName(){return nome;}
    public long getEstadoId(){return estadoId;}
}
