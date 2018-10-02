package promed.util;

public enum EnumStatusPedido implements IEnumCodigo {

	EMABERTO("Em Aberto", 0),
	FINALIZADO("Finalizado", 1);

	private final String descricao;
	private final Integer status;

	EnumStatusPedido(String descricao, Integer status) {
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
