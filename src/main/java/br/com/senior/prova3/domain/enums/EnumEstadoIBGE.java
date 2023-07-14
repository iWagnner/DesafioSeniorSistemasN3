package br.com.senior.prova3.domain.enums;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public enum EnumEstadoIBGE {

	ACRE(12, "AC"), // 12|AC|ACRE
	ALAGOAS(27, "AL"), // 27|AL|ALAGOAS
	AMAPA(16, "AP"), // 16|AP|AMAPA
	AMAZONAS(13, "AM"), // 13|AM|AMAZONAS
	BAHIA(29, "BA"), // 29|BA|BAHIA
	CEARA(23, "CE"), // 23|CE|CEARA
	DISTRITO_FEDERAL(53, "DF"), // 53|DF|DISTRITO FEDERAL
	ESPIRITO_SANTO(32, "ES"), // 32|ES|ESPIRITO SANTO
	GOIAS(52, "GO"), // 52|GO|GOIAS
	MARANHAO(21, "MA"), // 21|MA|MARANHAO
	MATO_GROSSO(51, "MT"), // 51|MT|MATO GROSSO
	MATO_GROSSO_DO_SUL(50, "MS"), // 50|MS|MATO GROSSO DO SUL
	MINAS_GERAIS(31, "MG"), // 31|MG|MINAS GERAIS
	PARA(15, "PA"), // 15|PA|PARA
	PARAIBA(25, "PB"), // 25|PB|PARAIBA
	PARANA(41, "PR"), // 41|PR|PARANA
	PERNAMBUCO(26, "PE"), // 26|PE|PERNAMBUCO
	PIAUI(22, "PI"), // 22|PI|PIAUI
	RIO_DE_JANEIRO(33, "RJ"), // 33|RJ|RIO DE JANEIRO
	RIO_GRANDE_DO_NORTE(24, "RN"), // 24|RN|RIO GRANDE DO NORTE
	RIO_GRANDE_DO_SUL(43, "RS"), // 43|RS|RIO GRANDE DO SUL
	RONDONIA(11, "RO"), // 11|RO|RONDONIA
	RORAIMA(14, "RR"), // 14|RR|RORAIMA
	SANTA_CATARINA(42, "SC"), // 42|SC|SANTA CATARINA
	SAO_PAULO(35, "SP"), // 35|SP|SAO PAULO
	SERGIPE(28, "SE"), // 28|SE|SERGIPE
	TOCANTINS(17, "TO");// 17|TO|TOCANTINS

	private int codigo;
	private String uf;

	private EnumEstadoIBGE(int codigo, String uf) {
		this.codigo = codigo;
		this.uf = uf;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getUf() {
		return uf;
	}

	public static EnumEstadoIBGE toEnum(String uf) {
		if (uf == null) {
			return null;
		}

		for (EnumEstadoIBGE x : EnumEstadoIBGE.values()) {
			if (uf.trim().toUpperCase().equals(x.getUf())) {
				return x;
			}
		}
		throw new IllegalArgumentException("UF Inválida");
	}

}
