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
	directory="ou=parceiros,o=unisantos", 
	objectClass={"top","organization"}
)
public class Parceiro {

	@LdapDn("o")
	@Pattern("^[a-z]{5,16}$")
	private String id;
	
	@LdapAttribute("description")
	@Pattern("^[A-Z\\s]{6,16}$")
	private String nome;
	
	@LdapAttribute("telephoneNumber")
	@Pattern("^[0-9\\s]{8,20}$")
	private String telefone;
	
	@LdapAttribute("businessCategory")
	@Pattern("^[A-Z\\s]{6,16}$")
	private String segmento;
	
	@LdapAttribute("registeredAddress")
	@Pattern("^[A-Z0-9\\s\\.,-\\/]{10,60}$")
	private String endereco;

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
	 * @return the segmento
	 */
	public String getSegmento() {
		return this.segmento;
	}

	/**
	 * @param segmento the segmento to set
	 */
	public void setSegmento( String segmento ) {
		this.segmento = segmento;
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
		buf.append("ID: '").append( id != null ? id : "NULO" ).append( "'\n" );
		buf.append("NOME: '").append( nome != null ? nome : "NULO" ).append( "'\n" );
		buf.append("TELEFONE: '").append( telefone != null ? telefone : "NULO" ).append( "'\n" );
		buf.append("SEGMENTO: '").append( segmento != null ? segmento : "NULO" ).append( "'\n" );
		buf.append("ENDERECO: '").append( endereco != null ? endereco : "NULO" ).append( "'\n" );
		return buf.toString();
	}
	
}
