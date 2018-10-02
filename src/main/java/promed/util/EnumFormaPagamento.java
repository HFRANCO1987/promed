package promed.util;

public enum EnumFormaPagamento implements IEnum{

	BOLETO("Boleto", "B", 0),
	DEBITO("Débito", "D", 1),
	CREDITO("Crédito", "C", 2);


	private final String descricao;
	private final String status;
	private final Integer index;

	EnumFormaPagamento(String descricao, String status, Integer index) {
		this.descricao = descricao;
		this.status = status;
		this.index  = index;
	}
	
	@Override
	public String getDescricao() {
		return descricao;
	}

	@Override
	public String getStatus() {
		return status;
	}
	
	@Override
	public Integer getIndex() {
		return index;
	}

}
