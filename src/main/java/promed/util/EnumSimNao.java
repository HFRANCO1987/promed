package promed.util;

public enum EnumSimNao implements IEnumCodigo {

	N("Não", 0),
	Y("Sim", 1);

	private final String descricao;
	private final Integer status;

	EnumSimNao(String descricao, Integer status) {
		this.descricao  = descricao;
		this.status      = status;
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
