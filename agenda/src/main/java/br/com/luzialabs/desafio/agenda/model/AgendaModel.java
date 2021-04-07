package br.com.luzialabs.desafio.agenda.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.luzialabs.desafio.agenda.constants.Constants;

@Entity
@Table(name = "Agenda")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AgendaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "dataHora")
    private String dataHora;

    @Column(name = "destinatario")
    private String destinatario;

    @Column(name = "mensagem",length=Constants.MAX_LENGTH_MESSAGE)
    private String mensagem;    
    
    @Column(name = "comunicacaoTipo")
    private String comunicacaoTipo;
    
    @Column(name = "statusEnvio")
    private String statusEnvio; 
    
    public AgendaModel() {
    }

    public AgendaModel(String datahora, String destinatario, String mensagem) {
        this.dataHora = datahora; //DateUtils.timestampValidation(datahora);
        this.destinatario = destinatario;
        this.mensagem = mensagem;
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
