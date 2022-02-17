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

import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpOrganizationService;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;

@ManagedBean(name = "ctrOrganizationBean")
@RequestScoped
public class GpOrganizationManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GpOrganization orgDataBean = new GpOrganization();
	private IGpOrganizationService<GpOrganization> orgService = new GpOrganizationServiceImpl();

	private List<GpOrganization> orgList = null;

	public GpOrganizationManagedBean() {
		this.orgList = this.orgService.findAll();
	}

	public String saveOrganization() throws GesproBusinessException {
		this.orgService.create(this.orgDataBean);
		this.orgList = this.orgService.findAll();
		this.orgDataBean = new GpOrganization();
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

	public String updateOrgById() {
		String editOrgId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("orgId");
		this.orgDataBean = this.orgService.findById(Integer.valueOf(editOrgId));
		return "success";
	}

	public String updateOrganization() throws GesproBusinessException {
		this.orgService.update(this.orgDataBean);
		this.orgList = this.orgService.findAll();
		this.orgDataBean = new GpOrganization();
		return "success";
	}

	public String deleteOrgById() {
		String delOrgId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("orgId");
		this.orgService.deleteById(Integer.valueOf(delOrgId));
		this.orgList = this.orgService.findAll();
		return "success";
	}

	public GpOrganization getOrgDataBean() {
		return orgDataBean;
	}

	public void setOrgDataBean(GpOrganization orgDataBean) {
		this.orgDataBean = orgDataBean;
	}

	public List<GpOrganization> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<GpOrganization> orgList) {
		this.orgList = orgList;
	}
}
