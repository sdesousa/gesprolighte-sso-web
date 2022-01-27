package af.cmr.indyli.gespro.light.trans.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import af.cmr.indyli.gespro.light.business.entity.GpPhase;
import af.cmr.indyli.gespro.light.business.service.IGpPhaseService;
import af.cmr.indyli.gespro.light.business.service.impl.GpPhaseServiceImpl;

@ManagedBean(name = "ctrPhaseBean")
@RequestScoped
public class GpPhaseManagedBean implements Serializable {

	private GpPhase phaseDataBean = new GpPhase();
	private IGpPhaseService phaseService = new GpPhaseServiceImpl();

	private List<GpPhase> phaseList = null;

	public GpPhaseManagedBean() {
		this.phaseList = this.phaseService.findAll();
	}

	public GpPhase getPhaseDataBean() {
		return phaseDataBean;
	}

	public void setPhaseDataBean(GpPhase phaseDataBean) {
		this.phaseDataBean = phaseDataBean;
	}

	public List<GpPhase> getPhaseList() {
		return phaseList;
	}

	public void setPhaseList(List<GpPhase> phaseList) {
		this.phaseList = phaseList;
	}

}
