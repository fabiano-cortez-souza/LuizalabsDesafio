package br.com.luzialabs.desafio.agenda.vo;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AgendaVO { //extends PubSubTopicObject{

	private static final long serialVersionUID = 1L;
    
	@JsonProperty("timestamp")
	private String timestamp = null;

	
	@JsonProperty("msisdn")
	private String msisdn = null;

	@JsonProperty("type")
	private String type = null;

	@JsonProperty("amount")
	private String amount = null;

	@JsonProperty("description")
	private String description = null;

	@Id
	@JsonProperty("transactionID")
	private String transactionID = null;
	
	public AgendaVO() {}
	
	public AgendaVO(String timestamp,
								String msisdn,
								String type,
								String amount,
								String description,
								String transactionID) {
		
		this.timestamp = timestamp;
		this.msisdn = msisdn;
		this.type = type;
		this.amount = amount;
		this.description = description;
		this.transactionID = transactionID;
	}
	
	public AgendaVO(String timestamp,
								String msisdn,
								String type,
								String amount,
								String transactionID) {

		this.timestamp = timestamp;
		this.msisdn = msisdn;
		this.type = type;
		this.amount = amount;
		this.transactionID = transactionID;
	}
	
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
}
