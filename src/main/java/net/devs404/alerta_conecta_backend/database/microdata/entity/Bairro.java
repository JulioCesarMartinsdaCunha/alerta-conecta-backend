package net.devs404.alerta_conecta_backend.database.microdata.entity;

public class Bairro
{
    public final long id;
    public final String nome;
    public final long cidadeId;

    public Bairro(long id, String name, long cidadeId)
    {
        this.id = id;
        this.nome = name;
        this.cidadeId = cidadeId;
    }


    public long getId(){return id;}
    public String getName(){return nome;}
    public long getCidadeId(){return cidadeId;}
}
