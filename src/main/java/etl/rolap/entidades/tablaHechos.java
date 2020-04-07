package etl.rolap.entidades;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Date;

public class tablaHechos {
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Id
    private Long id;
    @OneToMany
    private dimPaciente cliente;
    @OneToMany
    private dimHospital hospital;
    @OneToMany
    private dimTiempo fechaIngreso;

    private int duracion;
    private boolean uci;
    private boolean fallecido;
    private boolean tatramiento;
    public tablaHechos (){

    }
    public tablaHechos(Long id, dimPaciente cliente, dimHospital hospital, dimTiempo fechaIngreso, int duracion, boolean uci, boolean fallecido, boolean tatramiento) {
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

    public dimPaciente getCliente() {
        return cliente;
    }

    public void setCliente(dimPaciente cliente) {
        this.cliente = cliente;
    }

    public dimHospital getHospital() {
        return hospital;
    }

    public void setHospital(dimHospital hospital) {
        this.hospital = hospital;
    }

    public dimTiempo getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(dimTiempo fechaIngreso) {
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
        return "tablaHechos{" +
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
