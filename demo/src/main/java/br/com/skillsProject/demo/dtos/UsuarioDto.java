package br.com.skillsProject.demo.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDto {
    private Long idUsuario;
    private String username;
    private String senha;
    private String email;

    public UsuarioDto() {
        super();
    }

    public UsuarioDto(Long idUsuario, String username, String senha, String email) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.senha = senha;
        this.email = email;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
}
