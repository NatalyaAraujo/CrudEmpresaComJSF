package empresa.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-13T14:39:39.851-0300")
@StaticMetamodel(Empresa.class)
public class Empresa_ {
	public static volatile SingularAttribute<Empresa, String> CNPJ;
	public static volatile SingularAttribute<Empresa, String> razaoSocial;
	public static volatile SingularAttribute<Empresa, String> logradouro;
	public static volatile SingularAttribute<Empresa, Integer> numero;
	public static volatile SingularAttribute<Empresa, String> complemento;
	public static volatile SingularAttribute<Empresa, String> municipio;
	public static volatile SingularAttribute<Empresa, String> uf;
	public static volatile SingularAttribute<Empresa, String> telefone;
	public static volatile SingularAttribute<Empresa, String> email;
}
