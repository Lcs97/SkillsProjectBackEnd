package br.com.skillsProject.demo.dtos;


public class HabilidadeDoUsuarioDto {
	 
	    private Long habUsuarioId;
	    private String nomeUsuarioHab;
	    private String levelUsuarioHab;
	    private UsuarioDto usuario;

	    public HabilidadeDoUsuarioDto() {
	        super();
	    }

	    public HabilidadeDoUsuarioDto(Long habUsuarioId, String nomeUsuarioHab, String levelUsuarioHab, UsuarioDto usuario) {
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

		public UsuarioDto getUsuario() {
			return usuario;
		}

		public void setUsuario(UsuarioDto usuario) {
			this.usuario = usuario;
		}

}
