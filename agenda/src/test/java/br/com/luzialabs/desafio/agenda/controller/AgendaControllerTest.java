package br.com.luzialabs.desafio.agenda.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import br.com.luzialabs.desafio.agenda.business.AgendaBusiness;
import br.com.luzialabs.desafio.agenda.dto.AgendaDTO;
import br.com.luzialabs.desafio.agenda.http.AgendaApiResponse;
import br.com.luzialabs.desafio.agenda.model.AgendaModel;
import br.com.luzialabs.desafio.agenda.repository.AgendaRepository;
import br.com.luzialabs.desafio.agenda.service.AgendaService;

@RunWith(MockitoJUnitRunner.class)
class AgendaControllerTest {

	public AgendaController agendaController;
	
	@Mock
	public AgendaBusiness agendaBusinessMock;

	@Mock
	private AgendaService agendaServiceMock;

	@Mock
	private AgendaRepository agendaRepositoryMock;

	private AgendaModel agendaModel;

	private AgendaDTO agendaDTO;
	
	private ResponseEntity<AgendaApiResponse> apiResponseTest;
	
    private MockHttpServletRequest request = new MockHttpServletRequest();
    private MockHttpServletResponse response = new MockHttpServletResponse();
    
    private ResponseEntity<AgendaApiResponse> apiResponse;
    
	@BeforeEach
	public void setup() {
		initMocks(this);
		final String msisdn = "5590988205339";
		final String startDate = "20191128";
		final String endDate = "20191129";
		final String numRecord = "2";
		final String numPage = "10";
		
		final String amount = "123456";
		final String description = "teste";
		final String id = "AasDAsSaAaaSaQqLAaa2";
	    final String timeStamp = "20191128T14:00:17-0300";
	    final String type = "refil";

	    agendaBusinessMock = new AgendaBusiness();
		agendaModel = new AgendaModel();
		agendaServiceMock = new AgendaService();
		agendaController = new AgendaController();

		agendaServiceMock.setAgendaRepository(agendaRepositoryMock);
		agendaBusinessMock.setAgendaService(agendaServiceMock);
	 	agendaController.setAgendaBusiness(agendaBusinessMock); 
		
	    agendaDTO = new AgendaDTO();
	    agendaDTO.setStartDate(startDate);
	    agendaDTO.setEndDate(endDate);
	    agendaDTO.setNumPage(numPage);
	    agendaDTO.setNumRecord(numRecord);
	    
        request.addHeader("x-application-key", "123456dev");
        response.addHeader("x-application-key", "123456dev");
	}

	@Test
	void contexLoadsNotNull() throws Exception {
		assertThat(agendaController).isNotNull();
	}
	
	@Test
    void transactionHistoryHealthyOK() {
	    
	    apiResponseTest = agendaController.agendaHealthy(request, response); 
        
        assertEquals(HttpStatus.OK, apiResponseTest.getStatusCode());
    }
}