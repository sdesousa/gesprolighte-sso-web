package af.cmr.indyli.gespro.light.trans.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpPhaseService;
import af.cmr.indyli.gespro.light.business.service.IGpProjectService;
import af.cmr.indyli.gespro.light.business.service.impl.GpPhaseServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectServiceImpl;

@ManagedBean(name = "ctrPhaseBean")
@SessionScoped
public class GpPhaseManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GpPhase phsDataBean;
	private IGpPhaseService<GpPhase> phsService = new GpPhaseServiceImpl();
	private IGpProjectService<GpProject> prjService = new GpProjectServiceImpl();
	private List<GpPhase> phsList;
	private List<GpProject> projects;
	private UIComponent saveButton;

	public GpPhaseManagedBean() {
		this.phsList = this.phsService.findAll();
		this.phsDataBean = new GpPhase();
		this.phsDataBean.setGpProject(new GpProject());
	}

	public String savePhase() {
		String redirect = "fail";
		try {
			this.phsService.create(this.phsDataBean);
			redirect = "success";
		} catch (GesproBusinessException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(saveButton.getClientId(context), msg);
			e.printStackTrace();
		}
		this.phsList = this.phsService.findAll();
		this.reinitFields();
		return redirect;
	}
	
	public String addPhase() {
		this.projects = this.prjService.findAll();
		return "success";
	}
	public String updatePhase() {
		String redirect = "fail";
		try {
			this.phsService.update(this.phsDataBean);
			redirect = "success";
		} catch (GesproBusinessException e) {
			FacesMessage msg = new FacesMessage(e.getMessage());
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(saveButton.getClientId(context), msg);
			e.printStackTrace();
		}
		this.phsList = this.phsService.findAll();
		this.reinitFields();
		return redirect;
	}
	public String updatePhsById() {
		String editPhsId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("phsId");
		this.phsDataBean = this.phsService.findById(Integer.valueOf(editPhsId));
		this.projects = this.prjService.findAll();
		return "success";
	}
	public String deletePhsById() {
		String delPhsId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("phsId");
		this.phsService.deleteById(Integer.valueOf(delPhsId));
		this.phsList = this.phsService.findAll();
		return "success";
	}

	public GpPhase getPhsDataBean() {
		return phsDataBean;
	}

	public void setPhsDataBean(GpPhase phsDataBean) {
		this.phsDataBean = phsDataBean;
	}

	public List<GpPhase> getPhsList() {
		return phsList;
	}

	public void setPhsList(List<GpPhase> phsList) {
		this.phsList = phsList;
	}

	public List<GpProject> getProjects() {
		return projects;
	}

	public void setProjects(List<GpProject> projects) {
		this.projects = projects;
	}

	public UIComponent getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(UIComponent saveButton) {
		this.saveButton = saveButton;
	}
	
	private void reinitFields() {
		this.phsDataBean = new GpPhase();
		this.phsDataBean.setGpProject(new GpProject());
	}

}
