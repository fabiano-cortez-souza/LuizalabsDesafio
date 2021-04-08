package br.com.luzialabs.desafio.agenda;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import br.com.luzialabs.desafio.agenda.constants.Constants;

/** This Class is the Core of all microservices. It is an abstract Class
 * @author Gabriel Lima
 *
 */
@SpringBootApplication(scanBasePackages = { "br.com.luzialabs.desafio.agenda" })
@Configuration
public abstract class AgendaSettings {
	
	/** This methods sets the default TimeZone for all microservices
	 *
	 */
    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone(Constants.TIME_ZONE_ID));
    }
}
