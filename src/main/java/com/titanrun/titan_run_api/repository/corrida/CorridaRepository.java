package com.titanrun.titan_run_api.repository.corrida;

import com.titanrun.titan_run_api.model.corrida.Corrida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorridaRepository extends JpaRepository<Corrida, Long> {

}
