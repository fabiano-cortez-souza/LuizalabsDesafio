package br.com.luzialabs.desafio.agenda.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import br.com.luzialabs.desafio.agenda.business.AgendaBusiness;
import br.com.luzialabs.desafio.agenda.dto.AgendaDTO;
import br.com.luzialabs.desafio.agenda.enums.ComunicacaoTipoEnum;
import br.com.luzialabs.desafio.agenda.repository.AgendaRepository;
import br.com.luzialabs.desafio.agenda.service.AgendaService;

@WebMvcTest(controllers = AgendaController.class)
@ActiveProfiles("test")
class AgendaControllerURLTest {
	
    @Autowired                           
    private MockMvc mockMvc;
	
    @MockBean
    AgendaController agendaControllerMock;

	@Mock
	private AgendaService agendaServiceMock;

	@Mock
	public AgendaBusiness agendaBusinessMock;

	@MockBean
	private AgendaRepository agendaRepositoryMock;

	private StringBuilder sbGetPathVariable = new StringBuilder();
	private StringBuilder sbGetRequestParam = new StringBuilder();
	private StringBuilder sbPostAgendaSave = new StringBuilder();

	private final String basePathSave    = "/api/v1/agenda";
	private final String basePathSaveNok = "/api/v1/agend";
	private final String basePathGet     = "/api/v1/agendaConsulta/";
	private final String basePathGetNok  = "/api/v1/agendaConsult/";
	private final String dataHora = "20191128T14:00:17-0301";
	private final String destinatarioOk = "a@a.com";
	private final String destinatarioNok = "a@.a.com";
	private final String mensagem = "abcdefghijklmnopqrstuvxyz1234567890";
	private final String startDate = "20191128";
	private final String endDate = "20191129";
	private final String numRecord = "2";
	private final String numPage = "10";
	
	@BeforeEach
	public void setup() {
		initMocks(this);
		agendaServiceMock.setAgendaRepository(agendaRepositoryMock);
		agendaBusinessMock.setAgendaService(agendaServiceMock);
	}

	@Test
	void contexLoadsNotNull() throws Exception {
		agendaControllerMock = new AgendaController();
		assertThat(agendaControllerMock).isNotNull();
	}

	@Test
	void shouldGetPathVariableURLSucess() throws Exception {
		sbGetPathVariable.append(basePathGet);
		sbGetPathVariable.append(startDate).append("/");
		sbGetPathVariable.append(endDate).append("/");
		sbGetPathVariable.append(numRecord);

		this.mockMvc.perform(get(sbGetPathVariable.toString())).andDo(print())
		                                                       .andExpect(status().isOk());
	}
	
	@Test
	void shouldPostURLSucess() throws Exception {
		sbPostAgendaSave.append(basePathSave);

		AgendaDTO agendaDTO = new AgendaDTO();
		agendaDTO.setComunicacaoTipo(ComunicacaoTipoEnum.PUSH.getDesc()); 
		agendaDTO.setDataHora(dataHora);		
		agendaDTO.setDestinatario(destinatarioOk);
		agendaDTO.setMensagem(mensagem);
        
		this.mockMvc.perform(post(sbPostAgendaSave.toString())
				  .content(agendaDTO.toString()).contentType("application/json;charset=UTF-8"))
				  .andDo(print())
				  .andExpect(status().isOk());
	}

	@Test
	void shouldGetPathVariableURLNotSucess() throws Exception {
		sbGetPathVariable.append(basePathGetNok);

		sbGetPathVariable.append(startDate).append("/");
		sbGetPathVariable.append(endDate).append("/");
		sbGetPathVariable.append(numRecord);

		this.mockMvc.perform(get(sbGetPathVariable.toString())).andDo(print())
		                                                       .andExpect(status().is4xxClientError());
	}
	
	@Test
	void shouldGetRequestParamURLNotSucess() throws Exception {
		sbGetRequestParam.append(basePathGet).append("?");

		sbGetRequestParam.append("numPage=").append(numPage).append("&");
		sbGetRequestParam.append("numRecord=").append(numRecord).append("&");
		sbGetRequestParam.append("startDate=").append(startDate);

		this.mockMvc.perform(get(sbGetRequestParam.toString())).andDo(print())
		                                                       .andExpect(status().is4xxClientError());
	}
	
	@Test
	void shouldPostURLNotSucess() throws Exception {
		sbPostAgendaSave.append(basePathSaveNok);

		AgendaDTO agendaDTO = new AgendaDTO();
		agendaDTO.setComunicacaoTipo(ComunicacaoTipoEnum.WHATSAPP.getDesc());
		agendaDTO.setDestinatario("a@a.com");
		agendaDTO.setDataHora("20191128T14:00:17-0301");
		agendaDTO.setMensagem("AasDAsSaAaaSaQqLAaa0");

		this.mockMvc.perform(post(sbPostAgendaSave.toString())
				  .content(agendaDTO.toString()).contentType("application/json;charset=UTF-8"))
				  .andDo(print())
				  .andExpect(status().is4xxClientError());
	}
	
}