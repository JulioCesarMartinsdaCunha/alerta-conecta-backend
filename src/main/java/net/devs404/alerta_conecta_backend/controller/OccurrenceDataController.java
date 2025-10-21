package net.devs404.alerta_conecta_backend.controller;

import net.devs404.alerta_conecta_backend.database.DataBase;
import net.devs404.alerta_conecta_backend.database.occurrence.OccurrenceManage;
import net.devs404.alerta_conecta_backend.database.occurrence.entity.Occurrence;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/database/occurrence")
public class OccurrenceDataController
{
    @GetMapping("/getall")
    public static List<Occurrence> getAll()
    {
        List<Occurrence> list = OccurrenceManage.getAllOccurrences();
        System.out.println("Requisitou a coleta geral!");
        return list;
    }

    @PostMapping("/registry")
    public static ResponseEntity<String> registryOne(@RequestBody Occurrence newOccurrence)
    {
        boolean registered = OccurrenceManage.registryOccurrence(newOccurrence);
        if(registered)
        {
            System.out.println("Ocorrencia registrada com sucesso!");
            return ResponseEntity.status(HttpStatus.CREATED).body("sucesso ao cadastrar usuário!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("falha ao cadastrar usuário!");
    }

    @DeleteMapping("/{id}")
    public static ResponseEntity<String> archiveOne(@PathVariable long id)
    {
        boolean archived = OccurrenceManage.archiveOccurrence(id);
        if(archived)
        {
            System.out.println("Ocorrencia arquivada com sucesso!");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("sucesso ao arquivar usuário!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("falha ao arquivar usuário!");
    }
}
