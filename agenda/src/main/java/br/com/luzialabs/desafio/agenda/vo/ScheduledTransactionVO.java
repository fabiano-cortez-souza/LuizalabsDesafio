package br.com.luzialabs.desafio.agenda.vo;

import br.com.luzialabs.desafio.agenda.utils.JsonUtils;

public class ScheduledTransactionVO {

	private String arrivedAt;
	
	private String dateToBeProcessed;

	private AgendaVO transactionHistory;

	public String getArrivedAt() {
		return arrivedAt;
	}

	public void setArrivedAt(String arrivedAt) {
		this.arrivedAt = arrivedAt;
	}

	public String getDateToBeProcessed() {
		return dateToBeProcessed;
	}

	public void setDateToBeProcessed(String dateToBeProcessed) {
		this.dateToBeProcessed = dateToBeProcessed;
	}

	public AgendaVO getTransactionHistory() {
		return transactionHistory;
	}

	public void setTransactionHistory(AgendaVO transactionHistory) {
		this.transactionHistory = transactionHistory;
	}
	
	@Override
	public String toString() {
		return JsonUtils.parseToJsonString(this);
	}

}
