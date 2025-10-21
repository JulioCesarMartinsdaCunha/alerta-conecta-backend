package net.devs404.alerta_conecta_backend.GUI;

import javax.swing.JFrame;

public class GUIMain extends JFrame
{
    public GUIMain()
    {
        InitializeComponents();
    }

    private void InitializeComponents()
    {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 450);
        this.setResizable(false);
        this.setTitle("Painel de controle back-end");
    }
}
