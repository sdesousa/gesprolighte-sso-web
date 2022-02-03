package af.cmr.indyli.gespro.light.trans.managedbean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
	private GpProject prjDataBean = new GpProject();
	private IGpProjectService<GpProject> prjService = new GpProjectServiceImpl();
	private GpProjectManagerServiceImpl empService = new GpProjectManagerServiceImpl();
	private GpOrganizationServiceImpl orgService = new GpOrganizationServiceImpl();

	private List<GpProject> prjList = null;
	private List<GpOrganization> organizations;
	private List<GpProjectManager> projectManagers;
	private Integer orgId;
	private Integer pmId;
	private TimeZone tz = TimeZone.getDefault();

	public GpProjectManagedBean() {
		this.prjList = this.prjService.findAll();
	}

	
	public String saveProject() throws GesproBusinessException {
		prjDataBean.setCreationDate(new Date());
		prjDataBean.setGpOrganization(orgService.findById(orgId));
		prjDataBean.setGpChefProjet(empService.findById(pmId));
		this.prjService.create(this.prjDataBean);
		this.prjList = this.prjService.findAll();
		return "success";
	}

	public String addProject() {
		this.organizations = this.orgService.findAll();
		this.projectManagers = this.empService.findAll();
		return "success";
	}

	public String updateProject() throws GesproBusinessException {
		this.prjService.update(this.prjDataBean);
		this.prjList = this.prjService.findAll();
		return "success";
	}

	public String updatePrjById() {
		String editPrjId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prjId");
		this.prjDataBean = this.prjService.findById(Integer.valueOf(editPrjId));
		this.organizations = this.orgService.findAll();
		this.projectManagers = this.empService.findAll();
		return "success";
	}
	
	// TODO
	public String deletePrjById() {
		String delPrjId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prjId");
		this.prjService.deleteById(Integer.valueOf(delPrjId));
		this.prjList = this.prjService.findAll();
		return "success";
	}
	
//	public String getProjectPhase() {
//		String projectId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
//		// TODO : R�cup�rer Un project � partir de la valeur enti�re de la cha�ne de
//		// caract�res projectId
//		return "success";
//	}

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

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getPmId() {
		return pmId;
	}

	public void setPmId(Integer pmId) {
		this.pmId = pmId;
	}

	public TimeZone getTz() {
		return tz;
	}

	public void setTz(TimeZone tz) {
		this.tz = tz;
	}
	
}
