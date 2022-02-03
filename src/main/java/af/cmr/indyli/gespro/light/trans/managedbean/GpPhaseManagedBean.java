package af.cmr.indyli.gespro.light.trans.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.entity.GpProject;
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
	private GpPhase phsDataBean = new GpPhase();
	private IGpPhaseService<GpPhase> phsService = new GpPhaseServiceImpl();
	private IGpProjectService<GpProject> prjService = new GpProjectServiceImpl();
	private List<GpPhase> phsList = null;
	private List<GpProject> projects;
	private Integer prjId;

	public GpPhaseManagedBean() {
		this.phsList = this.phsService.findAll();
	}

	// TODO
	public String savePhase() throws GesproBusinessException {
		prjDataBean.setGpOrganization(orgService.findById(orgId));
		prjDataBean.setGpChefProjet(empService.findById(pmId));
		this.prjService.create(this.prjDataBean);
		this.prjList = this.prjService.findAll();
		return "success";
	}
	// TODO
	public String addPhase() {
		this.organizations = this.orgService.findAll();
		this.projectManagers = this.empService.findAll();
		return "success";
	}
	// TODO
	public String updatePhase() throws GesproBusinessException {
		this.prjService.update(this.prjDataBean);
		this.prjList = this.prjService.findAll();
		return "success";
	}
	// TODO
	public String updatePhsById() {
		String editPrjId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("prjId");
		this.prjDataBean = this.prjService.findById(Integer.valueOf(editPrjId));
		this.organizations = this.orgService.findAll();
		this.projectManagers = this.empService.findAll();
		return "success";
	}
	// TODO
	public String deletePhsById() {
		String delPhsId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("phsId");
		this.phsService.deleteById(Integer.valueOf(delPhsId));
		this.phsList = this.phsService.findAll();
		return "success";
	}

}
