package net.devs404.alerta_conecta_backend.database.entity;

public class Endereco
{
    private final String rua;
    private final int numero;

    private String complemento;

    public Endereco(String street, int number)
    {
        this.rua = street;
        this.numero = number;
    }

    /// GETTERS&SETTERS
    public String getStreet(){return rua;}
    public int getNumber(){return numero;}

    public String getComplement(){return complemento;}


    public void setComplement(String newComplement){complemento = newComplement;}
}
