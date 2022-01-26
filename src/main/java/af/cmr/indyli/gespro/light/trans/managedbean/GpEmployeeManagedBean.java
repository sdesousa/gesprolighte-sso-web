package af.cmr.indyli.gespro.light.trans.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;
import af.cmr.indyli.gespro.light.business.service.impl.GpEmployeeServiceImpl;

@ManagedBean(name = "ctrEmployeeBean")
@RequestScoped
public class GpEmployeeManagedBean implements Serializable{

	private GpEmployee empDataBean = new GpEmployee();
	private IGpEmployeeService<GpEmployee> empService = new GpEmployeeServiceImpl();

	private List<GpEmployee> empList = null;

	public GpEmployeeManagedBean() {
		this.empList = this.empService.findAll();
	}

	public String saveEmployee() throws GesproBusinessException {
		this.empService.create(this.empDataBean);
		this.empList = this.empService.findAll();
		return "success";
	}

	public void validateEmail(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {
		String eMail = (String) value;
		if (eMail.indexOf("@") < 0) {
			FacesMessage message = new FacesMessage("Adresse Email invalide !");
			throw new ValidatorException(message);
		}
	}

	public String updateEmpById() {
		String editEmpId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("empId");
		this.empDataBean = this.empService.findById(Integer.valueOf(editEmpId));
		return "success";
	}

	public String updateEmployee() throws GesproBusinessException {
		this.empService.update(this.empDataBean);
		this.empList = this.empService.findAll();
		return "success";
	}

	public String deleteEmpById() {
		String delEmpId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("empId");
		this.empService.deleteById(Integer.valueOf(delEmpId));
		this.empList = this.empService.findAll();
		return "success";
	}
	public GpEmployee getEmpDataBean() {
		return empDataBean;
	}

	public void setEmpDataBean(GpEmployee empDataBean) {
		this.empDataBean = empDataBean;
	}

	public List<GpEmployee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<GpEmployee> empList) {
		this.empList = empList;
	}
}
