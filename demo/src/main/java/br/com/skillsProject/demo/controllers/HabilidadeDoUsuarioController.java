package br.com.skillsProject.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.skillsProject.demo.dtos.HabilidadeDoUsuarioDto;
import br.com.skillsProject.demo.dtos.HabilidadeDto;
import br.com.skillsProject.demo.dtos.UsuarioDto;
import br.com.skillsProject.demo.services.HabilidadeDoUsuarioService;
import br.com.skillsProject.demo.services.HabilidadeService;
import br.com.skillsProject.demo.services.UsuarioService;

@RestController
@RequestMapping("/habilidadedousuario")
public class HabilidadeDoUsuarioController {

    @Autowired
    private HabilidadeDoUsuarioService habilidadeDoUsuarioService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HabilidadeService habilidadeService;

    //Associa uma Habilidade ao Usuário
    @PostMapping("/associar")
    public ResponseEntity<HabilidadeDoUsuarioDto> associarHabilidade(@RequestParam Long usuarioId, @RequestParam Long habilidadeId, @RequestParam String levelUsuarioHab) {
        
        if (usuarioId == null || habilidadeId == null || levelUsuarioHab == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<UsuarioDto> usuario = usuarioService.encontrarUsuarioPorId(usuarioId);
        Optional<HabilidadeDto> habilidade = habilidadeService.buscarHabilidadePorId(habilidadeId);

        if (!usuario.isPresent() || !habilidade.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        HabilidadeDoUsuarioDto habilidadeDoUsuarioDto = new HabilidadeDoUsuarioDto();
        habilidadeDoUsuarioDto.setNomeUsuarioHab(habilidade.get().getNomeHab());
        habilidadeDoUsuarioDto.setLevelUsuarioHab(levelUsuarioHab);
        habilidadeDoUsuarioDto.setUsuario(usuario.get());

        try {
            HabilidadeDoUsuarioDto novaAssociacao = habilidadeDoUsuarioService.salvarHabilidade(habilidadeDoUsuarioDto);
            return ResponseEntity.ok(novaAssociacao);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //Atualizar Habilidade vinculada ao usuário
    @PutMapping("/atualizarHabilidadeDoUsuario/{id}")
    public ResponseEntity<HabilidadeDoUsuarioDto> atualizarHabilidade(@PathVariable Long id, @RequestBody HabilidadeDoUsuarioDto habilidadeAtualizada) {
        var habilidade = habilidadeDoUsuarioService.buscarHabilidadePorId(id);
        if (habilidade == null) {
            return ResponseEntity.notFound().build();
        }

        habilidade.setNomeUsuarioHab(habilidadeAtualizada.getNomeUsuarioHab());
        habilidade.setLevelUsuarioHab(habilidadeAtualizada.getLevelUsuarioHab());
        var habilidadeSalva = habilidadeDoUsuarioService.salvarHabilidade(habilidade);
        return ResponseEntity.ok(habilidadeSalva);
    }

    //Deleta uma habilidade associada ao usuário
    @DeleteMapping("/deletarHabilidadeDoUsuario/{habUsuarioId}")
    public ResponseEntity<Void> deletarHabilidade(@PathVariable Long habUsuarioId) {
        var habilidade = habilidadeDoUsuarioService.buscarHabilidadePorId(habUsuarioId);
        if (habilidade == null) {
            return ResponseEntity.notFound().build();
        }

        habilidadeDoUsuarioService.deletarHabilidade(habUsuarioId);
        return ResponseEntity.noContent().build();
    }

    //Listar varias habilidades de um usuário
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<HabilidadeDoUsuarioDto>> listarHabilidadesPorUsuario(@PathVariable Long usuarioId) {
        Optional<UsuarioDto> usuario = usuarioService.encontrarUsuarioPorId(usuarioId);
        if (!usuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        List<HabilidadeDoUsuarioDto> habilidades = habilidadeDoUsuarioService.listarHabilidadesPorUsuario(usuario.get());
        return ResponseEntity.ok(habilidades);
    }

    //Retorna uma habilidade do usuário pelo id da habilidade
    @GetMapping("/{habUsuarioId}")
    public ResponseEntity<HabilidadeDoUsuarioDto> obterHabilidadePorId(@PathVariable Long habUsuarioId) {
        var habilidade = habilidadeDoUsuarioService.buscarHabilidadePorId(habUsuarioId);
        if (habilidade == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(habilidade);
    }
}
