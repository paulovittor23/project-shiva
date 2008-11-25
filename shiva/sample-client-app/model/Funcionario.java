package model;

import shiva.domain.mapping.LdapAttribute;
import shiva.domain.mapping.LdapDn;
import shiva.domain.mapping.LdapEntity;
import shiva.domain.validation.annotation.Pattern;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
@LdapEntity(
	directory="ou=funcionarios,o=unisantos",
	objectClass={"top","person","inetOrgPerson","organizationalPerson"}
)
public class Funcionario {
	
	//ATTRIBUTES
	
	@LdapDn("uid")
	@Pattern("^[a-z]{6,16}$")
	private String id;
	
	@LdapAttribute("cn")
	@Pattern("^[A-Z\\s]{2,20}$")
	private String nome;
	
	@LdapAttribute("sn")
	@Pattern("^[A-Z]{2,20}$")
	private String sobrenome;
	
	@LdapAttribute("telephoneNumber")
	@Pattern("^[0-9\\s]{8,20}$")
	private String telefone;
	
	@LdapAttribute("mobile")
	@Pattern("^[0-9\\s]{8,20}$")
	private String celular;
	
	@LdapAttribute("displayName")
	@Pattern("^[A-Z\\s]{2,20}$")
	private String apelido;
	
	@LdapAttribute("mail")
	@Pattern("^[a-z0-9_\\-]+(\\.[_a-z0-9\\-]+)*@([_a-z0-9\\-]+\\.)+([a-z]{2}|aero|arpa|biz|com|coop|edu|gov|info|int|jobs|mil|museum|name|nato|net|org|pro|travel)$")
	private String email;
	
	@LdapAttribute("employeeType")
	@Pattern("^[A-Z\\s]{5,30}$")
	private String cargo;
	
	@LdapAttribute("registeredAddress")
	@Pattern("^[A-Z0-9\\s\\.,-\\/]{10,60}$")
	private String endereco;
	
	//GETTERS AND SETTERS

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId( String id ) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome( String nome ) {
		this.nome = nome;
	}

	/**
	 * @return the sobrenome
	 */
	public String getSobrenome() {
		return this.sobrenome;
	}

	/**
	 * @param sobrenome the sobrenome to set
	 */
	public void setSobrenome( String sobrenome ) {
		this.sobrenome = sobrenome;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return this.telefone;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone( String telefone ) {
		this.telefone = telefone;
	}

	/**
	 * @return the celular
	 */
	public String getCelular() {
		return this.celular;
	}

	/**
	 * @param celular the celular to set
	 */
	public void setCelular( String celular ) {
		this.celular = celular;
	}

	/**
	 * @return the apelido
	 */
	public String getApelido() {
		return this.apelido;
	}

	/**
	 * @param apelido the apelido to set
	 */
	public void setApelido( String apelido ) {
		this.apelido = apelido;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail( String email ) {
		this.email = email;
	}

	/**
	 * @return the cargo
	 */
	public String getCargo() {
		return this.cargo;
	}

	/**
	 * @param cargo the cargo to set
	 */
	public void setCargo( String cargo ) {
		this.cargo = cargo;
	}

	/**
	 * @return the endereco
	 */
	public String getEndereco() {
		return this.endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco( String endereco ) {
		this.endereco = endereco;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		
		buf.append( "id: '" ).append( id != null ? id : "NULO" ).append( "'\n" );
		buf.append( "nome: '" ).append( nome != null ? nome : "NULO" ).append( "'\n" );
		buf.append( "sobrenome: '" ).append( sobrenome != null ? sobrenome : "NULO" ).append( "'\n" );
		buf.append( "telefone: '" ).append( telefone != null ? telefone : "NULO" ).append( "'\n" );
		buf.append( "celular: '" ).append( celular != null ? celular : "NULO" ).append( "'\n" );
		buf.append( "apelido: '" ).append( apelido != null ? apelido : "NULO" ).append( "'\n" );
		buf.append( "e-mail: '" ).append( email != null ? email : "NULO" ).append( "'\n" );
		buf.append( "cargo: '" ).append( cargo != null ? cargo : "NULO" ).append( "'\n" );
		buf.append( "endereco: '" ).append( endereco != null ? endereco : "NULO" ).append( "'\n" );
		
		return buf.toString();
	}
	
}
