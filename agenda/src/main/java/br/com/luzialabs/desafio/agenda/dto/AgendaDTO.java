package br.com.luzialabs.desafio.agenda.dto;

import javax.persistence.Column;

import br.com.luzialabs.desafio.agenda.base.ParseObjToJsonString;
import br.com.luzialabs.desafio.agenda.enums.RemocaoTipoEnum;

public class AgendaDTO extends ParseObjToJsonString {
    
    @Column(name = "dataHora")
    private String dataHora;

    @Column(name = "destinatario")
    private String destinatario;

    @Column(name = "mensagem")
    private String mensagem;    
    
    @Column(name = "comunicacaoTipo")
    private String comunicacaoTipo;
	
    @Column(name = "requestId")
	private long requestId;

    @Column(name = "startDate")
	private String startDate;

    @Column(name = "endDate")
	private String endDate;

    @Column(name = "numPage")
	private String numPage;

    @Column(name = "numRecord")
	private String numRecord;
    
    @Column(name = "remocaoTipoEnum")
    private RemocaoTipoEnum remocaoTipoEnum;

    public AgendaDTO() {};
    
    public AgendaDTO(String dataHora, String destinatario, String mensagem, String comunicacaoTipo) {
        super();
        this.dataHora = dataHora;
        this.destinatario = destinatario;
        this.mensagem = mensagem;
        this.comunicacaoTipo = comunicacaoTipo;
    }

    public AgendaDTO(String string, String string2, String string3, String desc, String desc2) {
        // TODO Auto-generated constructor stub
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getComunicacaoTipo() {
        return comunicacaoTipo;
    }

    public void setComunicacaoTipo(String comunicacaoTipo) {
        this.comunicacaoTipo = comunicacaoTipo;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getNumPage() {
        return numPage;
    }

    public void setNumPage(String numPage) {
        this.numPage = numPage;
    }

    public String getNumRecord() {
        return numRecord;
    }

    public void setNumRecord(String numRecord) {
        this.numRecord = numRecord;
    }

    public RemocaoTipoEnum getRemocaoTipoEnum() {
        return remocaoTipoEnum;
    }

    public void setRemocaoTipoEnum(RemocaoTipoEnum remocaoTipoEnum) {
        this.remocaoTipoEnum = remocaoTipoEnum;
    }

		
}
