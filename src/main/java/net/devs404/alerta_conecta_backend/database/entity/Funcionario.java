package net.devs404.alerta_conecta_backend.database.entity;

import net.devs404.alerta_conecta_backend.database.microdata.entity.Cargo;

import java.sql.Date;

public class Funcionario
{
    private String cpf;
    private String matricula;
    private String nome;

    private Date dataNasc;

    private String senha;

    private String email;
    private String numero;

    private Cargo cargo;
    private Endereco endereco;
    private StatusCorporal corporal;


    public Funcionario(String cpf, String registry, String name, String email, String number, Date dateBorn)
    {
        this.cpf = cpf;
        this.matricula = registry;
        this.nome = name;

        this.email = email;
        this.numero = number;

        this.dataNasc = dateBorn;
    }

    public void promoteRole(Cargo newRole)
    {
        cargo = newRole;
    }

    public void registryAddress(Endereco newAddress)
    {
        endereco = newAddress;
    }

    public void registryCorporalStatus(StatusCorporal newStatus)
    {
        corporal = newStatus;
    }

    public void printStatus()
    {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Matricula: " + matricula);
        System.out.println("Data Nascimento: " + dataNasc);
        System.out.println("Email: " + email);
        System.out.println("Numero: " + numero);
        System.out.println("Senha: " + senha);
        System.out.println("Cargo: " + cargo);
        System.out.println("Endereço: " + endereco);
        System.out.println("Status corporal: " + corporal);
    }

    /// GETTER&SETTERS
    public String getCpf(){return cpf;}
    public String getRegistry(){return matricula;}
    public String getName(){return nome;}

    public String getPass() {return senha;}
    public String getEmail(){return email;}
    public String getPhone(){return numero;}

    public Date getBornDate(){return dataNasc;}
    public Cargo getRole(){return cargo;}
    public Endereco getAddress(){return endereco;}
    public StatusCorporal getCorporalStatus(){return corporal;}


    //SETTERS
    public void setEmail(String newEmail){email = newEmail;}
    public void setPhone(String newPhone){numero = newPhone;}
    public void setAddress(Endereco newAddress) {this.endereco = newAddress;}

    public void setCorporal(StatusCorporal newCorporal) {this.corporal = newCorporal;}
    public void setCpf(String newCpf) {this.cpf = newCpf;}
    public void setRegistry(String newRegistry) {this.matricula = newRegistry;}
    public void setName(String newName) {this.nome = newName;}

    public void setBornDate(Date newBornDate) {this.dataNasc = newBornDate;}
    public void setPass(String newPass) {this.senha = newPass;}
}
