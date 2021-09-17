package br.com.mybank.mybank.controller;


import br.com.mybank.mybank.domain.Usuario;
import br.com.mybank.mybank.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("adicionar")
    public Usuario adicionarUsuario(@RequestBody Usuario usuario) {

        //TODO tratamento de erros e response entity
        return usuarioService.adicionarUsuario(usuario);
    }

    @GetMapping("listartodos")
    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    @GetMapping("validarsenha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String usuario, @RequestParam String senha) {
       return usuarioService.verificarSenha(usuario, senha);
    }

    @PutMapping("atualizar/{id}")
    public Usuario atualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return usuarioService.atualizarUsuario(id, usuario);
    }

    //TODO trocar para soft delete
    @DeleteMapping("remover/{id}")
    public void removerUsuario(@PathVariable Integer id) {
        usuarioService.removerPorID(id);
    }


}
