package etl.rolap.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AccesoRepository extends CrudRepository<Accesos,Integer>{
	
	List<Accesos> findByTiporecurso_idr(long recurso);
	List<Accesos> findByTiporecurso_url(String recurso);
	List<Accesos> findByTiempo_dia(long dia);
	List<Accesos> findByTiempo_mes(String mes);
	List<Accesos> findByTiempo_anio(long anio);
	List<Accesos> findByTiempo_hora(String hora);

}
