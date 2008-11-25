package main;

import java.util.Iterator;
import java.util.List;

import model.Funcionario;
import model.Parceiro;
import shiva.cfg.Configuration;
import shiva.domain.exception.InvalidConfigurationException;
import shiva.domain.exception.validation.ValidationException;
import shiva.session.LdapSession;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
@SuppressWarnings({"unused","unchecked"})
public class Main {

	public static void main( String[] args ) {
		//
		configurarShiva();

		//
		//insereFuncionarios();
		
		//
		//inserirParceiros();
		
		//
		listarFuncionarios();
		
		//
		listarParceiros();
	}

	/**
	 * 
	 */
	private static void configurarShiva() {
		Configuration c = Configuration.getInstance();
		c.getEntityRegistrator().register( Funcionario.class );
		c.getEntityRegistrator().register( Parceiro.class );

		try {
			c.configure();
		} catch ( InvalidConfigurationException e ) {
			e.getImplicitExceptionsDetail();
		}
	}
	
	/**
	 * 
	 */
	private static void insereFuncionarios(){

		Funcionario f1 = new Funcionario();
		f1.setId( "prendeiro" );
		f1.setNome( "PAULO VITOR" );
		f1.setSobrenome( "RENDEIRO" );
		f1.setApelido( "CURURU" );
		f1.setTelefone( "13 32717438" );
		f1.setCelular( "13 91539921" );
		f1.setCargo( "ANALISTA PROGRAMADOR JAVA" );
		f1.setEmail( "paulovittor23@gmail.com" );
		f1.setEndereco( "AV. AFONO PENA 744, APTO. 31 - SANTOS/SP" );
		
		Funcionario f2 = new Funcionario();
		f2.setId( "roliveira" );
		f2.setNome( "ROBERTO" );
		f2.setSobrenome( "SU" );
		f2.setApelido( "DALAI" );
		f2.setTelefone( "13 32534924" );
		f2.setCelular( "13 81429581" );
		f2.setCargo( "ANALISTA DE SISTEMAS" );
		f2.setEmail( "roliveira_su@gmail.com" );
		f2.setEndereco( "AV. SIQUEIRA CAMPOS 45 - SANTOS/SP" );
		
		try {
			
			//insert
			LdapSession.getInstance().persist( f1 );
			LdapSession.getInstance().persist( f2 );
			
		} catch ( ValidationException e ) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * 
	 */
	private static void inserirParceiros(){
		
		Parceiro p1 = new Parceiro();
		p1.setId( "abril" );
		p1.setNome( "EDITORA ABRIL" );
		p1.setSegmento( "EDITORA" );
		p1.setTelefone( "11 24334929" );
		p1.setEndereco( "AV. DAS NACOES UNIDAS, 7221 - SAO PAULO/SP" );
		
		Parceiro p2 = new Parceiro();
		p2.setId( "cyrela" );
		p2.setNome( "CYRELLA" );
		p2.setSegmento( "CONSTRUCAO" );
		p2.setTelefone( "11 32435678" );
		p2.setEndereco( "AV. PRES. JUSCELINO KUBITSCHEK, 1455 - SAO PAULO/SP" );
		
		try {
			
			//
			LdapSession.getInstance().persist( p1 );
			LdapSession.getInstance().persist( p2 );
			
		} catch ( ValidationException e ) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 */
	private static void listarFuncionarios(){
		try {
			List all = LdapSession.getInstance().getAll( Funcionario.class );
			for( Iterator iterator = all.iterator(); iterator.hasNext(); ) {
				Funcionario f = ( Funcionario ) iterator.next();
				System.out.println( f );
			}
		} catch ( ValidationException e ) {
			e.printStackTrace();
		}
	}
	
	private static void listarParceiros(){
		try {
			List all = LdapSession.getInstance().getAll( Parceiro.class );
			for( Iterator iterator = all.iterator(); iterator.hasNext(); ) {
				Parceiro p = ( Parceiro ) iterator.next();
				System.out.println( p );
			}
		} catch ( ValidationException e ) {
			e.printStackTrace();
		}
	}
	
}