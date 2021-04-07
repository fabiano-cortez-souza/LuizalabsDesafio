package br.com.luzialabs.desafio.agenda.dto;

import javax.persistence.Column;

public class AgendaDTO {
	
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

    public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	
}
