package etl.rolap.repositorios;

import etl.rolap.entidades.DimCompuesto;
import etl.rolap.entidades.DimHospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface TablaCompuesto extends JpaRepository<DimCompuesto,Long> {
     ArrayList<DimCompuesto> findAll();

}
