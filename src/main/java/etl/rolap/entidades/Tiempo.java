package etl.rolap.entidades;
import javax.persistence.*;

@Entity
@Table(name = "tiempo")
public class Tiempo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idt;
	@Column
	private int dia;
	@Column
	private String mes;
	@Column
	private int anio;
	@Column
	private int hora;
	
	public Tiempo() {
	}
	public Tiempo(int dia, String mes, int anio, int hora) {
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
		this.hora = hora;
	}

	public long getIdt() {
		return idt;
	}

	public void setIdt(long idt) {
		this.idt = idt;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		return dia + "/" + mes + "-"+ hora+":00";
	}
	
	
}
