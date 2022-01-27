package af.cmr.indyli.gespro.light.trans.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpPhaseService;
import af.cmr.indyli.gespro.light.business.service.IGpProjectService;
import af.cmr.indyli.gespro.light.business.service.impl.GpPhaseServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectServiceImpl;

@ManagedBean(name = "ctrProjetBean")
@RequestScoped
public class GpProjectManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5407580102778482190L;
	/**
	 * 
	 */
	private GpProject projectDataBean = new GpProject();
	private IGpProjectService projetService = new GpProjectServiceImpl();
	private IGpPhaseService phaseService = new GpPhaseServiceImpl();
	private List<GpProject> projectList = null;
	private List<GpPhase> phaseList = null;

	public GpProjectManagedBean() {
		this.projectList = this.projetService.findAll();
	}

	public String saveProject() throws GesproBusinessException {
		this.projetService.create(this.projectDataBean);
		this.projectList = this.projetService.findAll();
		return "success";
	}

	public String updateProject() {
		return "succcess";
	}

	public String getProjectPhase() {
		List<GpPhase> projectphaseList = new ArrayList<GpPhase>();
		String projectId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		this.projectDataBean = this.projetService.findById(Integer.valueOf(projectId));
		phaseList = phaseService.findAll();
		for (GpPhase gpPhase : phaseList) {
			if (gpPhase.getGpProject().getId() == Integer.valueOf(projectId)) {
				projectphaseList.add(gpPhase);
			}

		}
		this.phaseList = projectphaseList;
		return "success";
	}

	public String update() {
		return "success";
	}

	public GpProject getProjectDataBean() {
		return projectDataBean;
	}

	public void setProjectDataBean(GpProject projectDataBean) {
		this.projectDataBean = projectDataBean;
	}

	public List<GpProject> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<GpProject> projectList) {
		this.projectList = projectList;
	}

	public List<GpPhase> getPhaseList() {
		return phaseList;
	}

	public void setPhaseList(List<GpPhase> phaseList) {
		this.phaseList = phaseList;
	}

}
