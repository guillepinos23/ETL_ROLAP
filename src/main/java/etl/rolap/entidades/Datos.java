package etl.rolap.entidades;

import javax.persistence.*;

@Entity
@Table(name = "datos")
public class Datos {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idd;
	@Column
	private String ip;
	@Column
	private String numeros;
	@Column
	private String desde;
	@Column
	private String buscador;
	
	public Datos(){		
	}
	
	public Datos(String ip, String numeros, String desde, String buscador) {
		this.ip = ip;
		this.numeros = numeros;
		this.desde = desde;
		this.buscador = buscador;
	}

	public long getIdd() {
		return idd;
	}

	public void setIdd(long idd) {
		this.idd = idd;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNumeros() {
		return numeros;
	}

	public void setNumeros(String numeros) {
		this.numeros = numeros;
	}

	public String getDesde() {
		return desde;
	}

	public void setDesde(String desde) {
		this.desde = desde;
	}

	public String getBuscador() {
		return buscador;
	}

	public void setBuscador(String buscador) {
		this.buscador = buscador;
	}

	@Override
	public String toString() {
		return "Datos [idd=" + idd + ", ip=" + ip + ", numeros=" + numeros + ", desde=" + desde + ", buscador="
				+ buscador + "]";
	}
	
	
}
