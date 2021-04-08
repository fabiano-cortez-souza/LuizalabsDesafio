package br.com.luzialabs.desafio.agenda.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.luzialabs.desafio.agenda.business.AgendaBusiness;
import br.com.luzialabs.desafio.agenda.dto.AgendaDTO;
import br.com.luzialabs.desafio.agenda.enums.StatusEnvioEnum;
import br.com.luzialabs.desafio.agenda.http.AgendaApiResponse;
import br.com.luzialabs.desafio.agenda.model.AgendaModel;
import br.com.luzialabs.desafio.agenda.utils.JsonUtils;

@RestController
public class AgendaController{

    private static final Logger LOGGER = LoggerFactory.getLogger(AgendaController.class);
    
	@Autowired
	AgendaBusiness agendaBusiness;
	
	@PostMapping(value = "/api/v1/agenda", produces = { "application/json;charset=utf-8" })
	public ResponseEntity<Object> agendaSave(@RequestBody AgendaModel agendaModel, 
	                                         HttpServletRequest request, 
	                                         HttpServletResponse response) {

	    LOGGER.info("Entrando no agendaSave = {} ", agendaModel.getId());
	    
	    LOGGER.info("json: " + JsonUtils.parseToJsonString(agendaModel));
	    LOGGER.info("Validado email: " + agendaModel.getDestinatario().replaceAll("(?<=.{3}).(?=.*@)", "*"));
	       
	    
	    agendaModel.setStatusEnvio(StatusEnvioEnum.RECEBIDO.getDesc());
	    
	    AgendaApiResponse apiResponse = agendaBusiness.saveAgenda(agendaModel);    
        
	    LOGGER.info("apiResponse agendaSave = {} ", apiResponse.getMessage());
        
	    if (apiResponse.getCode() != 0) {
	        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
        }
	    
	    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
	
	@GetMapping(value = "/api/v1/agendaConsulta/{startDate}/{endDate}/{numRecord}", produces = { "application/json; charset=utf-8"})
    public ResponseEntity<Object> agendaFind(@PathVariable(name = "startDate") String startDate,
                                             @PathVariable(name = "endDate") String endDate,
                                             @PathVariable(name = "numRecord") String numRecord,
                                             @RequestParam(value = "numPage", required = false) String numPage,
                                             HttpServletRequest request, 
                                             HttpServletResponse response) {
        
	    LOGGER.info("Entrando no agendaFind = {} ", startDate + " - " + endDate);
	    
	    AgendaDTO agendaDTO = new AgendaDTO();
	    
	    agendaDTO.setStartDate(startDate);
	    agendaDTO.setEndDate(endDate);
	    agendaDTO.setNumPage(numPage);
	    agendaDTO.setNumRecord(numRecord);
      
        AgendaApiResponse apiResponse = agendaBusiness.findAgendaDocuments(agendaDTO);
        
        LOGGER.info("apiResponse agendaFind = {} ", apiResponse.getMessage());
        
        if (apiResponse.getCode() != 0) {
            return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
        }
        
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        
    }
	
	@GetMapping(value = "/api/v1/agendaRemove", produces = { "application/json; charset=utf-8"})
    public ResponseEntity<Object> transactionHistoryFindParametro(@RequestParam(value = "msisdn", required = true) String msisdn, 
                                                                  @RequestParam(value = "startDate", required = true) String startDate, 
                                                                  @RequestParam(value = "endDate", required = true) String endDate, 
                                                                  @RequestParam(value = "numRecord", required = true) String numRecord,
                                                                  @RequestParam(value = "numPage", required = false) String numPage,
                                                                  HttpServletRequest request, 
                                                                  HttpServletResponse response) {
	    
	    AgendaDTO agendaDTO = new AgendaDTO();
        agendaDTO.setStartDate(startDate);
        agendaDTO.setEndDate(endDate);
        agendaDTO.setNumPage(numPage);
        agendaDTO.setNumRecord(numRecord);
      
        AgendaApiResponse apiResponse = agendaBusiness.findAgendaDocuments(agendaDTO);
        
        LOGGER.info("apiResponse = {} ", apiResponse.getMessage());
        
        if (apiResponse.getCode() != 0) {
            return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
        }
        
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
