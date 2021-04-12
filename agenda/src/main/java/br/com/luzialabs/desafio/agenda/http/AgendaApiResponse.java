package br.com.luzialabs.desafio.agenda.http;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.luzialabs.desafio.agenda.dto.AgendaDTO;
import br.com.luzialabs.desafio.agenda.enums.ErrorType;
import br.com.luzialabs.desafio.agenda.enums.SuccessMessage;
import br.com.luzialabs.desafio.agenda.model.AgendaModel;
import br.com.luzialabs.desafio.agenda.vo.AgendaVO;

public class AgendaApiResponse extends ApiResponse {
        
    @JsonInclude(Include.NON_NULL)
    private List<AgendaVO> agendas;

    @JsonInclude(Include.NON_NULL)
    private List<AgendaModel> listagendas;
    
    @JsonInclude(Include.NON_NULL)
    private AgendaDTO agendaDTO;
    
    @JsonInclude(Include.NON_NULL)
    private AgendaModel agendaModel;
    
	public AgendaApiResponse() {}
	
	public AgendaApiResponse(SuccessMessage successMessage, List<AgendaVO> agendas) {
	    this.setMessageDetail(successMessage.getDesc());
	    this.setCode(successMessage.getCode());
	    this.agendas = agendas;
	}
	
    public AgendaApiResponse(SuccessMessage successMessage, AgendaDTO agendaDTO) {
        this.setMessageDetail(successMessage.getDesc());
        this.setCode(successMessage.getCode());
        this.agendaDTO = agendaDTO;
    }
    
    public AgendaApiResponse(SuccessMessage successMessage, AgendaModel agenda) {
        this.setMessageDetail(successMessage.getDesc());
        this.setCode(successMessage.getCode());
        this.agendaModel = agenda;
    }
    
	public List<AgendaModel> getListagendas() {
        return listagendas;
    }

    public void setListagendas(List<AgendaModel> listagendas) {
        this.listagendas = listagendas;
    }

    public AgendaApiResponse(ErrorType errorType) {
		super(errorType);
    }
	
	public AgendaApiResponse(SuccessMessage successMessage) {
		super(successMessage);
    }

    public AgendaModel getAgendaModel() {
        return agendaModel;
    }

    public void setAgendaModel(AgendaModel agendaModel) {
        this.agendaModel = agendaModel;
    }

    public List<AgendaVO> getAgendas() {
        return agendas;
    }

    public void setAgendas(List<AgendaVO> agendas) {
        this.agendas = agendas;
    }

    public AgendaDTO getAgendaDTO() {
        return agendaDTO;
    }

    public void setAgendaDTO(AgendaDTO agendaDTO) {
        this.agendaDTO = agendaDTO;
    }
	
}
