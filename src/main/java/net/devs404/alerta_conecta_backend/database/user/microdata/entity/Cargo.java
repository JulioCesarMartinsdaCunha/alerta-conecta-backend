package net.devs404.alerta_conecta_backend.database.user.microdata.entity;

public class Cargo
{
    public long id;
    public String nome;

    public Cargo(long id, String name)
    {
        this.id = id;
        this.nome = name;
    }

    public long getId(){return id;}
    public String getName(){return nome;}
}
