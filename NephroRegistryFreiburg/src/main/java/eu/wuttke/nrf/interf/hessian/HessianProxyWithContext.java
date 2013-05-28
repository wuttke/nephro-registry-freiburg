package eu.wuttke.nrf.interf.hessian;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.caucho.hessian.client.HessianConnection;
import com.caucho.hessian.client.HessianProxy;
import com.caucho.hessian.client.HessianProxyFactory;

public class HessianProxyWithContext 
extends HessianProxy {

	private static final long serialVersionUID = 1L;
	private Map<String, String> context = new HashMap<String, String>();

	public HessianProxyWithContext(URL url, HessianProxyFactory factory, Class<?> type) {
		super(url, factory, type);
	}

	public HessianProxyWithContext(URL url, HessianProxyFactory factory) {
		super(url, factory);
	}

	public void setContext(Map<String, String> context) {
		this.context = context;
	}

	@Override
	protected void addRequestHeaders(HessianConnection conn) {
		super.addRequestHeaders(conn);
		for (Entry<String, String> e : context.entrySet()) {
			conn.addHeader(e.getKey(), e.getValue());
		}
	}

}
