package eu.wuttke.nrf.interf.hessian;

import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.caucho.hessian.client.HessianProxyFactory;
import com.caucho.hessian.io.HessianRemoteObject;

public class HessianProxyFactoryWithContext extends HessianProxyFactory {

	private Map<String, String> context = new HashMap<String, String>();

	public Map<String, String> getContext() {
		return context;
	}

	public String putContext(String key, String value) {
		return context.put(key, value);
	}

	public Object create(Class<?> api, URL url, ClassLoader loader) {
		if (api == null)
			throw new NullPointerException("api must not be null for HessianProxyFactory.create()");

		HessianProxyWithContext handler = new HessianProxyWithContext(url, this, api);
		handler.setContext(context);
		return Proxy.newProxyInstance(loader, new Class[] { api, HessianRemoteObject.class }, handler);
	}

}
