package ca.bcit.comp4655.jms.admin;

import java.util.Properties;

import javax.naming.Context;



public class PropertiesFactory
{
	private static final long serialVersionUID = 2875815472750671318L;
	
	public static Properties getProperties ( String url )
	{
		Properties props = new Properties();
		props.setProperty( Context.INITIAL_CONTEXT_FACTORY ,"org.jnp.interfaces.NamingContextFactory" );
		props.setProperty( "java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces" );
		props.setProperty( "java.naming.provider.url", url );
		return props;
	}
}
