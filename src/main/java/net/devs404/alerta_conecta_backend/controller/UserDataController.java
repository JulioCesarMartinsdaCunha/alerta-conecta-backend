package net.devs404.alerta_conecta_backend.controller;

import net.devs404.alerta_conecta_backend.database.user.UserManage;
import net.devs404.alerta_conecta_backend.database.user.entity.Funcionario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/database/user")
public class UserDataController
{

    @GetMapping("/ping-test")
    public Map<String, Object> getList()
    {
        Map<String, Object> list = new LinkedHashMap<>();
        list.put("name", "Fazedor de sexo");
        list.put("idade", 10);
        list.put("comidafav", "porra37");
        return list;
    }

    @GetMapping("/funcionarios")
    public List<Funcionario> getAll()
    {
        return UserManage.getAllFuncionario();
    }

    @PostMapping("/login")
    public ResponseEntity<?> getLogin(@RequestBody Funcionario func)
    {
        Funcionario logged = UserManage.tryLogin(func);
        if(logged != null)
        {
            Map<String, Object> dados = Map.of("status", "sucesso",
                    "name", logged.getName(),
                    "email", logged.getEmail(),
                    "cpf", logged.getCpf(),
                    "registry", logged.getRegistry(),
                    "phone", logged.getPhone(),
                    "role", logged.getRole().getName()
            );
            System.out.println("Logou com sucesso!");
            return ResponseEntity.ok(dados);
        }
        System.out.println("Usuário não encontrado!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("status", "não encontrado"));
    }

    @PostMapping
    public ResponseEntity<String> postOne(@RequestBody Funcionario newFunc)
    {
        newFunc.printStatus();
        boolean confirm = UserManage.registryFuncionario(newFunc);

        if(confirm)
        {
            System.out.println("Usuário cadastrado com sucesso!");
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!");
        }
        else
        {
            System.out.println("Usuário não foi cadastrado com sucesso!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não cadastrado!");
        }
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<?> putOne(@PathVariable String matricula, @RequestBody Funcionario updFunc)
    {
        Funcionario func = UserManage.updateFuncionario(matricula, updFunc);

        if(func != null)
        {
            Map<String, Object> json = Map.of("status", "Atualizado",
                    "name", func.getName(),
                    "email", func.getEmail(),
                    "phone", func.getPhone()
            );

            ResponseEntity.accepted().body(json);
        }
        return null;
    }
}
