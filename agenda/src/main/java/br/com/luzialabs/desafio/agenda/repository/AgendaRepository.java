package br.com.luzialabs.desafio.agenda.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.luzialabs.desafio.agenda.model.AgendaModel;

@Repository
public interface AgendaRepository extends CrudRepository<AgendaModel, String> {
	
    /*
	//@Query(value = "{ $and: [{ 'msisdn' : { $eq : ?0 } }, { 'timestamp' : { $gte : ?1, $lte: ?2 } } ]}", count = true)
	@Query(value = "SELECT * FROM transactionHistory WHERE msisdn = @_msisdn AND timestamp >= @_strDate AND timestamp <= @_endDate", count = true)
	long countWithTimeStampRange(@Param("_msisdn")  String msisdn,
			                     @Param("_strDate") String strDate,
			                     @Param("_endDate") String endDate);
	
	
	//@Query("{ $and: [{ 'msisdn' : { $eq : ?0 } }, { 'timestamp' : { $gte : ?1, $lte: ?2 } } ]}, sort = { 'timeStamp' : -1 }")
	@Query("SELECT * FROM transactionHistory WHERE msisdn = @_msisdn AND timestamp >= @_strDate AND timestamp <= @_endDate ORDER BY timestamp DESC")
	List<AgendaInclusaoModel> getMsisdnByTimestamp(@Param("_msisdn")  String msisdn,
											           @Param("_strDate") String strDate,
													   @Param("_endDate") String endDate, 
													   Pageable pageable);
	*/
	List<AgendaModel> findById(long id);
	
}
