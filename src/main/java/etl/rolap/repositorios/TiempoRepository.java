package etl.rolap.repositorios;

import etl.rolap.entidades.DimTiempo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TiempoRepository extends JpaRepository<DimTiempo,Long> {
    DimTiempo findByFecha(String fecha);

}