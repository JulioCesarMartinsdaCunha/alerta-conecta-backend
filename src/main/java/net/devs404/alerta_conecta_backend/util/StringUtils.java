package net.devs404.alerta_conecta_backend.util;

public class StringUtils
{
    public static String capitalize(String text)
    {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
}
