package br.com.luzialabs.desafio.agenda.http;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.luzialabs.desafio.agenda.enums.ErrorType;
import br.com.luzialabs.desafio.agenda.enums.SuccessMessage;
import br.com.luzialabs.desafio.agenda.vo.AgendaVO;

public class AgendaApiResponse extends ApiResponse {
        
    @JsonInclude(Include.NON_NULL)
    private List<AgendaVO> agendas;

	public AgendaApiResponse() {}
	
	public AgendaApiResponse(SuccessMessage successMessage,
                       List<AgendaVO> agendas) {

	    this.setMessageDetail(successMessage.getDesc());
	    this.setCode(successMessage.getCode());
	    this.agendas = agendas;
	}
	
	public AgendaApiResponse(ErrorType errorType) {
		super(errorType);
    }
	
	public AgendaApiResponse(SuccessMessage successMessage) {
		super(successMessage);
    }
    
    public List<AgendaVO> getTransactions() {
        return agendas;
    }

    public void setTransactions(List<AgendaVO> transactions) {
        this.agendas = transactions;
    }
	
}
