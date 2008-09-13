package shiva.cfg.component.mapper.model.metadata;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.SequencedHashMap;

@SuppressWarnings("unchecked")
public class EntityMapping {
	
	private Class clazz;
	private String directoryNameBound;
	private String[] objectClass;
	
	private Map<Field, AttributeMapping> attributesMapped;

	/**
	 * 
	 * @return the clazz
	 */
	public Class getClazz() {
		return clazz;
	}

	/**
	 * 
	 * @param clazz the clazz to set
	 */
	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	/**
	 * 
	 * @return the directoryNameBound
	 */
	public String getDirectoryNameBound() {
		return directoryNameBound;
	}

	/**
	 * 
	 * @param directoryNameBound the directoryNameBound to set
	 */
	public void setDirectoryNameBound(String directoryNameBound) {
		this.directoryNameBound = directoryNameBound;
	}

	/**
	 * 
	 * @return the attributesMapped
	 */
	public Map<Field, AttributeMapping> getAttributesMapped() {
		return attributesMapped;
	}

	/**
	 * 
	 * @param attributesMapped the attributesMapped to set
	 */
	public void setAttributesMapped(Map<Field, AttributeMapping> attributesMapped) {
		this.attributesMapped = attributesMapped;
	}
	
	/**
	 * 
	 * @return the objectClass
	 */
	public String[] getObjectClass() {
		return objectClass;
	}

	/**
	 * 
	 * @param objectClass the objectClass to set
	 */
	public void setObjectClass(String[] objectClass) {
		this.objectClass = objectClass;
	}

	/**
	 * 
	 * @param attribute
	 * @param attributeMapping
	 */
	public void addAttributeMapped(Field attribute, AttributeMapping attributeMapping){
		if( this.attributesMapped == null ){
			this.attributesMapped = new SequencedHashMap();
		}
		
		this.attributesMapped.put(attribute, attributeMapping);
	}

	/**
	 * 
	 * 
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append( "clazz: " + (this.clazz != null ? this.clazz.getCanonicalName() : null) + "\n" );
		buffer.append( "directoryNameBound: " + this.directoryNameBound + "\n" );
		
		buffer.append("objectClass: {");
		if( this.objectClass != null && this.objectClass.length > 0 ){
			for (int i = 0; i < this.objectClass.length; i++) {
				buffer.append( this.objectClass[i] + (i < this.objectClass.length - 1 ? ", " : "") );
			}
		}
		buffer.append("}\n");
		
		buffer.append( "attributesMapped: \n" );
		
		if( this.attributesMapped != null ){
			
			Set<Field> keySet = this.attributesMapped.keySet();
			
			for (Field field : keySet) {
				
				AttributeMapping attributeMapping = this.attributesMapped.get(field);
				buffer.append( attributeMapping + "\n" );
			}
		}else{
			buffer.append( "-none \n" );
		}
		
		return buffer.toString();
	}
	
}
