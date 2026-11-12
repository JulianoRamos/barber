package com.cesul.barber.controllers;

import com.cesul.barber.dtos.UserCreationDTO;
import com.cesul.barber.services.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
@Tag(name = "Usuários", description = "Gerenciamento de usuários da barbearia")
public class UsersController {

    private List<UserCreationDTO> users = new ArrayList<>();

    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    @Operation(summary = "Listar todos", description = "Retorna a lista de todos os usuários cadastrados.")
    public ResponseEntity<List<UserCreationDTO>> getAll() {
        return ResponseEntity.ok(this.usersService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserCreationDTO> update(@PathVariable UUID id, @RequestBody UserCreationDTO updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                updatedUser.setId(id);
                users.set(i, updatedUser);
                return ResponseEntity.ok(updatedUser);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Criar utilizador", description = "Gera um ID automático, valida os dados (nome e e-mail) e salva na lista.")
    public ResponseEntity<UserCreationDTO> create(@RequestBody UserCreationDTO user) {
        UserCreationDTO newUser = this.usersService.create(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PatchMapping("/{id}/avatar")
    @Operation(
            summary = "Atualizar Avatar",
            description = "Atualiza exclusivamente a imagem de perfil do utilizador. Ignora outros campos."
    )
    public ResponseEntity<UserCreationDTO> patchUser(@PathVariable UUID id, @RequestBody Map<String, Object> avatar) {
        Optional<UserCreationDTO> userOptional = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();

        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UserCreationDTO existingUser = userOptional.get();

        if (avatar.containsKey("avatarUrl")) {
            existingUser.setAvatarUrl((String) avatar.get("avatarUrl"));
        }

        return ResponseEntity.ok(existingUser);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar utilizador", description = "Remove o utilizador correspondente ao ID da lista.")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        boolean removed = users.removeIf(u -> u.getId().equals(id));

        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/clear")
    @Operation(summary = "Limpar utilizadores", description = "Limpa a lista em memória (Útil para reiniciar os testes durante a aula).")
    public ResponseEntity<Void> clearUsers() {
        this.users.clear();
        return ResponseEntity.noContent().build();
    }
}
