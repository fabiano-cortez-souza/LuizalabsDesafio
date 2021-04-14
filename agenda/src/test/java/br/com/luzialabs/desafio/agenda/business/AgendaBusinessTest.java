package br.com.luzialabs.desafio.agenda.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.luzialabs.desafio.agenda.dto.AgendaDTO;
import br.com.luzialabs.desafio.agenda.enums.ComunicacaoTipoEnum;
import br.com.luzialabs.desafio.agenda.enums.StatusEnvioEnum;
import br.com.luzialabs.desafio.agenda.http.AgendaApiResponse;
import br.com.luzialabs.desafio.agenda.model.AgendaModel;
import br.com.luzialabs.desafio.agenda.repository.AgendaRepository;
import br.com.luzialabs.desafio.agenda.service.AgendaService;

@RunWith(MockitoJUnitRunner.class)
class AgendaBusinessTest {

    public AgendaBusiness agendaBusiness;

	@Mock
	private AgendaService agendaServiceMock;

	@Mock
	private AgendaRepository agendaRepositoryMock;

	private AgendaModel agendaModel;
	
	private AgendaDTO agendaDTO;

    private List<AgendaModel> listAgendaModel = new ArrayList<>();
	
	@BeforeEach
	public void setup() {
		initMocks(this);
		
		agendaBusiness = new AgendaBusiness();
		agendaModel = new AgendaModel();
		//agendaServiceMock = new TransactionHistoryService();

		agendaServiceMock.setAgendaRepository(agendaRepositoryMock);
		agendaBusiness.setAgendaService(agendaServiceMock);
		

		agendaModel.setDataHora("20200101T23:59:59");
		agendaModel.setComunicacaoTipo(ComunicacaoTipoEnum.EMAIL.getDesc());
		agendaModel.setDestinatario("a@a.com");
		agendaModel.setId(1l);
		agendaModel.setMensagem("abcdefghijklmnopqrstyxuz1234567890");
		agendaModel.setStatusEnvio(StatusEnvioEnum.RECEBIDO.getDesc());
		
		listAgendaModel.add(agendaModel);
		
		agendaDTO = new AgendaDTO();
		agendaDTO.setStartDate("20191124");
		agendaDTO.setEndDate("20191125");
		agendaDTO.setNumPage("2");
		agendaDTO.setNumRecord("20");

	}

	@Test
	void verifyEndDateIsEmpty() {
		agendaDTO.setEndDate("");

		AgendaApiResponse apiResponse = agendaBusiness.fieldValidationFind(agendaDTO);
		assertEquals("Required field not found", apiResponse.getMessageDetail());
	}	
 
	@Test
	void numRecordsIsNull() {
		agendaDTO.setNumRecord(null);

		AgendaApiResponse apiResponse = agendaBusiness.fieldValidationFind(agendaDTO);
		assertEquals("Required field not found", apiResponse.getMessageDetail());
	}
    
    @Test
    void verifyStartDateANDEndDate() {
        agendaDTO.setStartDate("");

        AgendaApiResponse apiResponse = agendaBusiness.fieldValidationFind(agendaDTO);
        assertEquals("Required field not found", apiResponse.getMessageDetail());
   
        agendaDTO.setStartDate("2021/01/01");

        apiResponse = agendaBusiness.fieldValidationFind(agendaDTO);
        assertEquals("Date format is invalid, expected yyyyMMdd", apiResponse.getMessageDetail());
  
        agendaDTO.setStartDate("2019-11-28");

        apiResponse = agendaBusiness.fieldValidationFind(agendaDTO);
        assertEquals("Date format is invalid, expected yyyyMMdd", apiResponse.getMessageDetail());       

        agendaDTO.setEndDate("2019-11-29");

        apiResponse = agendaBusiness.fieldValidationFind(agendaDTO);
        assertEquals("Date format is invalid, expected yyyyMMdd", apiResponse.getMessageDetail());     
    }
    
	@Test
	void numRecordValueTests() {
		agendaDTO.setNumRecord("-1");

		AgendaApiResponse apiResponse = agendaBusiness.fieldValidationFind(agendaDTO);
		assertEquals("NumRecord format is invalid", apiResponse.getMessageDetail());

		agendaDTO.setNumRecord("0");

	    apiResponse = agendaBusiness.fieldValidationFind(agendaDTO);
		assertEquals("NumRecord format is invalid", apiResponse.getMessageDetail());

		agendaDTO.setNumRecord("101");

		apiResponse = agendaBusiness.fieldValidationFind(agendaDTO);
		assertEquals("NumRecord format is invalid", apiResponse.getMessageDetail());

		agendaDTO.setNumRecord("a");

		apiResponse = agendaBusiness.fieldValidationFind(agendaDTO);
		assertEquals("NumRecord format is invalid", apiResponse.getMessageDetail());
	}

	@Test
	void NumPageNotNullButNotNumber() {
		agendaDTO.setNumPage("abc");

		AgendaApiResponse apiResponse = agendaBusiness.fieldValidationFind(agendaDTO);
		assertEquals("NumPage format is invalid", apiResponse.getMessageDetail());

	}

	@Test
	void NumPageValid() {
		agendaDTO.setNumPage("1234");

		AgendaApiResponse apiResponse = agendaBusiness.fieldValidationFind(agendaDTO);
		assertNull(apiResponse);
	}

	@Test
	void NumPageBlank() {
		agendaDTO.setNumPage("");

		AgendaApiResponse apiResponse = agendaBusiness.fieldValidationFind(agendaDTO);
		assertNull(apiResponse);
	}

	@Test
    void fieldValidationFindReturnNull(){
	    AgendaApiResponse apiResponse = agendaBusiness.fieldValidationFind(agendaDTO);
	    assertEquals(null, apiResponse);
	}
	
	@Test
	void testPrivateMethodprocessaBuscaPorData() throws Exception {
	    
	    AgendaDTO agendadto = validAgendaPayload().get(0);
	    agendadto.setNumPage("2");
	    agendadto.setNumRecord("20");
	    
	    when(agendaServiceMock.getAgendaByDataHora(Mockito.any(), Mockito.any())).thenReturn(listAgendaModel);
	    AgendaApiResponse apiResponse = agendaBusiness.processaBuscaPorData(agendadto);
	    
	    System.out.println(apiResponse.toString());
        assertEquals("EMAIL", apiResponse.getAgendas().get(0).getComunicacaoTipo());
        assertEquals("20200101T23:59:59", apiResponse.getAgendas().get(0).getDataHora());
        assertEquals("a@a.com", apiResponse.getAgendas().get(0).getDestinatario());
        assertEquals("abcdefghijklmnopqrstyxuz1234567890", apiResponse.getAgendas().get(0).getMensagem());
	
        assertEquals("Search agenda was successfully", apiResponse.getMessageDetail());
	
	}

    private List<AgendaDTO> validAgendaPayload() {

        List<AgendaDTO> transactions = new ArrayList<>();
        transactions.add(new AgendaDTO("20200101T23:59:59", 
                                         "a@a.com", 
                                         "descriptionPayload",
                                         ComunicacaoTipoEnum.EMAIL.getDesc()));
                                       
        return transactions;
    }
    
}