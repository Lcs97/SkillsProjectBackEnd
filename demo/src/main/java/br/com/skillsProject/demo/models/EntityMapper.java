package br.com.skillsProject.demo.models;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.skillsProject.demo.dtos.HabilidadeDoUsuarioDto;
import br.com.skillsProject.demo.dtos.HabilidadeDto;
import br.com.skillsProject.demo.dtos.UsuarioDto;

@Mapper
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    HabilidadeDto habilidadeToHabilidadeDto(Habilidade habilidade);
    Habilidade habilidadeDtoToHabilidade(HabilidadeDto habilidadeDto);

    HabilidadeDoUsuarioDto habilidadeDoUsuarioToHabilidadeDoUsuarioDto(HabilidadeDoUsuario habilidadeDoUsuario);
    HabilidadeDoUsuario habilidadeDoUsuarioDtoToHabilidadeDoUsuario(HabilidadeDoUsuarioDto habilidadeDoUsuarioDto);

    UsuarioDto usuarioToUsuarioDto(Usuario usuario);
    Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);
    
}
