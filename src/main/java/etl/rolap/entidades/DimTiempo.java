package etl.rolap.entidades;

import javax.persistence.*;
import java.util.Date;
@Entity
public class DimTiempo {

    @Id
    private Long id;
    @Column
    private String fecha;
    @Column
    private int dia;
    @Column
    private int mes;
    @Column
    private int anno;
    @Column
    private String cuatrim;
    @Column
    private String diaSemana;
    @Column
    private boolean esFinde;

    public DimTiempo(Long id, String fecha, int dia, int mes, int anno, String cuatrim, String diaSemana, boolean esFinde) {
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
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
