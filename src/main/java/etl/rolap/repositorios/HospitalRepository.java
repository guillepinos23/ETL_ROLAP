package etl.rolap.repositorios;

import etl.rolap.entidades.DimHospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<DimHospital,Long> {
    DimHospital save(DimHospital h);
    DimHospital findById(long id);

}
