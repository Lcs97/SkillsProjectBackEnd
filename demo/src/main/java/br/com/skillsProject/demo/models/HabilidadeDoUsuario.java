package br.com.skillsProject.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "habilidadeDoUsuario")
@JsonIgnoreProperties(ignoreUnknown = true)
public class HabilidadeDoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long habUsuarioId;

    @NotBlank(message = "O nome da habilidade não pode estar em branco")
    private String nomeUsuarioHab;

    @NotBlank(message = "O nível da habilidade não pode estar em branco")
    private String levelUsuarioHab;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public HabilidadeDoUsuario() {
        super();
    }

    public HabilidadeDoUsuario(Long habUsuarioId, String nomeUsuarioHab, String levelUsuarioHab, Usuario usuario) {
        super();
        this.habUsuarioId = habUsuarioId;
        this.nomeUsuarioHab = nomeUsuarioHab;
        this.levelUsuarioHab = levelUsuarioHab;
        this.usuario = usuario;
    }

	public Long getHabUsuarioId() {
		return habUsuarioId;
	}

	public void setHabUsuarioId(Long habUsuarioId) {
		this.habUsuarioId = habUsuarioId;
	}

	public String getNomeUsuarioHab() {
		return nomeUsuarioHab;
	}

	public void setNomeUsuarioHab(String nomeUsuarioHab) {
		this.nomeUsuarioHab = nomeUsuarioHab;
	}

	public String getLevelUsuarioHab() {
		return levelUsuarioHab;
	}

	public void setLevelUsuarioHab(String levelUsuarioHab) {
		this.levelUsuarioHab = levelUsuarioHab;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
