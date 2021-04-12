package br.com.luzialabs.desafio.agenda.enums;

public enum TimeLimitEnum {

    STARTTIME(1,"STARTTIME"),
    ENDTIME(2,"ENDTIME");
			
    public final Integer code;
    private final String desc;
    
    private TimeLimitEnum(Integer c, String s) {
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
    
    public static TimeLimitEnum getFromCode(Integer code) {
        if (code == null || code == 0) {
            return null;
        }

        for (TimeLimitEnum value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }

    public static boolean contains(String string) {
        for (TimeLimitEnum c : TimeLimitEnum.values()) {
            if (c.name().equals(string)) {
                return true;
            }
        }
        return false;
    }
    

}
