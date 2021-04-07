package br.com.luzialabs.desafio.agenda.enums;

public enum SuccessMessage {
	
	TRANSACTION_SUCCESS(0, "The transaction was successfully"),
	TRANSACTION_SEARCH_SUCCESS(0, "Search was successfully"),
    OCS_SUCCESS(0, "The OCS was successfully");
	
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
