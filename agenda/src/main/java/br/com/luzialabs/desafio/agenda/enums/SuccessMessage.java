package br.com.luzialabs.desafio.agenda.enums;

public enum SuccessMessage {
	
	AGENDA_SUCCESS(0, "The agenda write was successfully"),
	AGENDA_SEARCH_SUCCESS(0, "Search agenda was successfully"),
	AGENDA_DELETE(0, "The agenda delete was successfully");
	
	private String desc;
	private int code;
    
    private SuccessMessage(int code, String desc) {
        this.desc = desc;
        this.code = code;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public int getCode() {
    	return code;
    }
}
