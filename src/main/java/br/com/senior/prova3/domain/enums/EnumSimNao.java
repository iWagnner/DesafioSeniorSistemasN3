package br.com.senior.prova3.domain.enums;
/**
 * Autor: Wagner Ribeiro
 * Data: 12 de julho de 2023
 */
public enum EnumSimNao {

    NAO("N", "Não"),
    SIM("S", "Sim");

    private String id;
    private String descricao;

    private EnumSimNao(String id, String descricao) {
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
        return new StringBuilder().append(id).append(" - ").append(descricao).toString();
    }

    public boolean isSim() {
        return this.equals(SIM);
    }

    public boolean isNao() {
        return this.equals(NAO);
    }

    public static EnumSimNao fromChar(String str) {
        if (str == null) {
            return null;
        }
        str = str.toUpperCase();
        if ("S".equals(str)) {
            return EnumSimNao.SIM;
        }
        if ("N".equals(str)) {
            return EnumSimNao.NAO;
        }
        return null;
    }

    public static boolean toBoolean(EnumSimNao value) {
        if (value == EnumSimNao.SIM) {
            return true;
        }
        return false;
    }

    public static boolean toBooleanDef(EnumSimNao value, boolean defValue) {
        if (value == null) {
            return defValue;
        }
        switch (value) {
            case SIM:
                return true;
            case NAO:
                return false;
            default:
                return defValue;
        }
    }

    public static Boolean toBooleanNullable(EnumSimNao value) {
        if (value == null) {
            return null;
        }
        switch (value) {
            case SIM:
                return true;
            case NAO:
                return false;
            default:
                return null;
        }
    }

    public static String toCharDef(EnumSimNao value, String defValue) {
        if (value == null) {
            return defValue;
        }
        switch (value) {
            case SIM:
                return "S";
            case NAO:
                return "N";
            default:
                return defValue;
        }
    }

    public static String toCharNullable(EnumSimNao value) {
        return toCharDef(value, null);
    }
    
    public static EnumSimNao toEnum(String id){
        if (id == null){
            return null;
        }

        for (EnumSimNao x: EnumSimNao.values()){
            if(id.equals(x.getId())){
                return x;
            }
        }
        throw new IllegalArgumentException("Tipo Inválido");
    }
}
