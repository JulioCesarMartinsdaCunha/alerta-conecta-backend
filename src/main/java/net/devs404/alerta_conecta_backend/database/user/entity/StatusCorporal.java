package net.devs404.alerta_conecta_backend.database.user.entity;

public class StatusCorporal
{
    private final float pesoCorporal;
    private final float altura;

    private final double totalIMC;

    public StatusCorporal(float corporalWeight, float height)
    {
        this.pesoCorporal = corporalWeight;
        this.altura = height;

        totalIMC = pesoCorporal/(Math.pow(altura, 2));
    }

    /// GETTERS&SETTERS
    public float getCorporalWeight(){return pesoCorporal;}
    public float getHeight(){return altura;}
    public double getIMC(){return totalIMC;}
}
