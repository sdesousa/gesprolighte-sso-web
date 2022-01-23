package af.cmr.indyli.gespro.light.trans.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="personBean")
@SessionScoped
public class PersonManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	public String getName() {
		return name; 
	}

	public void setName(String name) {
		this.name = name;
	}

}
