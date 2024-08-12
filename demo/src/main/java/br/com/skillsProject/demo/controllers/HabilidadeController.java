package br.com.skillsProject.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import br.com.skillsProject.demo.dtos.HabilidadeDto;
import br.com.skillsProject.demo.services.HabilidadeService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/habilidades")
public class HabilidadeController {

    @Autowired
    private HabilidadeService habilidadeService;

    @PostMapping
    public ResponseEntity<HabilidadeDto> adicionarHabilidade(@RequestBody HabilidadeDto habilidadeDto) {
        try {
            HabilidadeDto novaHabilidade = habilidadeService.salvarHabilidade(habilidadeDto);
            return ResponseEntity.ok(novaHabilidade);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabilidadeDto> buscarHabilidadePorId(@PathVariable Long id) {
        Optional<HabilidadeDto> habilidade = habilidadeService.buscarHabilidadePorId(id);
        if (!habilidade.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(habilidade.get());
    }

    @GetMapping
    public ResponseEntity<List<HabilidadeDto>> buscarTodasHabilidades() {
        List<HabilidadeDto> habilidades = habilidadeService.buscarTodasHabilidades();
        return ResponseEntity.ok(habilidades);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabilidadeDto> atualizarHabilidade(@PathVariable Long id, @RequestBody HabilidadeDto habilidadeDto) {
        HabilidadeDto habilidadeAtualizada = habilidadeService.atualizarHabilidade(id, habilidadeDto);
        return ResponseEntity.ok(habilidadeAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarHabilidade(@PathVariable Long id) {
        habilidadeService.deletarHabilidade(id);
        return ResponseEntity.noContent().build();
    }
}
