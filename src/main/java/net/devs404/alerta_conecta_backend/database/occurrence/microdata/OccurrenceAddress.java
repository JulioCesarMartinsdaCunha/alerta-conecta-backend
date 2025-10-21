package net.devs404.alerta_conecta_backend.database.occurrence.microdata;

public class OccurrenceAddress
{
    private String rua;
    private String numero;
    private String complemento;

    private int idBairro;


    public OccurrenceAddress(){}

    /// GETTERS&SETTERS
    public String getStreet() {return rua;}
    public String getNumber() {return numero;}
    public String getComplement() {return complemento;}
    public int getIdDistrict() {return idBairro;}


    public void setStreet(String rua) {this.rua = rua;}
    public void setNumber(String numero) {this.numero = numero;}
    public void setComplement(String complemento) {this.complemento = complemento;}
    public void setIdDistrict(int idBairro) {this.idBairro = idBairro;}
}
