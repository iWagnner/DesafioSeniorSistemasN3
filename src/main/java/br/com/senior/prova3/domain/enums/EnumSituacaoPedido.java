package br.com.senior.prova3.domain.enums;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public enum EnumSituacaoPedido {
    ABERTO(0, "Aberto"), //
    CANCELADO(1, "Cancelado"), //
    FINALIZADO(2, "Finalizado"), //
    FATURADO(3, "Faturado"), //
    EXPIRADO(4, "Expirado"), //
    FECHADO(5, "Fechado"), //
    FATURADO_POR_NF(6, "Faturado por Nota Fiscal"), //
    EM_FATURAMENTO(7, "Em Faturamento"), //
    EM_ORCAMENTO(8, "Em Orçamento"), //
    MESCLADO(99, "Mesclado");

    private Integer id;
    private String descricao;

    private EnumSituacaoPedido(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    /**
     * Pega a situação conforme o ID ou descrição da situação do pedido.
     * 
     * @param value a descrição ou o ID da situação.
     * @return a situacao do pedido se existir, caso contrário retornará <code>null</code>.
     */
    public static EnumSituacaoPedido getSituacaoPedido(String valor) {
        if (valor == null) {
            return null;
        }

        try { //Se for um numero, pesquisa pelo identificador.
            Integer value = Integer.valueOf(valor);
            return getSituacaoPedido(value);
        } catch (NumberFormatException e) {
            //Like a magayver
        }

        for (EnumSituacaoPedido situacao : values()) {
            if (situacao.getDescricao().equalsIgnoreCase(valor)) {
                return situacao;
            }
        }
        return null;
    }

    public static EnumSituacaoPedido getSituacaoPedido(Integer idSituacao) {
        for (EnumSituacaoPedido situacao : values()) {
            if (situacao.getId().equals(idSituacao)) {
                return situacao;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return new StringBuilder().append(id).append(" - ").append(descricao).toString();
    }

    public boolean isFaturado() {
        return this == FATURADO || this == FATURADO_POR_NF;
    }

    public boolean isExpirado() {
        return this == EXPIRADO;
    }
    
    public static EnumSituacaoPedido toEnum(Integer id) {
		if (id == null) {
			return null;
		}

		for (EnumSituacaoPedido x : EnumSituacaoPedido.values()) {
			if (id.equals(x.getId())) {
				return x;
			}
		}
		throw new IllegalArgumentException("A Situação do Pedido é Inválida");
	}
}
