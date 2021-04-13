package br.com.luzialabs.desafio.agenda.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import br.com.luzialabs.desafio.agenda.constants.Constants;
import br.com.luzialabs.desafio.agenda.enums.TimeLimitEnum;

public class DateUtils {
    
    private DateUtils() {
        throw new IllegalStateException("Utility class");
    }
    
	public static LocalDateTime timestampValidation(String timestamp){
        DateTimeFormatter format = DateTimeFormatter.ofPattern(Constants.TIMESTAMP_FORMAT);
        return LocalDateTime.parse(timestamp, format);
	}

	public static LocalDate dateValidation(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        return LocalDate.parse(date, format);
	}
		
	public static LocalDate getLocalDateOf(String date) {
		return dateValidation(date);
	}

	@SuppressWarnings("deprecation")
    public static String parseDateToTimestamp(String data, TimeLimitEnum timeLimitEnum) throws ParseException {
	       
	       Date date = new SimpleDateFormat(Constants.DATE_FORMAT).parse(data);
           String iso8601DtFormatini = new SimpleDateFormat(Constants.TIMESTAMP_FORMAT).format(date);
	  
           date.setHours(23);
           date.setMinutes(59);
           date.setSeconds(59);
           String iso8601DtFormatEnd = new SimpleDateFormat(Constants.TIMESTAMP_FORMAT).format(date);
           
           if (timeLimitEnum.getCode() == 1) {
               return iso8601DtFormatini;
           }
          
           return iso8601DtFormatEnd;
	}
}
