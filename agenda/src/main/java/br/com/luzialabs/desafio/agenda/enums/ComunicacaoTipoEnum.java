package br.com.luzialabs.desafio.agenda.enums;

public enum ComunicacaoTipoEnum {

    EMAIL(1,"EMAIL"),
    SMS(2,"SMS"),
	PUSH(3,"PUSH"),
	WHATSAPP(4,"WHATSAPP");
			
    public final Integer code;
    private final String desc;
    
    private ComunicacaoTipoEnum(Integer c, String s) {
        this.code = c;
        this.desc = s;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
    
    public String getEnumName() {
        return name();
    }
    
    public static ComunicacaoTipoEnum getFromCode(Integer code) {
        if (code == null || code == 0) {
            return null;
        }

        for (ComunicacaoTipoEnum value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }

    public static boolean contains(String string) {
        for (ComunicacaoTipoEnum c : ComunicacaoTipoEnum.values()) {
            if (c.name().equals(string)) {
                return true;
            }
        }
        return false;
    }
    

}
