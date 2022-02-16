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

import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpProjectManagerService;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectManagerServiceImpl;

@ManagedBean(name = "ctrProjectManagerBean")
@RequestScoped
public class GpProjectManagerManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GpProjectManager pmDataBean = new GpProjectManager();
	private IGpProjectManagerService pmService = new GpProjectManagerServiceImpl();

	private List<GpProjectManager> pmList = null;
	private UIComponent saveButton;

	public GpProjectManagerManagedBean() {
		this.pmList = this.pmService.findAll();
	}

	public String saveEmployee() throws GesproBusinessException {
		try {
			this.pmService.create(this.pmDataBean);
			this.pmList = this.pmService.findAll();
			return "success";
		} catch (GesproBusinessException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(saveButton.getClientId(context), msg);
			e.printStackTrace();
		}
		return "fail";
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
		this.pmDataBean = this.pmService.findById(Integer.valueOf(editEmpId));
		return "success";
	}

	public String updateEmployee() throws GesproBusinessException {
		try {
			this.pmService.update(this.pmDataBean);
			this.pmList = this.pmService.findAll();
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
		this.pmService.deleteById(Integer.valueOf(delEmpId));
		this.pmList = this.pmService.findAll();
		return "success";
	}

	public GpProjectManager getPmDataBean() {
		return pmDataBean;
	}

	public void setPmDataBean(GpProjectManager pmDataBean) {
		this.pmDataBean = pmDataBean;
	}

	public List<GpProjectManager> getPmList() {
		return pmList;
	}

	public void setPmList(List<GpProjectManager> pmList) {
		this.pmList = pmList;
	}
	
	public UIComponent getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(UIComponent saveButton) {
		this.saveButton = saveButton;
	}
	
}
