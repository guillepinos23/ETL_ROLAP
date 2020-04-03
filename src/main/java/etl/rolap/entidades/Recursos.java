package etl.rolap.entidades;
import javax.persistence.*;

@Entity
@Table(name = "recursos")
public class Recursos {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idr;
	@Column
	private String url;
	
	public Recursos() {
	}
	
	public Recursos(String url) {
		this.url = url;
	}

	public long getIdr() {
		return idr;
	}

	public void setIdr(long idr) {
		this.idr = idr;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Recursos [idr=" + idr + ", url=" + url + "]";
	}
	
	
}
