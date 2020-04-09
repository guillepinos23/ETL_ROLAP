package etl.rolap.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
@Entity
public class DimTiempo {

    @Id
    private Long id;
    private Date fecha;
    private String dia;
    private String mes;
    private String anno;
    private String cuatrim;
    private String diaSemana;
    private boolean esFinde;

    public DimTiempo(Long id, Date fecha, String dia, String mes, String anno, String cuatrim, String diaSemana, boolean esFinde) {
        this.id = id;
        this.fecha = fecha;
        this.dia = dia;
        this.mes = mes;
        this.anno = anno;
        this.cuatrim = cuatrim;
        this.diaSemana = diaSemana;
        this.esFinde = esFinde;
    }

    public DimTiempo(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public String getCuatrim() {
        return cuatrim;
    }

    public void setCuatrim(String cuatrim) {
        this.cuatrim = cuatrim;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public boolean isEsFinde() {
        return esFinde;
    }

    public void setEsFinde(boolean esFinde) {
        this.esFinde = esFinde;
    }

    @Override
    public String toString() {
        return "dimTiempo{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", dia='" + dia + '\'' +
                ", mes='" + mes + '\'' +
                ", anno='" + anno + '\'' +
                ", cuatrim='" + cuatrim + '\'' +
                ", diaSemana='" + diaSemana + '\'' +
                ", esFinde=" + esFinde +
                '}';
    }
}
