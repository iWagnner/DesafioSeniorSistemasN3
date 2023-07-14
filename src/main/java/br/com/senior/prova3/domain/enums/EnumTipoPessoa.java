package br.com.senior.prova3.domain.enums;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public enum EnumTipoPessoa {
	 	FISICA(0, "FISICA"),
	    JURIDICA(1, "JURIDICA");

	    private Integer id;
	    private String descricao;

	    EnumTipoPessoa(Integer id, String descricao) {
	        this.id = id;
	        this.descricao = descricao;
	    }

	    public Integer getId() {
	        return id;
	    }

	    public String getDescricao() {
	        return descricao;
	    }

	    public static EnumTipoPessoa toEnum(Integer id){
	        if (id == null){
	            return null;
	        }

	        for (EnumTipoPessoa x: EnumTipoPessoa.values()){
	            if(id.equals(x.getId())){
	                return x;
	            }
	        }
	        throw new IllegalArgumentException("Tipo Perfil Entidade Inválido");
	    }

}
