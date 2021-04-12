package br.com.luzialabs.desafio.agenda.enums;

public enum RemocaoTipoEnum {

    BY_ID(1,"By Id"),
	BY_DATE(2,"By Date");
			
    public final Integer code;
    private final String desc;
    
    private RemocaoTipoEnum(Integer c, String s) {
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
    
    public static RemocaoTipoEnum getFromCode(Integer code) {
        if (code == null || code == 0) {
            return null;
        }

        for (RemocaoTipoEnum value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }

    public static boolean contains(String string) {
        for (RemocaoTipoEnum c : RemocaoTipoEnum.values()) {
            if (c.name().equals(string)) {
                return true;
            }
        }
        return false;
    }
    

}
