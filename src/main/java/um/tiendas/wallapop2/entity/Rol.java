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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "ROLES", schema = "WALLAPOP2")
@Data
public class Rol implements java.io.Serializable{
    
    @Id
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
    
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rol")
    @JsonBackReference
   	private List<Usuario> usuario = new ArrayList<Usuario>();    

    public Rol(){

    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Rol)) {
			return false;
		}
		Rol other = (Rol) obj;
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
