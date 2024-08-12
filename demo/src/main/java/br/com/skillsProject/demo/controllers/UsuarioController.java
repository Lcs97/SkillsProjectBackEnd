package br.com.skillsProject.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.skillsProject.demo.dtos.UsuarioDto;
import br.com.skillsProject.demo.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PutMapping("/atualizar/{usuarioId}")
    public ResponseEntity<UsuarioDto> atualizarUsuario(
            @PathVariable Long usuarioId,
            @RequestBody UsuarioDto usuarioDto) {
        try {
            var usuarioAtualizado = usuarioService.atualizarUsuario(
                    usuarioId, usuarioDto.getUsername(), usuarioDto.getSenha(), usuarioDto.getEmail());
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/deletar/{usuarioId}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long usuarioId) {
        try {
            usuarioService.deletarUsuario(usuarioId);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(404).build();
        }
    }
}
