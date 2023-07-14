package br.com.senior.prova3.domain.enums;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public enum EnumPerfil {
    FORNECEDOR("F", "ROLE_FORNECEDOR"),
    CLIENTE("C", "ROLE_CLIENTE"),
    USUARIO("U", "ROLE_USUARIO");

    private String id;
    private String descricao;

    EnumPerfil(String id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static EnumPerfil toEnum(String id){
        if (id == null){
            return null;
        }

        for (EnumPerfil x: EnumPerfil.values()){
            if(id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil Inválido");
    }
}
