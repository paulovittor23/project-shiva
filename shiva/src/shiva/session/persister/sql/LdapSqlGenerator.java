package shiva.session.persister.sql;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.SequencedHashMap;
import org.apache.log4j.Logger;

import shiva.domain.metadata.AttributeMapping;
import shiva.domain.metadata.EntityMapping;
import shiva.domain.operation.Operation;
import shiva.domain.operation.Restriction;
import shiva.domain.operation.filter.Filter;
import shiva.domain.operation.impl.Delete;
import shiva.domain.operation.impl.Insert;
import shiva.domain.operation.impl.Select;
import shiva.domain.operation.impl.Update;

/**
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
@SuppressWarnings("unchecked")
public class LdapSqlGenerator implements SqlGenerator {
	
	private Logger logger = Logger.getLogger(this.getClass());

	/* (non-Javadoc)
	 * @see shiva.session.persister.sql.SqlGen#generateInsertString(shiva.domain.metadata.EntityMapping, java.lang.Object)
	 */
	@Override
	public String generateInsertString(EntityMapping em, Object ldapEntity) {
		
		Operation operation = new Insert();

		// directoryName
		String directoryNameBound = em.getDirectoryNameBound();
		operation.setDirectoryName( directoryNameBound );

		// objectClass
		operation.setObjectClasses( em.getObjectClass() );		
		
		// attributes
		Map<String, Object> columns = new SequencedHashMap();
		
		Map<Field, AttributeMapping> ams = em.getAttributesMapped();
		Set<Field> keySet = ams.keySet();
				
		for (Field field : keySet) {
			AttributeMapping am = ams.get( field );
			
			try {
				
				//
				Field att = am.getAttribute();
				boolean accessible = att.isAccessible();
				
				if( ! accessible ){
					att.setAccessible( true );
				}
				
				String attributeNameBound = am.getAttributeNameBound();
				Object attributeValue = att.get( ldapEntity );
				
				if( ! accessible ){
					att.setAccessible( false );
				}
				
				if(am.isDn()){
					String dnType = am.getDnType();
					String dnValue = ((String) attributeValue) + "," + directoryNameBound;

					//operation.setDirectoryName( dnType + "=" + dnValue );
					operation.setDistinguishedName( dnType + "=" + dnValue );
				}
				
				columns.put(attributeNameBound, attributeValue );
				
			} catch (IllegalArgumentException e) {
				logger.error("generateInsertString is not ok :(", e);
			} catch (IllegalAccessException e) {
				logger.error("generateInsertString is not ok :(", e);
			}
		}
		
		operation.setColumns(columns);
		return operation.toStatementString();
	}
	
	/* (non-Javadoc)
	 * @see shiva.session.persister.sql.SqlGen#generateDeleteString(shiva.domain.metadata.EntityMapping, java.lang.Object)
	 */
	@Override
	public String generateDeleteString(EntityMapping em, Object ldapEntity) {

		Operation operation = new Delete();
		
		try {
			
			// directoryName
			String directoryNameBound = em.getDirectoryNameBound();
			operation.setDirectoryName( directoryNameBound );
			
			// dn
			AttributeMapping dnAttribute = em.getDnAttribute();
			Field attribute = dnAttribute.getAttribute();
			
			boolean accessible = attribute.isAccessible();
			
			if( ! accessible ){
				attribute.setAccessible( true );
			}
			
			Object attributeValue = attribute.get( ldapEntity );
			String dnType = dnAttribute.getDnType();
			String dnValue = (String) attributeValue;
			
			operation.setDistinguishedName( dnType + "=" + dnValue );
			
			if( ! accessible ){
				attribute.setAccessible( false );
			}
			
		} catch ( IllegalArgumentException e ) {
			logger.error("generateDeleteString is not ok :(", e);
		} catch ( IllegalAccessException e ) {
			logger.error("generateDeleteString is not ok :(", e);
		}
		
		return operation.toStatementString();
	}

	/* (non-Javadoc)
	 * @see shiva.session.persister.sql.SqlGen#generateUpdateString(shiva.domain.metadata.EntityMapping, java.lang.Object)
	 */
	@Override
	public String generateUpdateString(EntityMapping em, Object ldapEntity) {

		Operation operation = new Update();

		// directoryName
		String directoryNameBound = em.getDirectoryNameBound();
		operation.setDirectoryName( directoryNameBound );

		// objectClass
		operation.setObjectClasses( em.getObjectClass() );		
		
		// attributes
		Map<String, Object> columns = new SequencedHashMap();
		
		Map<Field, AttributeMapping> ams = em.getAttributesMapped();
		Set<Field> keySet = ams.keySet();
				
		for (Field field : keySet) {
			AttributeMapping am = ams.get( field );
			
			try {
				
				//
				Field att = am.getAttribute();
				boolean accessible = att.isAccessible();
				
				if( ! accessible ){
					att.setAccessible( true );
				}
				
				String attributeNameBound = am.getAttributeNameBound();
				Object attributeValue = att.get( ldapEntity );
				
				if( ! accessible ){
					att.setAccessible( false );
				}
				
				if(am.isDn()){
					
					String dnType = am.getDnType();
					String dnValue = ((String) attributeValue) + "," + directoryNameBound;
					
					//operation.setDirectoryName( dnType + "=" + dnValue );
					operation.setDistinguishedName( dnType + "=" + dnValue );
					
				}else{
					columns.put(attributeNameBound, attributeValue );
				}
				
			} catch (IllegalArgumentException e) {
				logger.error("generateInsertString is not ok :(", e);
			} catch (IllegalAccessException e) {
				logger.error("generateInsertString is not ok :(", e);
			}
		}
		
		operation.setColumns(columns);
		return operation.toStatementString();
	}

	/* (non-Javadoc)
	 * @see shiva.session.persister.sql.SqlGenerator#generateSelectByDnString(shiva.domain.metadata.EntityMapping, java.lang.Object)
	 */
	@Override
	public String generateSelectByDnString( EntityMapping em, Object ldapEntity ) {
		
		Operation operation = new Select();
		
		//distinguished name
		AttributeMapping dnAtt = em.getDnAttribute();
		String dnType = dnAtt.getDnType();
		
		Field att = dnAtt.getAttribute();
		boolean accessible = att.isAccessible();
		
		if( ! accessible ){
			att.setAccessible( true );
		}
		
		try {
		
			Object attributeValue = att.get( ldapEntity );
			String dnValue = ((String) attributeValue) + "," + em.getDirectoryNameBound();
			
			//operation.setDirectoryName( dnType + "=" + dnValue );
			operation.setDistinguishedName( dnType + "=" + dnValue );
			
		} catch (IllegalArgumentException e) {
			logger.error("generateInsertString is not ok :(", e);
		} catch (IllegalAccessException e) {
			logger.error("generateInsertString is not ok :(", e);
		}
		
		if( ! accessible ){
			att.setAccessible( false );
		}
		
		return operation.toStatementString();
	}
	
	/* (non-Javadoc)
	 * @see shiva.session.persister.sql.SqlGenerator#generateSelectAllString(shiva.domain.metadata.EntityMapping, java.lang.Class)
	 */
	@Override
	public String generateSelectAllString( EntityMapping em, Class clazz ) {
		
		Select operation = new Select();
		
		//distinguished name
		AttributeMapping dnAtt = em.getDnAttribute();
		//String dnType = dnAtt.getDnType();
		
		Field att = dnAtt.getAttribute();
		boolean accessible = att.isAccessible();
		
		if( ! accessible ){
			att.setAccessible( true );
		}
		
		try {
		
			operation.setDistinguishedName( em.getDirectoryNameBound() );

			//
			Filter filter = operation.getFilter();
			
			String[] objectClasses = em.getObjectClass();
			for( int i = 0; i < objectClasses.length; i++ ) {
				String objectClass = objectClasses[ i ];
				filter.addRestriction( new Restriction("objectClass", "=", objectClass) );
			}
			
		} catch (IllegalArgumentException e) {
			logger.error("generateInsertString is not ok :(", e);
		} 
		
		if( ! accessible ){
			att.setAccessible( false );
		}
		
		return operation.toStatementString();
	}

}
