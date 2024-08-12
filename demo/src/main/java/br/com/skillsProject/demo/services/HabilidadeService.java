package br.com.skillsProject.demo.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.skillsProject.demo.dtos.HabilidadeDto;
import br.com.skillsProject.demo.models.EntityMapper;
import br.com.skillsProject.demo.models.Habilidade;
import br.com.skillsProject.demo.repositories.HabilidadeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HabilidadeService {

    @Autowired
    private HabilidadeRepository habilidadeRepository;

    public HabilidadeDto salvarHabilidade(HabilidadeDto habilidadeDto) {
        Optional<Habilidade> existingHabilidade = habilidadeRepository.findByNomeHab(habilidadeDto.getNomeHab());
        if (existingHabilidade.isPresent()) {
            return EntityMapper.INSTANCE.habilidadeToHabilidadeDto(existingHabilidade.get());
        }

        Habilidade habilidade = EntityMapper.INSTANCE.habilidadeDtoToHabilidade(habilidadeDto);
        Habilidade novaHabilidade = habilidadeRepository.save(habilidade);
        return EntityMapper.INSTANCE.habilidadeToHabilidadeDto(novaHabilidade);
    }

    public Optional<HabilidadeDto> buscarHabilidadePorId(Long id) {
        return habilidadeRepository.findById(id)
                .map(EntityMapper.INSTANCE::habilidadeToHabilidadeDto);
    }

    public List<HabilidadeDto> buscarTodasHabilidades() {
        return habilidadeRepository.findAll().stream()
                .map(EntityMapper.INSTANCE::habilidadeToHabilidadeDto)
                .collect(Collectors.toList());
    }

    public HabilidadeDto atualizarHabilidade(Long id, HabilidadeDto habilidadeDto) {
        Habilidade habilidade = habilidadeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Habilidade não encontrada"));

        habilidade.setNomeHab(habilidadeDto.getNomeHab());
        habilidade.setNivelHab(habilidadeDto.getNivelHab());
        habilidade.setDescricao(habilidadeDto.getDescricao());
        habilidade.setImageUrl(habilidadeDto.getImageUrl());

        Habilidade habilidadeAtualizada = habilidadeRepository.save(habilidade);
        return EntityMapper.INSTANCE.habilidadeToHabilidadeDto(habilidadeAtualizada);
    }

    public void deletarHabilidade(Long id) {
        habilidadeRepository.deleteById(id);
    }
}
