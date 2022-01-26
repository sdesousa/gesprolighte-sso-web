package af.cmr.indyli.gespro.light.trans.managedbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpProjectService;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectServiceImpl;

@ManagedBean(name = "ctrProjetBean")
@RequestScoped
public class GpProjectManagedBean {

	private GpProject projectDataBean = new GpProject();
	private IGpProjectService projetService = new GpProjectServiceImpl();

	private List<GpProject> projectList = null;

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

}
