package net.devs404.alerta_conecta_backend.database.user.microdata.entity;

public class Estado
{
    private final long id;
    private final String nome;
    private final String sigla;

    public Estado(long id, String name, String sigla)
    {
        this.id = id;
        this.nome = name;
        this.sigla = sigla;
    }

    public long getId(){return id;}
    public String getName(){return nome;}
    public String getSigla(){return sigla;}
}
