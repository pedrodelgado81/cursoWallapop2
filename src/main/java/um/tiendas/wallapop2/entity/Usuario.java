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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import um.tiendas.wallapop2.model.UsuarioDTO;

@Entity
@Table(name = "USUARIO", schema = "WALLAPOP2")
@SequenceGenerator(schema = "WALLAPOP2",sequenceName = "usuario_seq",name = "generador_usuario_seq",initialValue = 2 ,allocationSize = 1)
@Data
public class Usuario implements java.io.Serializable{
    
    @Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(generator = "generador_usuario_seq",strategy = GenerationType.SEQUENCE)
	private Integer id;
    
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
	
    @Column(name = "APELLIDOS", nullable = false, length = 100)
    private String apellidos;
	
    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;
	
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;
    
    @Column(name = "AVATAR", length = 1000)
    private String avatar;
    
    @ManyToOne()
    @JoinColumn(name = "id_rol")
    private Rol rol;
    
    @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "FECHA_ALTA", nullable = false, length = 26)
    private Date fechaAlta;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	@JsonBackReference
    private List<Producto> productos = new ArrayList<Producto>();
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	@JsonBackReference
    private List<Compra> compras = new ArrayList<Compra>();

    public Usuario(){

    }
    
    public UsuarioDTO toDTO() {
    	return new UsuarioDTO(nombre, apellidos, email);
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
}
