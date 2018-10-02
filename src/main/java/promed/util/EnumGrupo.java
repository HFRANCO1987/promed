package promed.util;



public enum EnumGrupo implements IEnumCodigo {
	
	ADMIN("ADMIN", 0),
	CLIENTE("CLIENTE",  1);

	private final String descricao;
	private final Integer status;
	
	private EnumGrupo(String descricao, Integer status) {
		this.descricao = descricao;
		this.status = status;
	}

	@Override
	public String getDescricao() {
		return descricao;
	}

	@Override
	public Integer getStatus() {
		return status;
	}
	
}
