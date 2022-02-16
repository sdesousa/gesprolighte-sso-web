package af.cmr.indyli.gespro.light.trans.managedbean;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import af.cmr.indyli.gespro.light.business.entity.GpEmployee;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpEmployeeService;
import af.cmr.indyli.gespro.light.business.service.impl.GpEmployeeServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectManagerServiceImpl;

@ManagedBean(name = "ctrEmployeeBean")
@RequestScoped
public class GpEmployeeManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GpEmployee empDataBean = new GpEmployee();
	private GpProjectManagerServiceImpl pmServive = new GpProjectManagerServiceImpl();
	private IGpEmployeeService<GpEmployee> empService = new GpEmployeeServiceImpl();

	private List<GpEmployee> empList = null;
	private boolean isPM = false;
	private UIComponent saveButton;

	public GpEmployeeManagedBean() {
		this.empList = this.empService.findAll();
	}

	public String saveEmployee() throws GesproBusinessException, ValidatorException {
		try {
			if(isPM) {
				GpProjectManager pm = new GpProjectManager(this.empDataBean);
				this.pmServive.create(pm);
			} else {
				this.empService.create(this.empDataBean);
			}
			this.empList = this.empService.findAll();
			empDataBean = new GpEmployee();
			return "success";
		} catch (GesproBusinessException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(saveButton.getClientId(context), msg);
			e.printStackTrace();
		}
		return "fail";
		
	}
	
	public String promoteEmpById() {
		String editEmpId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("empId");
		this.empDataBean = this.empService.findById(Integer.valueOf(editEmpId));
		this.pmServive.promoteToProjectManager(empDataBean);
		return "success";
	}

	public void validateEmail(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {
		String eMail = (String) value;
		Pattern p = Pattern.compile("[\\w\\.-]*[a-zA-Z0-9_]@[\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]");
		Matcher m = p.matcher(eMail);
		if (!m.matches()) {
			FacesMessage message = new FacesMessage("Adresse Email invalide !");
			throw new ValidatorException(message);
		}
	}

	public String updateEmpById() {
		String editEmpId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("empId");
		this.empDataBean = this.empService.findById(Integer.valueOf(editEmpId));
		return "success";
	}

	public String updateEmployee() throws GesproBusinessException, ValidatorException {
		try {
			this.empService.update(this.empDataBean);
			this.empList = this.empService.findAll();
			empDataBean = new GpEmployee();
			return "success";
		} catch (GesproBusinessException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(saveButton.getClientId(context), msg);
			e.printStackTrace();
		}
		return "fail";
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

	public boolean getIsPM() {
		return isPM;
	}

	public void setIsPM(boolean isPM) {
		this.isPM = isPM;
	}

	public UIComponent getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(UIComponent saveButton) {
		this.saveButton = saveButton;
	}
	
}
