package um.tiendas.wallapop2.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
@Table(name = "COMPRA", schema = "WALLAPOP2")
@SequenceGenerator(schema = "WALLAPOP2",sequenceName = "compra_seq",name = "generador_compra_seq",initialValue = 0 ,allocationSize = 1)
public class Compra implements java.io.Serializable{

    @Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(generator = "generador_compra_seq", strategy = GenerationType.SEQUENCE)
    private Integer id;
	
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_COMPRADOR", nullable = false)
	@JsonManagedReference
    private Usuario usuario;
	
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_COMPRA", nullable = false, length = 26)
    private Date fechaCompra;
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "compra")
	@JsonBackReference
	private List<Producto> productos = new ArrayList<Producto>();

    @Transient
	public double getImporteTotal() {
		double importe=0;
		for(Producto producto: productos) {
			importe+=producto.getPrecio();
		}
		return importe;
	}

    public Compra() {
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Compra)) {
			return false;
		}
		Compra other = (Compra) obj;
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		return true;
	}
}
