package etl.rolap.repositorios;

import etl.rolap.entidades.DimTiempo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiempoRepository extends JpaRepository<DimTiempo,Integer> {

}