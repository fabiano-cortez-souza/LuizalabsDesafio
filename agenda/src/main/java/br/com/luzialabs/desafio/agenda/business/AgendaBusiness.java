package br.com.luzialabs.desafio.agenda.business;

import java.net.HttpURLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import br.com.luzialabs.desafio.agenda.constants.Constants;
import br.com.luzialabs.desafio.agenda.dto.AgendaDTO;
import br.com.luzialabs.desafio.agenda.enums.ComunicacaoTipoEnum;
import br.com.luzialabs.desafio.agenda.enums.ErrorType;
import br.com.luzialabs.desafio.agenda.enums.RemocaoTipoEnum;
import br.com.luzialabs.desafio.agenda.enums.StatusEnvioEnum;
import br.com.luzialabs.desafio.agenda.enums.SuccessMessage;
import br.com.luzialabs.desafio.agenda.enums.TimeLimitEnum;
import br.com.luzialabs.desafio.agenda.http.AgendaApiResponse;
import br.com.luzialabs.desafio.agenda.model.AgendaModel;
import br.com.luzialabs.desafio.agenda.service.AgendaService;
import br.com.luzialabs.desafio.agenda.utils.DateUtils;
import br.com.luzialabs.desafio.agenda.utils.JsonUtils;
import br.com.luzialabs.desafio.agenda.utils.Utils;
import br.com.luzialabs.desafio.agenda.vo.AgendaVO;

@Service
public class AgendaBusiness {
 
    @Value("${endpoint.send.mail}")
    private String endpointsendmail;
    
    @Value("${endpoint.send.sms}")
    private String endpointsendsms;
    
    @Value("${endpoint.send.push}")
    private String endpointsendpush;
    
    @Value("${endpoint.send.whatsapp}")
    private String endpointsendwhatsapp;

	@Autowired
	AgendaService agendaService;

	public List<AgendaModel> agendaModel;
	
