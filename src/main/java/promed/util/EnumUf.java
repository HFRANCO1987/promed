package promed.util;



public enum EnumUf implements IEnum {

	AC("Acre", "AC", 1),
	AL("Alagoas", "AL", 2), 
	AP("Amapa", "AP", 3), 
	AM("Amazonas", "AM", 4), 
	BA("Bahia", "BA", 5), 
	CE("Ceará", "CE", 6), 
	DF("Distrito Federal", "DF", 7), 
	ES("Espirito Santo", "ES", 8), 
	GO("Goiás", "GO", 9), 
	MA("Maranhão", "MA", 10), 
	MT("Mato Grosso", "MT", 11), 
	MS("Mato Grosso do Sul", "MS",12), 
	MG("Minas Gerais", "MG",13), 
	PA("Pará", "PA",14), 
	PB("Paraíba", "PB",15), 
	PR("Paraná", "PR",16), 
	PE("Pernambuco", "PE",17), 
	PI("Piauí", "PI",18), 
	RJ("Rio de Janeiro", "RJ",19), 
	RN("Rio Grande do Norte", "RN",20), 
	RS("Rio Grande do Sul", "RS",21), 
	RO("Rondonia", "RO",22), 
	RR("Roraima", "RR",23), 
	SC("Santa Catarina", "SC",24), 
	SP("São Paulo", "SP",25), 
	SE("Sergipe", "SE",26), 
	TO("Tocantins", "TO",27);

	private final String descricao;
	private final String status;
	private final Integer index;

	EnumUf(String descricao, String status, Integer index) {
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
