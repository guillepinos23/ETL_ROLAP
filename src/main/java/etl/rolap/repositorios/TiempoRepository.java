package etl.rolap.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import etl.rolap.entidades.Tiempo;

@RepositoryRestResource
public interface TiempoRepository extends CrudRepository<Tiempo,Integer>{

	List<Tiempo> findByDia(int dia);
	List<Tiempo> findByMes(String mes);
	List<Tiempo> findByAnio(int anio);
	List<Tiempo> findByHora(int hora);
	Tiempo findByAnioAndDiaAndMesAndHora(int a, int d, String m ,int h);
	
}
