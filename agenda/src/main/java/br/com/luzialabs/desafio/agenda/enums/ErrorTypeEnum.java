package br.com.luzialabs.desafio.agenda.enums;

import java.net.HttpURLConnection;

public enum ErrorTypeEnum {
	
    FIELD_VALIDATION_COMMUNICATION_TYPE_INVALID(1,"Communication type is invalid"),
	FIELD_VALIDATION_REQUIRED_FIELD_NOT_FOUND(1, "Required field not found"),
	FIELD_VALIDATION_NUMERIC_FIELD_INVALID(1, "Numeric field is invalid"),
	FIELD_VALIDATION_STATUS_TYPE_INVALID(2, "Status type is invalid"),
	FIELD_VALIDATION_TIMESTAMP_INVALID(4, "Timestamp format is invalid"),
	FIELD_VALIDATION_TYPE_INVALID(5, "Type field is invalid"),
	FIELD_VALIDATION_TYPE_DELETE_INVALID(5, "Type delete is invalid"),
	FIELD_VALIDATION_TRANSACTIONID_INVALID(6, "TransactionId field is invalid"),
	FIELD_VALIDATION_DESCRIPTION_INVALID(7, "Description field is invalid"),
	FIELD_VALIDATION_DATE_INVALID(4, "Date format is invalid, expected yyyyMMdd"),
	FIELD_VALIDATION_EMAIL_INVALID(4, "Email format is invalid"),
	FIELD_VALIDATION_NUMRECORDS_INVALID(3,"NumRecord format is invalid"),
	FIELD_VALIDATION_NUMPAGE_INVALID(3,"NumPage format is invalid"),
	DOCUMENT_ALREADY_EXISTS(8, "This event already exists"),
	DOCUMENT_NOT_EXISTS(8, "This event not exists"),
	DOCUMENT_NOT_WRITE_IN_DB(8, "This event not write in Data Base"),
	DOCUMENT_COUNT_LIMIT_OVERFLOW(8, "Limit return of database is overflow"),
	HTTP_CLIENT_TIMEOUT(HttpURLConnection.HTTP_CLIENT_TIMEOUT, "Request TimeOut"),
	HTTP_UNSUPPORTED_TYPE(HttpURLConnection.HTTP_UNSUPPORTED_TYPE, "Unsupported Media Type"),
	HTTP_RESPONSE_SEND_DENIED(8, "Send Request Denied");
	
    public final Integer code;
    private final String desc;
    
    private ErrorTypeEnum(Integer c, String s) {
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
    
    public static ErrorTypeEnum getFromCode(Integer code) {
        if (code == null || code == 0) {
            return null;
        }

        for (ErrorTypeEnum value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
