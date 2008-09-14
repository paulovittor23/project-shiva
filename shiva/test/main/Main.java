package main;

import model.Funcionario;
import shiva.cfg.Configuration;
import shiva.domain.exception.InvalidConfigurationException;
import shiva.session.LdapSession;

public class Main {

	public static void main( String[] args ) {
		configuraJLdapAnnotations();

		Funcionario a = new Funcionario();
		a.setId( "cribeiro" );
		a.setNome( "Claudio" );
		a.setSobrenome( "Ribeiro" );
		
		Funcionario b = new Funcionario();
		b.setId( "ldantas" );
		b.setNome( "Luisa" );
		b.setSobrenome( "Dantas" );
		
		LdapSession.getInstance().persist( a );
		LdapSession.getInstance().persist( b );
	}

	private static void configuraJLdapAnnotations() {
		Configuration c = Configuration.getInstance();
		c.getEntityRegistrator().register( Funcionario.class );

		try {
			c.configure();
		} catch ( InvalidConfigurationException e ) {
			e.getImplicitExceptionsDetail();
		}
	}

}
