package net.devs404.alerta_conecta_backend.controller;

import net.devs404.alerta_conecta_backend.database.UserManage;
import net.devs404.alerta_conecta_backend.database.entity.Funcionario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class UserDataController
{
    public static final int PORT_WHO_ACTING = 3308;
    public static final String SUB_WHO_ACTING = "database";

    @GetMapping("/" + SUB_WHO_ACTING + "/get-all")
    public Map<String, Object> getList()
    {
        Map<String, Object> list = new LinkedHashMap<>();
        list.put("name", "Fazedor de sexo");
        list.put("idade", 10);
        list.put("comidafav", "porra37");
        return list;
    }


    //ARRUMARAR!!!
    @PostMapping("/" + SUB_WHO_ACTING+"/login")
    public ResponseEntity<?> getOne(@RequestBody String cpf, String pass)
    {
        System.out.println("CPF: "+cpf);
        System.out.println("Senha: "+pass);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cpf);
    }

    @PostMapping("/" + SUB_WHO_ACTING)
    public ResponseEntity<?> postOne(@RequestBody Funcionario func)
    {
        func.printStatus();
        boolean confirm = UserManage.registryFuncionario(func);

        if(confirm)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(func);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(func);
        }
    }
}
