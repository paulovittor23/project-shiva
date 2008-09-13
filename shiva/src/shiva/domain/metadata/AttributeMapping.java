package shiva.domain.metadata;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;

/**
 * 
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 */
@SuppressWarnings("unchecked")
public class AttributeMapping {
	
	private Field attribute;
	private String attributeNameBound;
	private Collection<Annotation> validations;
	
	/**
	 * 
	 * @return the attribute
	 */
	public Field getAttribute() {
		return attribute;
	}
	
	/**
	 * 
	 * @param attribute the attribute to set
	 */
	public void setAttribute(Field attribute) {
		this.attribute = attribute;
	}
	
	/**
	 * 
	 * @return the attributeNameBound
	 */
	public String getAttributeNameBound() {
		return attributeNameBound;
	}
	
	/**
	 * 
	 * @param attributeNameBound the attributeNameBound to set
	 */
	public void setAttributeNameBound(String attributeNameBound) {
		this.attributeNameBound = attributeNameBound;
	}
	
	/**
	 * 
	 * @return the validations
	 */
	public Collection<Annotation> getValidations() {
		return validations;
	}
	
	/**
	 * 
	 * @param validations the validations to set
	 */
	public void setValidations(Collection<Annotation> validations) {
		this.validations = validations;
	}
	
	
	/*  */
	
	/**
	 * 
	 * @return
	 */
	public boolean isUidAttribute(){
	    return attributeNameBound != null && attributeNameBound.equalsIgnoreCase("UID");
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isValidated(){
		return this.validations != null && this.validations.size() > 0;
	}
	
	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public boolean isValidatedBy( Class clazz ){
		if( this.validations != null && this.validations.size() > 0 ){
			for (Annotation annotation : this.validations) {
				if( annotation.getClass().equals( clazz ) ){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public Annotation getValidationBy( Class clazz ){
		if( this.validations != null && this.validations.size() > 0 ){
			for (Annotation annotation : this.validations) {
				if( annotation.getClass().equals( clazz ) ){
					return annotation;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * 
	 */
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("\tattribute: " + (attribute != null ? attribute.getName() : "NULL" ) + "\n");
		buffer.append("\tattributeNameBound: " + attributeNameBound + "\n");
		buffer.append("\tvalidations: \n");
		
		if( this.validations != null ){
			for (Annotation validation : this.validations) {
				buffer.append("\t\t-" + validation.getClass() + " \n");
			}
		}else{
			buffer.append("\t\t-NONE");
		}
		
		return buffer.toString();
	}
	
}
