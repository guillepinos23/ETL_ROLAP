package etl.rolap.repositorios;

import etl.rolap.entidades.DimHospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<DimHospital,Integer> {


}
