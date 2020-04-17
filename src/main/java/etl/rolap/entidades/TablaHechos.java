package etl.rolap.entidades;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class TablaHechos {
    @Id
    private Long id;
    @OneToMany
    private List<DimPaciente> cliente;
    @OneToMany
    private List<DimHospital> hospital;
    @OneToMany
    private List<DimTiempo> fechaIngreso;

    private int duracion;
    private boolean uci;
    private boolean fallecido;
    private boolean tatramiento;
    public TablaHechos(){

    }
    public TablaHechos(Long id, List<DimPaciente> cliente, List<DimHospital> hospital, List<DimTiempo> fechaIngreso, int duracion, boolean uci, boolean fallecido, boolean tatramiento) {
        this.id = id;
        this.cliente = cliente;
        this.hospital = hospital;
        this.fechaIngreso = fechaIngreso;
        this.duracion = duracion;
        this.uci = uci;
        this.fallecido = fallecido;
        this.tatramiento = tatramiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DimPaciente> getCliente() {
        return cliente;
    }

    public void setCliente(List<DimPaciente> cliente) {
        this.cliente = cliente;
    }

    public List<DimHospital> getHospital() {
        return hospital;
    }

    public void setHospital(List<DimHospital> hospital) {
        this.hospital = hospital;
    }

    public List<DimTiempo> getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(List<DimTiempo> fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public boolean isUci() {
        return uci;
    }

    public void setUci(boolean uci) {
        this.uci = uci;
    }

    public boolean isFallecido() {
        return fallecido;
    }

    public void setFallecido(boolean fallecido) {
        this.fallecido = fallecido;
    }

    public boolean isTatramiento() {
        return tatramiento;
    }

    public void setTatramiento(boolean tatramiento) {
        this.tatramiento = tatramiento;
    }

    @Override
    public String toString() {
        return "TablaHechos{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", hospital=" + hospital +
                ", fechaIngreso=" + fechaIngreso +
                ", duracion=" + duracion +
                ", uci=" + uci +
                ", fallecido=" + fallecido +
                ", tatramiento=" + tatramiento +
                '}';
    }
}
