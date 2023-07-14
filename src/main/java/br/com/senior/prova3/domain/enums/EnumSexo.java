package br.com.senior.prova3.domain.enums;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public enum EnumSexo {

    MASCULINO("M", "Masculino"),
    FEMININO("F", "Feminino"),
    OUTROS("O", "Outros");


    private String id;
    private String descricao;

    private EnumSexo(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String toString() {
        return new StringBuilder()
                .append(id)
                .append(" - ")
                .append(descricao)
                .toString();
    }
    
    public static EnumSexo toEnum(String id) {
		if (id == null) {
			return null;
		}

		for (EnumSexo x : EnumSexo.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Sexo Inválido");
	}

}
