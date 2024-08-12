package br.com.skillsProject.demo.dtos;

public class HabilidadeDto {
    private Long habId;
    private String nomeHab;
    private Integer nivelHab;
    private String imageUrl;
    private String descricao;


    public HabilidadeDto() {
        super();
    }

    public HabilidadeDto(Long habId, String nomeHab, Integer nivelHab, String imageUrl, String descricao) {
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

    public Integer getNivelHab() {
        return nivelHab;
    }

    public void setNivelHab(Integer nivelHab) {
        this.nivelHab = nivelHab;
    }
}
