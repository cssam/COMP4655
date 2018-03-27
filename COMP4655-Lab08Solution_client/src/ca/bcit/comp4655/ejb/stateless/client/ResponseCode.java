package ca.bcit.comp4655.ejb.stateless.client;


public class ResponseCode
{

	protected String code;
	protected String desc;

	public String getCode()
	{
		return code;
	}

	public void setCode( String value )
	{
		this.code = value;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc( String value )
	{
		this.desc = value;
	}

}
