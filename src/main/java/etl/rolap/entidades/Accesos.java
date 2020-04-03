package etl.rolap.entidades;
import javax.persistence.*;

@Entity
@Table(name = "accesos")
public class Accesos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ida;
	@ManyToOne
	@JoinColumn(name="idr")
	private Recursos tiporecurso;
	@ManyToOne
	@JoinColumn(name="idt")
	private Tiempo tiempo;
	
	public Accesos() {
	}

	public Accesos(Recursos tiporecurso, Tiempo tiempo) {
		this.tiporecurso = tiporecurso;
		this.tiempo = tiempo;
	}

	public int getIda() {
		return ida;
	}

	public void setIda(int ida) {
		this.ida = ida;
	}

	public Recursos gettiporecurso() {
		return tiporecurso;
	}

	public void settiporecurso(Recursos tiporecurso) {
		this.tiporecurso = tiporecurso;
	}

	public Tiempo getTiempo() {
		return tiempo;
	}

	public void setTiempo(Tiempo tiempo) {
		this.tiempo = tiempo;
	}

	@Override
	public String toString() {
		return "Accesos [ida=" + ida + ", tiporecurso=" + tiporecurso + ", tiempo=" + tiempo + "]";
	}
	
}
