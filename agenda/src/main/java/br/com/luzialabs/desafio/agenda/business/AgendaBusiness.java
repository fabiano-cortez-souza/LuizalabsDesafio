package br.com.luzialabs.desafio.agenda.business;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luzialabs.desafio.agenda.constants.Constants;
import br.com.luzialabs.desafio.agenda.dto.AgendaDTO;
import br.com.luzialabs.desafio.agenda.enums.ComunicacaoTipoEnum;
import br.com.luzialabs.desafio.agenda.enums.ErrorType;
import br.com.luzialabs.desafio.agenda.enums.StatusEnvioEnum;
import br.com.luzialabs.desafio.agenda.enums.SuccessMessage;
import br.com.luzialabs.desafio.agenda.exceptions.AgendaApiResponse;
import br.com.luzialabs.desafio.agenda.model.AgendaModel;
import br.com.luzialabs.desafio.agenda.service.AgendaService;
import br.com.luzialabs.desafio.agenda.utils.DateUtils;
import br.com.luzialabs.desafio.agenda.utils.Utils;
import br.com.luzialabs.desafio.agenda.vo.AgendaVO;

@Service
public class AgendaBusiness {
	
	@Autowired
	AgendaService agendaService;
	
	public List<AgendaModel> agendaModel;
	
