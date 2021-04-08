package br.com.luzialabs.desafio.agenda.vo;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.luzialabs.desafio.agenda.utils.JsonUtils;

public class AgendaVO { 

	private static final long serialVersionUID = 1L;
    
    @Id
    @JsonProperty("id")
    private long id;

    @JsonProperty("dataHora")
    private String dataHora;

    @JsonProperty("destinatario")
    private String destinatario;

    @JsonProperty("mensagem")
    private String mensagem;    
    
    @JsonProperty("comunicacaoTipo")
    private String comunicacaoTipo;
    
    @JsonProperty("statusEnvio")
    private String statusEnvio; 
	
	public AgendaVO() {}
	
	public AgendaVO(long id,
					String dataHora,
					String destinatario,
					String mensagem,
					String comunicacaoTipo,
					String statusEnvio) {
		
	    this.id = id;
	    this.dataHora = dataHora;
	    this.destinatario = destinatario;
	    this.mensagem = mensagem;    
	    this.comunicacaoTipo = comunicacaoTipo;
	    this.statusEnvio = statusEnvio; 	
	}
	
    @Override
    public String toString() {
        return JsonUtils.parseToJsonString(this);
    }	

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getStatusEnvio() {
        return statusEnvio;
    }

    public void setStatusEnvio(String statusEnvio) {
        this.statusEnvio = statusEnvio;
    }
}
