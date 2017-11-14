package empresa.modelo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Matriz extends Empresa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="matriz", orphanRemoval = true)
	private Set<Filial> filiais;

	public Set<Filial> getFiliais() {
		return filiais;
	}

	public void setFiliais(Set<Filial> filiais) {
		this.filiais = filiais;
	}
	
	public void adicionarFilial(Filial filial){
		this.filiais.add(filial);
	}
	 
}
