package empresa.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Central implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Empresa> empresas;
	private List<Matriz> matrizes;
	//private int numeroMatrizes;
	private List<Filial> filiais;
	//private int numeroFiliais;
	private int tamanho;
	
	public Central() {
		empresas = new ArrayList<Empresa>();
		Matriz matriz = new Matriz();
		matriz.setCNPJ("76.334.828/0001-48");
		matriz.setComplemento("Bloco 1 - Sala 203");
		matriz.setEmail("beta@gmail.com");
		matriz.setLogradouro("Rua dos Bobos");
		matriz.setMunicipio("Fortaleza");
		matriz.setNumero(0);
		matriz.setRazaoSocial("Beta Tecnologia LTDA");
		matriz.setTelefone("33001010");
		matriz.setUf("CE");
		
		empresas.add(matriz);
		incrementarNumeroEmpresas();
		
		matriz = new Matriz();
		matriz.setCNPJ("75.334.828/0001-48");
		matriz.setComplemento("Bloco A - Sala 200");
		matriz.setEmail("friends@gmail.com");
		matriz.setLogradouro("Rua Desembargador Moreira");
		matriz.setMunicipio("Fortaleza");
		matriz.setNumero(0);
		matriz.setRazaoSocial("Friends Tecnologia LTDA");
		matriz.setTelefone("33001011");
		matriz.setUf("CE");
		
		empresas.add(matriz);
		incrementarNumeroEmpresas();
	}

	public List<Matriz> getMatrizes() {
		return matrizes;
	}

	public void setMatrizes(List<Matriz> matrizes) {
		this.matrizes = matrizes;
	}

	public List<Filial> getFiliais() {
		return filiais;
	}

	public void setFiliais(List<Filial> filiais) {
		this.filiais = filiais;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}
	
	public void incrementarNumeroEmpresas(){
		//int n = this.agenda.getnContatos();
		this.tamanho++;
	}
	
	public void decrementarNumeroEmpresas(){
		this.tamanho--;
	}
	
	public int getPosicaoEmpresa(String cnpj){
		if(cnpj != null && !cnpj.isEmpty()){
			Empresa itera;
			for(int i=0;i<this.tamanho;i++ ){
				itera = empresas.get(i);
				if(itera.getCNPJ().equals(cnpj)){
					return i;
				}
			}
		}
		return -1;
	}
	
	public int getPosicaoMatriz(String cnpj){
		if(cnpj != null && !cnpj.isEmpty()){
			Empresa itera;
			for(int i=0;i<this.matrizes.size();i++ ){
				itera = matrizes.get(i);
				if(itera.getCNPJ().equals(cnpj)){
					return i;
				}
			}
		}
		return -1;
	}
	
	public Empresa getEmpresa(String cnpj){
		int pos = this.getPosicaoEmpresa(cnpj);
		if(pos != -1){
			return empresas.get(pos);
		}
		return null;
	}
	
	
	public void incluirEmpresa(Empresa empresa) {
		this.empresas.add(empresa);
		this.incrementarNumeroEmpresas();
	}
	
	public void incluirMatriz(Matriz matriz) {
		this.matrizes.add(matriz);
		//this.incrementarNumeroEmpresas();
	}
	
	public void incluirFilial(Filial filial) {
		if(filial != null){
			Matriz m = filial.getMatriz();
			int pos = getPosicaoMatriz(m.getCNPJ());
			this.matrizes.get(pos).adicionarFilial(filial);
			//this.incrementarNumeroEmpresas();
		}
	}
	
	public void deletarEmpresa(String cnpj){
		Empresa emp = this.getEmpresa(cnpj);
		if(emp!= null){
			this.empresas.remove(emp);
			this.decrementarNumeroEmpresas();
		}
	}
	
	public boolean atualizarEmpresa(Empresa empresaAntes, Empresa empModificada){
		if(empModificada != null){
			int pos = this.getPosicaoEmpresa(empresaAntes.getCNPJ());
			
			empresaAntes.setCNPJ(empModificada.getCNPJ());
			empresaAntes.setRazaoSocial(empModificada.getRazaoSocial());
			empresaAntes.setMunicipio(empModificada.getMunicipio());
			empresaAntes.setUf(empModificada.getUf());
			empresaAntes.setLogradouro(empModificada.getLogradouro());
			empresaAntes.setComplemento(empModificada.getComplemento());
			empresaAntes.setNumero(empModificada.getNumero());
					
			this.empresas.set(pos, empresaAntes);
			
			return true;
		}
		return false;
	}
	
}
