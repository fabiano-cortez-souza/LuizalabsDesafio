package br.com.luzialabs.desafio.agenda.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luzialabs.desafio.agenda.model.AgendaModel;
import br.com.luzialabs.desafio.agenda.repository.AgendaRepository;

@Service
public class AgendaService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AgendaService.class);

	@Autowired
	private AgendaRepository agendaRepository;

	public boolean save(AgendaModel transactionHistoryDocument) {
		try {
			agendaRepository.save(transactionHistoryDocument);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean eventExists(String transactionId) {
		return agendaRepository.existsById(transactionId);
	}

	public List<AgendaModel> findAgendaById(long id) {
		return agendaRepository.findById(id);
	}
/*
	public long countDocuments(String msisdn, String strDate, String endDate) {
		return agendaRepository.countWithTimeStampRange(msisdn, strDate, endDate);
	}
*/
	public AgendaRepository getAgendaRepository() {
		return agendaRepository;
	}

	public void setAgendaRepository(AgendaRepository agendaRepository) {
		this.agendaRepository = agendaRepository;
	}
}
