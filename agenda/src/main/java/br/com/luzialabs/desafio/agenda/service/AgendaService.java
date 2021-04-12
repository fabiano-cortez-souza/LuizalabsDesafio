package br.com.luzialabs.desafio.agenda.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.luzialabs.desafio.agenda.http.HttpRequest;
import br.com.luzialabs.desafio.agenda.model.AgendaModel;
import br.com.luzialabs.desafio.agenda.repository.AgendaRepository;

@Service
public class AgendaService extends HttpRequest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgendaService.class);

    @Autowired
    private AgendaRepository agendaRepository;

    public String envioPostRequest(Object payload, String address, String content_type) throws Exception {

        String response = null;
        LOGGER.info("[EnvioPostRequest] - enviando post Request IP {} - CONTENT-TYPE: {} ", address, content_type);
        response = sendPostRequest(payload.toString(), address, content_type);

        return response;
    }

    public boolean save(AgendaModel transactionHistoryDocument) {
        try {
            agendaRepository.save(transactionHistoryDocument);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean eventExists(long id) {
        return agendaRepository.existsById(id);
    }

    public List<AgendaModel> findAgendaById(long id) {
        return agendaRepository.findById(id);
    }

    public boolean delete(AgendaModel agenda) {
        try {
            agendaRepository.delete(agenda);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<AgendaModel> getAgendaByDataHora(String strDate, String endDate) {
        return agendaRepository.getByDataHora(strDate, endDate);
    }

    public long countDocuments(String strDate, String endDate) {
        return agendaRepository.countWithDataHoraRange(strDate, endDate);
    }

    public long countDocument(long id) {
        return agendaRepository.findById(id).size();
    }

    public AgendaRepository getAgendaRepository() {
        return agendaRepository;
    }

    public void setAgendaRepository(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

}
