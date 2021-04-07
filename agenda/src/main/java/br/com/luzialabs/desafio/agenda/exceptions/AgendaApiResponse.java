package br.com.luzialabs.desafio.agenda.exceptions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.luzialabs.desafio.agenda.enums.ErrorType;
import br.com.luzialabs.desafio.agenda.enums.SuccessMessage;
import br.com.luzialabs.desafio.agenda.exceptions.ApiResponse;
import br.com.luzialabs.desafio.agenda.vo.AgendaVO;

public class AgendaApiResponse extends ApiResponse {

    @JsonInclude(Include.NON_NULL)
    private Integer currentPage;
    
    @JsonInclude(Include.NON_NULL)
    private Integer totalNumPage;

    @JsonInclude(Include.NON_NULL)
    private Long totalNumRecord;
    
    @JsonInclude(Include.NON_NULL)
    private List<AgendaVO> agendas;

	public AgendaApiResponse() {}
	
	public AgendaApiResponse(SuccessMessage successMessage,
                       Integer currentPage,
                       Integer totalNumPage,
                       Long totalNumRecord,
                       List<AgendaVO> agendas) {

	    this.setMessageDetail(successMessage.getDesc());
	    this.setCode(successMessage.getCode());
	    this.currentPage = currentPage;
	    this.totalNumPage = totalNumPage;
	    this.totalNumRecord = totalNumRecord;
	    this.agendas = agendas;
	}
	
	public AgendaApiResponse(ErrorType errorType) {
		super(errorType);
    }
	
	public AgendaApiResponse(SuccessMessage successMessage) {
		super(successMessage);
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalNumPage() {
        return totalNumPage;
    }

    public void setTotalNumPage(Integer totalNumPage) {
        this.totalNumPage = totalNumPage;
    }

    public Long getTotalNumRecord() {
        return totalNumRecord;
    }

    public void setTotalNumRecord(Long totalNumRecord) {
        this.totalNumRecord = totalNumRecord;
    }
    
    public List<AgendaVO> getTransactions() {
        return agendas;
    }

    public void setTransactions(List<AgendaVO> transactions) {
        this.agendas = transactions;
    }
	
}
