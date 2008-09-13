package shiva.cfg.component.registrator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Paulo Vitor
 * @author Roberto Su
 * 
 * @description
 *
 * @param <E>
 */
public class Registrator<E> implements Registrable<E> {

	private Set<E> registeredObjects;
	
	public Registrator() {
		this.registeredObjects = new HashSet<E>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jldap.core.config.behaviors.registrable.Registrable#getRegisteredObjects()
	 */
	public Set<E> getRegisteredObjects() {
		return this.registeredObjects;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jldap.core.config.behaviors.registrable.Registrable#register(java.lang.Object)
	 */
	public void register(E element) {
		if (!this.registeredObjects.contains(element)) {
			this.registeredObjects.add(element);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jldap.core.config.behaviors.registrable.Registrable#registerAll(java.util.Collection)
	 */
	public void registerAll(Collection<E> elements) {
		if (elements != null && elements.size() > 0) {
			for (E element : elements) {
				this.register(element);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jldap.core.config.behaviors.registrable.Registrable#unregister(java.lang.Object)
	 */
	public void unregister(E element) {
		if (this.registeredObjects.contains(element)) {
			this.registeredObjects.remove(element);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jldap.core.config.behaviors.registrable.Registrable#unregisterAll(java.util.Collection)
	 */
	public void unregisterAll(Collection<E> elements) {
		if (elements != null && elements.size() > 0) {
			for (E element : elements) {
				this.unregister(element);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jldap.core.config.components.registrator.Registrable#registeredCount()
	 */
	public Integer registeredObjectsCount() {
		return this.registeredObjects.size();
	}

	/*
	 * 
	 * (non-Javadoc)
	 * @see jldap.core.config.component.registrator.Registrable#isObjectRegistered(java.lang.Object)
	 */
	public Boolean isObjectRegistered(E element) {
		if (this.registeredObjects != null && this.registeredObjects.size() > 0) {
			for (E registeredObject : this.registeredObjects) {
				if( registeredObject.equals(element) ){
					return true;
				}
			}
		}
		return false;
	}

}
