package model;

import shiva.domain.annotation.mapping.LdapAttribute;
import shiva.domain.annotation.mapping.LdapEntity;

@LdapEntity(
	directory="ou=funcionarios,o=unisantos",
	objectClass={"top","person","inetOrgPerson","organizationalPerson"}
)
public class Funcionario {
	
	@LdapAttribute("cn")
	private String nome;
	
	@LdapAttribute("sn")
	private String sobrenome;
	
	@LdapAttribute("uid")
	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return this.sobrenome;
	}
	public void setSobrenome( String sobrenome ) {
		this.sobrenome = sobrenome;
	}
	
}
