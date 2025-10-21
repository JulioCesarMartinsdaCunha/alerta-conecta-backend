package net.devs404.alerta_conecta_backend;

import net.devs404.alerta_conecta_backend.GUI.GUIMain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application
{
    public static void main(String[] args)
    {
        new GUIMain();
        SpringApplication.run(net.devs404.alerta_conecta_backend.Application.class, args);
    }
}