	private String httpAddress = "";
	private boolean jUnitTest = false;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AgendaBusiness.class);
	
	public AgendaApiResponse saveAgenda(AgendaModel agendaModel) {

        AgendaApiResponse api = fieldValidationSave(agendaModel);

        String httpResponseBody = "";

        if (api == null) {
            agendaService.save(agendaModel);

            /*
             * Envio da mensagem pelo endpoint identiticado 
             */
            try {
                httpResponseBody = agendaService.envioPostRequest(agendaModel.toString(), 
                                                                  httpAddress,
                                                                  MediaType.APPLICATION_JSON_VALUE);

                if (agendaService.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    api = new AgendaApiResponse(SuccessMessage.AGENDA_SUCCESS);
                } else {
                    api = JsonUtils.getGson().fromJson(httpResponseBody, AgendaApiResponse.class);
                }
            } catch (Exception exception) {
                LOGGER.info("[saveAgenda] START_Exception_FAILED" + 
                            " HTTPRESPONSEBODY = {}" + 
                            " APIRESPONSE = {}"      + 
                            " EXCEPTION = {}"        + 
                            " END_Exception_FAILED",
                            httpResponseBody, 
                            api, 
                            exception.getMessage());

                api = new AgendaApiResponse(ErrorType.HTTP_RESPONSE_SEND_DENIED);
            }

        }
        return api;
    }

	public AgendaApiResponse fieldValidationSave(AgendaModel agendaModel) {
			
	    ComunicacaoTipoEnum comunicacaoTipoEnum = ComunicacaoTipoEnum.valueOf(agendaModel.getComunicacaoTipo());
	     
        switch (comunicacaoTipoEnum) {
        case EMAIL:
            httpAddress = endpointsendmail;
            break;
        case PUSH:
            httpAddress = endpointsendpush;
            break;
        case SMS:
            httpAddress = endpointsendsms;
            break;
        case WHATSAPP:
            httpAddress = endpointsendwhatsapp;
            break;
        default:
            return new AgendaApiResponse(ErrorType.FIELD_VALIDATION_COMMUNICATION_TYPE_INVALID);
        }

		if( StringUtils.isBlank(agendaModel.getDataHora()) || 
		    StringUtils.isBlank(agendaModel.getDestinatario()) ||
			StringUtils.isBlank(agendaModel.getMensagem())) {
			
		    return new AgendaApiResponse(ErrorType.FIELD_VALIDATION_REQUIRED_FIELD_NOT_FOUND);
		}
		
		if(Utils.invalidTimestampFormat(agendaModel.getDataHora())) {	    
		    return new AgendaApiResponse(ErrorType.FIELD_VALIDATION_TIMESTAMP_INVALID);
		}
		
		if(! StatusEnvioEnum.contains(agendaModel.getStatusEnvio())) {
		    return new AgendaApiResponse(ErrorType.FIELD_VALIDATION_STATUS_TYPE_INVALID);
		}
		
		if(Utils.invalidTimestampFormat(agendaModel.getDataHora())) {    
            return new AgendaApiResponse(ErrorType.FIELD_VALIDATION_TIMESTAMP_INVALID);
        }
		
        if(agendaModel.getMensagem().length() > Constants.MAX_LENGTH_MESSAGE) {
            return new AgendaApiResponse(ErrorType.FIELD_VALIDATION_DESCRIPTION_INVALID);
        }	
        
		return null;
	}
	
    public AgendaApiResponse deleteAgendaDocument(AgendaDTO agendaDTO, int tipoRemocao) {
        AgendaApiResponse apiResponse = null;
        AgendaModel agenda = new AgendaModel();
        
        if (tipoRemocao == 1 || tipoRemocao == 2) {
            agendaDTO.setRemocaoTipoEnum(RemocaoTipoEnum.getFromCode(tipoRemocao));

            try {
                agendaDTO = parseDateToTimestamp(agendaDTO);

                List<AgendaModel> listagenda = agendaService.findAgendaById(agendaDTO.getRequestId());

                List<AgendaModel> listagendas = agendaService.getAgendaByDataHora(agendaDTO.getStartDate(),
                        agendaDTO.getEndDate());

                if (agendaDTO.getRemocaoTipoEnum().equals(RemocaoTipoEnum.BY_ID)) {
                    if (listagenda.size() > 0) {
                        agenda = listagenda.get(0);
                        if (agendaService.delete(agenda)) {
                            apiResponse = new AgendaApiResponse(SuccessMessage.AGENDA_DELETE, agenda);
                        }
                    } else {
                        apiResponse = new AgendaApiResponse(ErrorType.DOCUMENT_NOT_EXISTS);
                    }
                } else if (agendaDTO.getRemocaoTipoEnum().equals(RemocaoTipoEnum.BY_DATE)) {
                    if (listagendas.size() > 0) {
                        
                        listagendas.forEach(agenda1 -> { agendaService.delete(agenda1); });
                        
                        apiResponse = new AgendaApiResponse(SuccessMessage.AGENDA_DELETE);
                        apiResponse.setListagendas(listagendas);       
                        
                    } else {
                        apiResponse = new AgendaApiResponse(ErrorType.DOCUMENT_NOT_EXISTS);
                    }
                }
            } catch (ParseException e) {
                apiResponse = new AgendaApiResponse(ErrorType.FIELD_VALIDATION_DATE_INVALID);
            }
        } else {
            apiResponse = new AgendaApiResponse(ErrorType.FIELD_VALIDATION_TYPE_DELETE_INVALID);
        }
        return apiResponse;
    }
    
    public AgendaApiResponse findAgendaDocuments(AgendaDTO agendaDTO) {

        agendaModel = new ArrayList<AgendaModel>();
        AgendaApiResponse apiResponse = fieldValidationFind(agendaDTO);
        List<AgendaVO> listAgendaVO = new ArrayList<AgendaVO>();

        try {
            agendaDTO = parseDateToTimestamp(agendaDTO);
            int numPage = agendaDTO.getNumPage() != null && !agendaDTO.getNumPage().equals("")
                    ? Integer.parseInt(agendaDTO.getNumPage())
                    : 0;
            int numRecord = Integer.parseInt(agendaDTO.getNumRecord());

            if (apiResponse == null) {

                if (!jUnitTest) {
                    agendaModel = agendaService.getAgendaByDataHora(agendaDTO.getStartDate(), agendaDTO.getEndDate());
                }

                long totalOfDocuments = agendaModel.size();
                int totalPages = Utils.calculateTotalOfPages(totalOfDocuments, numRecord);

                if (totalPages == 0) {
                    totalPages = 1;
                }

                if (totalPages <= numPage) {
                    if (!agendaModel.isEmpty()) {
                        for (AgendaModel agendaModel : agendaModel) {

                            AgendaVO agendaVO = new AgendaVO();
                            agendaVO.setId(agendaModel.getId());
                            agendaVO.setDataHora(agendaModel.getDataHora());
                            agendaVO.setDestinatario(agendaModel.getDestinatario());
                            agendaVO.setMensagem(agendaModel.getMensagem());
                            agendaVO.setComunicacaoTipo(agendaModel.getComunicacaoTipo());
                            agendaVO.setStatusEnvio(agendaModel.getStatusEnvio());

                            listAgendaVO.add(agendaVO);
                        }
                    }
                } else {
                    apiResponse = new AgendaApiResponse(ErrorType.DOCUMENT_COUNT_LIMIT_OVERFLOW);
                }

                apiResponse = new AgendaApiResponse(SuccessMessage.AGENDA_SEARCH_SUCCESS, listAgendaVO);
            }
        } catch (ParseException exception) {
            LOGGER.info("[findAgendaDocuments] Parse error = " + 
                    " AgendaDTO = {}" + 
                    " EXCEPTION = {}",
                    agendaDTO.toString(), 
                    exception.getMessage());
            apiResponse = new AgendaApiResponse(ErrorType.FIELD_VALIDATION_DATE_INVALID);
        }
        return apiResponse;
    }
    
    public AgendaApiResponse fieldValidationFind(AgendaDTO agendaDTO) {
        
        if(StringUtils.isBlank(agendaDTO.getStartDate()) || 
           StringUtils.isBlank(agendaDTO.getEndDate()) ||
           StringUtils.isBlank(agendaDTO.getNumRecord())) {
            
            return new AgendaApiResponse (ErrorType.FIELD_VALIDATION_REQUIRED_FIELD_NOT_FOUND);
        }
        
        if(Utils.dateValidation(agendaDTO.getStartDate()) ||
           Utils.dateValidation(agendaDTO.getEndDate())) {
            
            return new AgendaApiResponse(ErrorType.FIELD_VALIDATION_DATE_INVALID);
        }
        
                
        if(!Utils.hasOnlyNumbers(agendaDTO.getNumRecord()) ||
           Integer.parseInt(agendaDTO.getNumRecord()) < 1 || 
           Integer.parseInt(agendaDTO.getNumRecord()) > 100) {
            
            return new AgendaApiResponse(ErrorType.FIELD_VALIDATION_NUMRECORDS_INVALID);
        }
        
        if(!StringUtils.isBlank(agendaDTO.getNumPage()) && 
           !Utils.hasOnlyNumbers(agendaDTO.getNumPage())) {
           
            return new AgendaApiResponse(ErrorType.FIELD_VALIDATION_NUMPAGE_INVALID);
        }
        
        return null;
    }
    
    private AgendaDTO parseDateToTimestamp(AgendaDTO agendaDTO) throws ParseException{
        agendaDTO.setStartDate(
                DateUtils.parseDateToTimestamp(agendaDTO.getStartDate(), TimeLimitEnum.STARTTIME).toString());

        agendaDTO.setEndDate(
                DateUtils.parseDateToTimestamp(agendaDTO.getEndDate(), TimeLimitEnum.ENDTIME).toString());

        return agendaDTO;
    }
    
    public AgendaService getTransactionHistoryService() {
        return agendaService;
    }
  
	public void setTransactionHistoryService(AgendaService agendaService) {
		this.agendaService = agendaService;
	}

    public List<AgendaModel> getAgendaModels() {
        return agendaModel;
    }

    public void setTransactionHistoryModels(List<AgendaModel> agendaModel) {
        this.agendaModel = agendaModel;
    }

    public boolean isjUnitTest() {
        return jUnitTest;
    }

    public void setjUnitTest(boolean jUnitTest) {
        this.jUnitTest = jUnitTest;
    }
}