	private boolean jUnitTest = false;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AgendaBusiness.class);
	
	public AgendaApiResponse saveAgenda(AgendaModel agendaModel) {

	    AgendaApiResponse api = fieldValidationSave(agendaModel);

        if (api == null) {
            agendaService.save(agendaModel);
            api = new AgendaApiResponse(SuccessMessage.TRANSACTION_SUCCESS);
        }
        return api;
	}

	public AgendaApiResponse fieldValidationSave(AgendaModel agendaModel) {
			
		if( StringUtils.isBlank(agendaModel.getDataHora()) || 
		    StringUtils.isBlank(agendaModel.getDestinatario()) ||
			StringUtils.isBlank(agendaModel.getMensagem())) {
			
		    return new AgendaApiResponse(ErrorType.FIELD_VALIDATION_REQUIRED_FIELD_NOT_FOUND);
		}
		
		if(Utils.invalidTimestampFormat(agendaModel.getDataHora())) {	    
		    return new AgendaApiResponse(ErrorType.FIELD_VALIDATION_TIMESTAMP_INVALID);
		}
		
		if (! ComunicacaoTipoEnum.contains(agendaModel.getComunicacaoTipo())) {
		    return new AgendaApiResponse(ErrorType.FIELD_VALIDATION_COMMUNICATION_TYPE_INVALID);
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
        
       
        
		/*

		
		if(!Utils.hasOnlyNumbers(agendaModel.getAmount()) || 
	       Utils.fieldOutOfRange(agendaModel.getAmount())) {
	         
		    return new AgendaApiResponse(ErrorType.FIELD_VALIDATION_AMOUNT_INVALID);
		}
	      
		if(agendaModel.getType().length() > 20) {
            
            return new AgendaApiResponse(ErrorType.FIELD_VALIDATION_TYPE_INVALID);
        }
	      
		if(agendaModel.getTransactionID().length() > Constants.DEFAULT_TRANSACTION_ID_LENGTH) {
            
            return new AgendaApiResponse(ErrorType.FIELD_VALIDATION_TRANSACTIONID_INVALID);
        }      
	        
		if(StringUtils.isBlank(agendaModel.getDescription())) {
            return new AgendaApiResponse(ErrorType.FIELD_VALIDATION_DESCRIPTION_INVALID);
        }
		

		
		agendaModel.setId(agendaModel.getTransactionID());
		
		if(agendaService.eventExists(agendaModel.getTransactionID())) {
            
		    return new AgendaApiResponse(ErrorType.DOCUMENT_ALREADY_EXISTS);
        }
		/**/
		return null;
	}
	
	public AgendaApiResponse findAgendaDocuments(AgendaDTO agendaDTO) {
        
	    agendaModel = new ArrayList<AgendaModel>();
	    List<AgendaModel> listaById = new ArrayList<AgendaModel>();
	    
        AgendaApiResponse apiResponse = fieldValidationFind(agendaDTO);
        List<AgendaVO> listAgendaVO = new ArrayList<AgendaVO>();
        
        int numPage = agendaDTO.getNumPage() != null && !agendaDTO.getNumPage().equals("") ? Integer.parseInt(agendaDTO.getNumPage()) : 0;
        int numRecord = Integer.parseInt(agendaDTO.getNumRecord());
        
        if(apiResponse == null) {
        	
            if(!jUnitTest) {
                listaById = agendaService.findAgendaById(agendaDTO.getRequestId());
            }
            
            for (AgendaModel agendaModel : listaById) {
                
/*                DateTime timestampDate = DateTime.parse(agendaModel.getTimestamp());
                DateTime strDate = DateTime.parse(agendaDTO.getStartDate()+ "T" + LocalTime.of(0, 0, 0));
                DateTime endDate = DateTime.parse(agendaDTO.getEndDate()+ "T" + LocalTime.of(23, 59, 59));
                
                if (agendaModel.size() <= numRecord && 
                    ((timestampDate.isAfter(strDate) && timestampDate.isBefore(endDate)) ||
                      timestampDate.isEqual(strDate) || timestampDate.isEqual(endDate))) {
                    
                    agendaModel.add(agendaModel);
                }
    */
            }
            
            long totalOfDocuments = agendaModel.size();
            int totalPages = Utils.calculateTotalOfPages(totalOfDocuments, numRecord);
            
            if(totalPages == 0) { 
                totalPages = 1;
            }
            
            if(!agendaModel.isEmpty()) {
                for (AgendaModel agendaModel : agendaModel) {
                    
                    AgendaVO agendaVO = new AgendaVO();
/*                  agendaVO.setTimestamp(agendaModel.getTimestamp());
                    agendaVO.setType(agendaModel.getType());
                    agendaVO.setAmount(agendaModel.getAmount());
                    agendaVO.setDescription(agendaModel.getDescription());
                    agendaVO.setTransactionId(agendaModel.getTransactionID());
*/
                    listAgendaVO.add(agendaVO);
                }
            }
            
            apiResponse = new AgendaApiResponse(SuccessMessage.TRANSACTION_SEARCH_SUCCESS,
                                                            numPage,
                                                            totalPages,
                                                            totalOfDocuments,
                                                            listAgendaVO);
        }
        
        return apiResponse;
    }
    
    public AgendaApiResponse fieldValidationFind(AgendaDTO agendaDTO) {
        
/*        if(StringUtils.isBlank(agendaDTO.getStartDate()) || 
           StringUtils.isBlank(agendaDTO.getMsisdn()) ||
           StringUtils.isBlank(agendaDTO.getEndDate()) ||
           StringUtils.isBlank(agendaDTO.getNumRecord())) {
            
            return new AgendaApiResponse (ErrorType.FIELD_VALIDATION_REQUIRED_FIELD_NOT_FOUND);
        }
        
        if(invalidDateFormat(agendaDTO.getStartDate()) ||
           invalidDateFormat(agendaDTO.getEndDate())) {
            
            return new AgendaApiResponse(ErrorType.FIELD_VALIDATION_DATE_INVALID);
        }
        
        if (!Utils.hasOnlyNumbers(agendaDTO.getMsisdn()) ||
            agendaDTO.getMsisdn().length() > Constants.MSISDN_LENGTH) {
            
            return new AgendaApiResponse(ErrorType.FIELD_VALIDATION_MSISDN_INVALID);
        }
                
        if(!Utils.hasOnlyNumbers(agendaDTO.getNumRecord()) ||
           Integer.parseInt(agendaDTO.getNumRecord()) < 1 || 
           Integer.parseInt(agendaDTO.getNumRecord()) > 100) {
            
            return new AgendaApiResponse(ErrorType.FIELD_VALIDATION_NUMRECORDS_INVALID);
        }
        
        if(!StringUtils.isBlank(agendaDTO.getNumPage()) && 
           !Utils.hasOnlyNumbers(agendaDTO.getNumPage())) {
           
            return new AgendaApiResponse(ErrorType.FIELD_VALIDATION_NUMPAGE_INVALID);
        }*/
        
        
//      if(ChronoUnit.DAYS.between(DateUtils.getLocalDateOf(transactionHistorySolicitation.getStartDate()), 
//                                 DateUtils.getLocalDateOf(transactionHistorySolicitation.getEndDate())) == 0) {
//          
//          transactionHistorySolicitation.setStartDate(transactionHistorySolicitation.getStartDate() + "T" + LocalTime.of(0, 0, 0));
//          transactionHistorySolicitation.setEndDate(transactionHistorySolicitation.getEndDate() + "T" + LocalTime.of(23, 59, 59));
//      }
        
        return null;
    }

    private boolean invalidDateFormat(String startDate) {
        try {
            DateUtils.dateValidation(startDate);
            return false;
        } catch (DateTimeParseException dateTimeParseException) {
            return true;
        }
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
