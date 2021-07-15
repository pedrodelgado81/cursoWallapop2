package um.tiendas.wallapop2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
@Table(name = "PRODUCTO", schema = "WALLAPOP2")
@NamedQueries({ @NamedQuery(name = "Producto.obtenerProductoTodos", query = "select producto from Producto producto"),
		@NamedQuery(name = "Producto.obtenerProductosVenta", query = "select producto from Producto producto where producto.usuario != :usuario and producto.compra is null") })
@SequenceGenerator(schema = "WALLAPOP2", sequenceName = "producto_seq", name = "generador_producto_seq", initialValue = 0, allocationSize = 1)

public class Producto implements java.io.Serializable{
    
    @Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(generator = "generador_producto_seq", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_COMPRA")
	@JsonManagedReference
    private Compra compra;
	
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_VENDEDOR", nullable = false)
	@JsonManagedReference
	private Usuario usuario;
	
    @Column(name = "NOMBRE", nullable = false, length = 100)
	private String nombre;
	
    @Column(name = "PRECIO", nullable = false, precision = 17, scale = 0)
	private double precio;
	
    @Column(name = "IMAGEN", nullable = false, length = 1000)
	private String imagen;

    @Column(name = "DESCRIPCION_CORTA", nullable = false, length = 250)
	private String descripcionCorta;
	
    @Column(name = "DESCRIPCION_LARGA", length = 4000)
	private String descripcionLarga;
	

    public Producto() {
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
		if (!(obj instanceof Producto)) {
			return false;
		}
		Producto other = (Producto) obj;
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
