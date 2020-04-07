package etl.rolap.repositorios;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RecursosRepository extends CrudRepository<Recursos,Integer>{

	Recursos findByUrl(String url);
}
