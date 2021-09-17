package br.com.mybank.mybank.service;

import br.com.mybank.mybank.domain.Usuario;
import br.com.mybank.mybank.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    public Usuario adicionarUsuario(Usuario usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario atualizarUsuario(Integer id, Usuario usuario) {
        if (usuarioRepository.findById(id).isEmpty() && !usuarioRepository.findById(id).get().getId().equals(id)) {
            return null;
        }
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    //TODO Trocar esse void
    public void removerPorID(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public ResponseEntity<Boolean> verificarSenha(String usuario, String senha) {


        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsuario(usuario);
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        boolean valid = encoder.matches(senha, usuarioOptional.get().getSenha());
        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(valid);



    }
}