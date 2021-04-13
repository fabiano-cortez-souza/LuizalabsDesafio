package br.com.luzialabs.desafio.agenda.enums;

public enum StatusEnvioEnum {

    ENVIADO(1,"ENVIADO"),
    NAO_ENVIADO(1,"NAO ENVIADO"),
    PENDENTE(2,"PENDENTE"),
	RECEBIDO(3,"RECEBIDO");
			
    public final Integer code;
    private final String desc;
    
    private StatusEnvioEnum(Integer c, String s) {
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
    
    public static StatusEnvioEnum getFromCode(Integer code) {
        if (code == null || code == 0) {
            return null;
        }

        for (StatusEnvioEnum value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }

    public static boolean contains(String string) {
        for (StatusEnvioEnum c : StatusEnvioEnum.values()) {
            if (c.name().equals(string)) {
                return true;
            }
        }
        return false;
    }
    
}
