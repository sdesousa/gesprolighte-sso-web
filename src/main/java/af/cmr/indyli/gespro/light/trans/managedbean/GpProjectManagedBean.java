package af.cmr.indyli.gespro.light.trans.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
//import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
//import af.cmr.indyli.gespro.light.business.service.IGpPhaseService;
import af.cmr.indyli.gespro.light.business.service.IGpProjectService;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;
//import af.cmr.indyli.gespro.light.business.service.impl.GpPhaseServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectManagerServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectServiceImpl;

@ManagedBean(name = "ctrProjectBean")
@SessionScoped
public class GpProjectManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GpProject prjDataBean;
	private IGpProjectService<GpProject> prjService = new GpProjectServiceImpl();
	private GpProjectManagerServiceImpl empService = new GpProjectManagerServiceImpl();
	private GpOrganizationServiceImpl orgService = new GpOrganizationServiceImpl();
	private List<GpProject> prjList = null;
	private List<GpOrganization> organizations;
	private List<GpProjectManager> projectManagers;
	private UIComponent saveButton;

	public GpProjectManagedBean() {
		this.prjList = this.prjService.findAll();
		this.prjDataBean = new GpProject();
		this.prjDataBean.setGpChefProjet(new GpProjectManager());
		this.prjDataBean.setGpOrganization(new GpOrganization());
	}

	public String saveProject() throws GesproBusinessException {
		String redirect = "fail";
		try {
			this.prjService.create(this.prjDataBean);
			redirect = "success";
		} catch (GesproBusinessException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(saveButton.getClientId(context), msg);
			e.printStackTrace();
		}
		this.prjList = this.prjService.findAll();
		this.reinitFields();
		return redirect;
	}

	public String addProject() {
		this.organizations = this.orgService.findAll();
		this.projectManagers = this.empService.findAll();
		return "success";
	}

	public String updateProject() throws GesproBusinessException {
		String redirect = "fail";
		try {
			this.prjService.update(this.prjDataBean);
			redirect = "success";
		} catch (GesproBusinessException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(saveButton.getClientId(context), msg);
			e.printStackTrace();
		}
		this.prjList = this.prjService.findAll();
		this.reinitFields();
		return redirect;
	}

	public String updatePrjById() {
		String editPrjId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prjId");
		this.prjDataBean = this.prjService.findById(Integer.valueOf(editPrjId));
		this.organizations = this.orgService.findAll();
		this.projectManagers = this.empService.findAll();
		return "success";
	}
	
	public String deletePrjById() {
		String delPrjId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prjId");
		this.prjService.deleteById(Integer.valueOf(delPrjId));
		this.prjList = this.prjService.findAll();
		return "success";
	}

	public GpProject getPrjDataBean() {
		return prjDataBean;
	}

	public void setPrjDataBean(GpProject prjDataBean) {
		this.prjDataBean = prjDataBean;
	}

	public List<GpProject> getPrjList() {
		return prjList;
	}

	public void setPrjList(List<GpProject> prjList) {
		this.prjList = prjList;
	}

	public List<GpOrganization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<GpOrganization> organizations) {
		this.organizations = organizations;
	}

	public List<GpProjectManager> getProjectManagers() {
		return projectManagers;
	}

	public void setProjectManagers(List<GpProjectManager> projectManagers) {
		this.projectManagers = projectManagers;
	}
	
	public UIComponent getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(UIComponent saveButton) {
		this.saveButton = saveButton;
	}
	
	private void reinitFields() {
		this.prjDataBean = new GpProject();
		this.prjDataBean.setGpChefProjet(new GpProjectManager());
		this.prjDataBean.setGpOrganization(new GpOrganization());
	}
	
}
