package empresa.manageds;

import empresa.DAO.EmpresaDAO;
import empresa.modelo.Central;
import empresa.modelo.Empresa;
import empresa.modelo.Matriz;

import java.util.ArrayList;
import java.util.List;

public class EmpresaManagedBean implements java.io.Serializable {

	// private static Logger log = Logger.getLogger(EmpresaManagedBean.class);

	private static final long serialVersionUID = 1L;

	private String selectedCnpj;
	private Empresa empresa;
	private Matriz matriz;
	private List<Empresa> empresas;
	private List<Matriz> matrizes;
	private Central central;
	private String msg;
	private boolean matrizFilial;
	private boolean editar;
	private Empresa emprAnterior;
	
	public EmpresaManagedBean() {
		central = new Central();
		matrizes = new ArrayList<Matriz>();
		empresas = new ArrayList<Empresa>();
		this.selectedCnpj = "";
		matriz = new Matriz();
		editar = false;
	}
	
	public boolean isMatrizFilial() {
		return matrizFilial;
	}

	public void setMatrizFilial(boolean matrizFilial) {
		this.matrizFilial = matrizFilial;
	}
	
	public Matriz getMatriz() {
		return matriz;
	}

	public void setMatriz(Matriz matriz) {
		this.matriz = matriz;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getEmpresas() {
		return central.getEmpresas();
		//return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public List<Matriz> getMatrizes() {
		return matrizes;
	}

	public void setMatrizes(List<Matriz> matrizes) {
		this.matrizes = matrizes;
	}

	public void setSelectedCnpj(String cnpj) {
		this.selectedCnpj = cnpj;
	}
	
	public String getSelectedCNPJ() {
		return selectedCnpj;
	}

	public void setMsg(String message) {
		this.msg = message;
	}
	
	public String getMsg(){
		return this.msg;
	}

	public void limparEmpresa() {
		this.empresa.setRazaoSocial("");
		this.empresa.setCNPJ("");
		this.empresa.setComplemento("");
		this.empresa.setLogradouro("");
		this.empresa.setMunicipio("");
		this.empresa.setUf("");
		this.empresa.setNumero(null);
	}

	public String editEmpresa() {
		// EmpresaDAO empresaDAO = new EmpresaDAO();
		// Empresa empresa = empresaDAO.getEmpresa();
		Empresa empresa = central.getEmpresa(this.getSelectedCNPJ());
		
		if (empresa != null) {
			this.empresa = empresa;
			
			this.empresa.setCNPJ(empresa.getCNPJ());
			this.empresa.setRazaoSocial(empresa.getRazaoSocial());
			this.empresa.setMunicipio(empresa.getMunicipio());
			this.empresa.setUf(empresa.getUf());
			this.empresa.setLogradouro(empresa.getLogradouro());
			this.empresa.setComplemento(empresa.getComplemento());
			this.empresa.setNumero(empresa.getNumero());
			this.editar = true;
			this.emprAnterior = empresa;

		} else {
			this.setMsg("Empresa nao encontrada!");
		}
		return "insert";
	}
	
	public String add(){
		return "insert";
	}
	
	public String update(){
		return "update";
	}

	public String createEmpresa() {
		String str = "index";
		try {
			// EmpresaDAO empresaDAO = new EmpresaDAO();
			central.incluirEmpresa(this.empresa);

			limparEmpresa();
			this.setMsg("Empresa cadastrada!");
		} catch (Exception e) {
			this.setMsg(e.getMessage());
			str = "insert";
		}
		return str;
	}

	public String deleteEmpresa() {
		String str = "index";
		try {
			central.deletarEmpresa(this.getSelectedCNPJ());
			limparEmpresa();
			this.setMsg("Excluído com sucesso!");
		} catch (Exception e) {
			this.setMsg(e.getMessage());
		}
		return str;
	}

	public List<EmpresaManagedBean> getListaMatrizes() {
		List<EmpresaManagedBean> empresas = new ArrayList<EmpresaManagedBean>();

		/*try {
			EmpresaDAO empresaDAO = new EmpresaDAO();

			for (Matriz matriz : empresaDAO.listMatrizes()) {
				EmpresaManagedBean bean = new EmpresaManagedBean();
				bean.setEmpresa(matriz);

				empresas.add(bean);
			}

		} catch (Exception e) {
			this.setMsg(e.getMessage());
		}*/

		return empresas;
	}

	public List<EmpresaManagedBean> getListaEmpresas() {
		limparEmpresa();
		List<EmpresaManagedBean> empresas = new ArrayList<EmpresaManagedBean>();

		/*try {
			EmpresaDAO empresaDAO = new EmpresaDAO();

			for (Empresa empresa : empresaDAO.listEmpresas()) {

				EmpresaManagedBean bean = new EmpresaManagedBean();
				bean.setEmpresa(empresa);

				empresas.add(bean);
			}

		} catch (Exception e) {
			this.setMsg(e.getMessage());
			log.error(e);
		}
		*/
		return empresas;
	}
	
	public String atualizarEmpresa(){
		if (editar){
			return this.updateEmpresa();
		} else
			return this.createEmpresa();
	}

	public String updateEmpresa() {
		String str = "index";
		try {
			if(central.atualizarEmpresa(this.emprAnterior, this.empresa)){
				this.setMsg("Empresa atualizada com sucesso!");
			} else
				this.setMsg("Erro ao Atualizar dados.");
			limparEmpresa();
		} catch (Exception e) {
			this.setMsg(e.getMessage());
			str = "deleteUpdate";
		}
		this.editar = false;
		return str;
	}

}
