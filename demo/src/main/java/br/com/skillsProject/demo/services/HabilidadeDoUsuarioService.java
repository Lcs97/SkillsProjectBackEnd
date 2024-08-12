package br.com.skillsProject.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.skillsProject.demo.dtos.HabilidadeDoUsuarioDto;
import br.com.skillsProject.demo.dtos.UsuarioDto;
import br.com.skillsProject.demo.models.EntityMapper;
import br.com.skillsProject.demo.models.HabilidadeDoUsuario;
import br.com.skillsProject.demo.models.Usuario;
import br.com.skillsProject.demo.repositories.HabilidadeDoUsuarioRepository;
import br.com.skillsProject.demo.repositories.UsuarioRepository; // Importar o repositório de Usuário

@Service
public class HabilidadeDoUsuarioService {

    @Autowired
    private HabilidadeDoUsuarioRepository habilidadeDoUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public HabilidadeDoUsuarioDto salvarHabilidade(HabilidadeDoUsuarioDto habilidadeDoUsuarioDto) {
        if (habilidadeDoUsuarioDto.getUsuario() == null || habilidadeDoUsuarioDto.getUsuario().getIdUsuario() == null) {
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo");
        }

        Long usuarioId = habilidadeDoUsuarioDto.getUsuario().getIdUsuario();

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        HabilidadeDoUsuario habilidadeDoUsuario = EntityMapper.INSTANCE.habilidadeDoUsuarioDtoToHabilidadeDoUsuario(habilidadeDoUsuarioDto);
        habilidadeDoUsuario.setUsuario(usuario);
        HabilidadeDoUsuario novaHabilidadeDoUsuario = habilidadeDoUsuarioRepository.save(habilidadeDoUsuario);
        return EntityMapper.INSTANCE.habilidadeDoUsuarioToHabilidadeDoUsuarioDto(novaHabilidadeDoUsuario);
    }



    public HabilidadeDoUsuarioDto buscarHabilidadePorId(Long habUsuarioId) {
        Optional<HabilidadeDoUsuario> habilidade = habilidadeDoUsuarioRepository.findById(habUsuarioId);
        return habilidade.map(EntityMapper.INSTANCE::habilidadeDoUsuarioToHabilidadeDoUsuarioDto).orElse(null);
    }

    public void deletarHabilidade(Long habUsuarioId) {
        habilidadeDoUsuarioRepository.deleteById(habUsuarioId);
    }

    public List<HabilidadeDoUsuarioDto> listarHabilidadesPorUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = EntityMapper.INSTANCE.usuarioDtoToUsuario(usuarioDto);
        return habilidadeDoUsuarioRepository.findByUsuario(usuario)
            .stream()
            .map(EntityMapper.INSTANCE::habilidadeDoUsuarioToHabilidadeDoUsuarioDto)
            .collect(Collectors.toList());
    }
}
