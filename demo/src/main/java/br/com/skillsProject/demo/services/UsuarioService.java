package br.com.skillsProject.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.skillsProject.demo.dtos.UsuarioDto;
import br.com.skillsProject.demo.models.EntityMapper;
import br.com.skillsProject.demo.models.Usuario;
import br.com.skillsProject.demo.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioDto cadastrarUsuario(String username, String senha, String email) {
        if (usuarioRepository.findByUsername(username).isPresent()) {
            throw new IllegalStateException("Username já existe!");
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setEmail(email);
        Usuario novoUsuario = usuarioRepository.save(usuario);
        return EntityMapper.INSTANCE.usuarioToUsuarioDto(novoUsuario);
    }

    public Optional<UsuarioDto> encontrarUsuarioPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
            .map(EntityMapper.INSTANCE::usuarioToUsuarioDto);
    }

    public UsuarioDto atualizarUsuario(Long idUsuario, String username, String senha, String email) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow(() -> new IllegalStateException("Usuário não encontrado"));
        
        usuario.setUsername(username);
        
        if (senha != null && !senha.isBlank()) {
            usuario.setSenha(passwordEncoder.encode(senha));
        }
        
        usuario.setEmail(email);
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return EntityMapper.INSTANCE.usuarioToUsuarioDto(usuarioAtualizado);
    }

    public void deletarUsuario(Long idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new IllegalStateException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(idUsuario);
    }
}
