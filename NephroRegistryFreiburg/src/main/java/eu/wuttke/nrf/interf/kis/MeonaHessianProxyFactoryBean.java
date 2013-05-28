package eu.wuttke.nrf.interf.kis;

import org.springframework.remoting.RemoteLookupFailureException;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import eu.wuttke.nrf.interf.hessian.HessianProxyFactoryWithContext;

public class MeonaHessianProxyFactoryBean 
extends HessianProxyFactoryBean {
	
	public static String REQUEST_PROFILE_ATTRIBUTE_NAME = "X-Meona-Profile";

	public MeonaHessianProxyFactoryBean() {
		factory = new HessianProxyFactoryWithContext();
		setProxyFactory(factory);
	}
	
	public void setMeonaProfile(String p) {
		meonaProfile = p;
	}
	
	@Override
	public void prepare() throws RemoteLookupFailureException {
		assert meonaProfile != null && meonaProfile.length() > 0;
		super.prepare();
		factory.putContext(REQUEST_PROFILE_ATTRIBUTE_NAME, meonaProfile);
	}
	
	private HessianProxyFactoryWithContext factory;
	private String meonaProfile;
	
}
