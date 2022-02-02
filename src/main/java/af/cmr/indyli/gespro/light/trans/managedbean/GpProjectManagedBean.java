package af.cmr.indyli.gespro.light.trans.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import af.cmr.indyli.gespro.light.business.entity.GpOrganization;
import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
import af.cmr.indyli.gespro.light.business.entity.GpProjectManager;
import af.cmr.indyli.gespro.light.business.exception.GesproBusinessException;
import af.cmr.indyli.gespro.light.business.service.IGpPhaseService;
import af.cmr.indyli.gespro.light.business.service.IGpProjectService;
import af.cmr.indyli.gespro.light.business.service.impl.GpOrganizationServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpPhaseServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectManagerServiceImpl;
import af.cmr.indyli.gespro.light.business.service.impl.GpProjectServiceImpl;

@ManagedBean(name = "ctrProjetBean")
@RequestScoped
public class GpProjectManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GpProject projectDataBean = new GpProject();
	private GpOrganization organizationDataBean = new GpOrganization();
	private IGpProjectService projetService = new GpProjectServiceImpl();
	private IGpPhaseService phaseService = new GpPhaseServiceImpl();
	private GpProjectManagerServiceImpl empService = new GpProjectManagerServiceImpl();
	private GpOrganizationServiceImpl orgService = new GpOrganizationServiceImpl();

	private List<GpProject> projectList = null;
	private List<GpPhase> phaseList = null;
	private List<GpOrganization> organizations;
	private List<GpProjectManager> projectManagers;
	private GpOrganization org;
	private String idOrg;
	private String idEmp;

	public GpProjectManagedBean() {
		// TODO : recuperez liste tous les projets
	}

	public String saveProject() throws GesproBusinessException {
		// TODO : appellez le service de creation de projects
		// TODO : recuperez la nouvelle liste des projets

		return "success";
	}

	public String addProject() {

		this.organizations = this.orgService.findAll();
		this.projectManagers = this.empService.findAll();

		return "success";
	}

	public String updateProject() {
		return "succcess";
	}

	public String getProjectPhase() {
		String projectId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

		// TODO : Récupérer Un project à partir de la valeur entière de la chaîne de
		// caractères projectId

		return "success";
	}

	public String update() {
		return "success";
	}

	// TODO : Génère les getter et les setter

}
