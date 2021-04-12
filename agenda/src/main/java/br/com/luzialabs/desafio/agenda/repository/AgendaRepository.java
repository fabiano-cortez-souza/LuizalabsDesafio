package br.com.luzialabs.desafio.agenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.luzialabs.desafio.agenda.model.AgendaModel;

@Repository
public interface AgendaRepository extends JpaRepository<AgendaModel, String> {
    
	@Query(value = "SELECT COUNT(*) FROM agenda WHERE data_hora >= ?1 AND data_hora <= ?2", nativeQuery  = true)
	long countWithDataHoraRange(String strDate,
			                    String endDate);
	
	@Query(value = "SELECT * FROM agenda WHERE data_hora >= ?1 AND data_hora <= ?2 ORDER BY data_hora DESC", nativeQuery  = true)
	List<AgendaModel> getByDataHora(String strDate,
									String endDate);
	
	List<AgendaModel> findById(long id);

    boolean existsById(long id);

}
