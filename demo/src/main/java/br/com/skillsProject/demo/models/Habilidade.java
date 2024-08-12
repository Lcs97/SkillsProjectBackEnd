package br.com.skillsProject.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "habilidade")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Habilidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long habId;

    @NotBlank(message = "O nome da habilidade não pode estar em branco")
    private String nomeHab;

    @Column(nullable = false)
    private Integer nivelHab;
    
    @Column
    private String imageUrl;
    
    @Column
    private String descricao;



	public Habilidade() {
        super();
    }

    

    public Habilidade(Long habId, String nomeHab,
			Integer nivelHab, String imageUrl, String descricao) {
		super();
		this.habId = habId;
		this.nomeHab = nomeHab;
		this.nivelHab = nivelHab;
		this.imageUrl = imageUrl;
		this.descricao = descricao;
	}



	public String getImageUrl() {
    	return imageUrl;
    }
    
    public void setImageUrl(String imageUrl) {
    	this.imageUrl = imageUrl;
    }
    
    public String getDescricao() {
    	return descricao;
    }
    
    public void setDescricao(String descricao) {
    	this.descricao = descricao;
    }
    public Long getHabId() {
        return habId;
    }

    public void setHabId(Long habId) {
        this.habId = habId;
    }

    public String getNomeHab() {
        return nomeHab;
    }

    public void setNomeHab(String nomeHab) {
        this.nomeHab = nomeHab;
    }

    public int getNivelHab() {
        return nivelHab;
    }

    public void setNivelHab(Integer nivelHab) {
        this.nivelHab = nivelHab;
    }
}
