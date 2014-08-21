package com.lu.webservice.cxf;

import java.util.List;
import javax.jws.WebService;

@WebService(endpointInterface = "com.lu.webservice.cxf.IHelloServices")
public class HelloServicesImpl implements IHelloServices
{

	public String sayHello(String name)
	{
		return "Hello " + name + " .";
	}

	public String sayHelloToAll(List<UserInfo> users)
	{
		String hello = "hello ";
		for (UserInfo user : users)
		{
			hello += user.getName() + " ,";
		}
		hello += " ,everybody.";
		return hello;
	}

	@Override
	public String request(String request)
	{
		String response = "{\n"
				+ "  \"cmd\": \"segTrackDetailData\",\n"
				+ "  \"result\": 0,\n"
				+ "  \"note\": \"SUCCESS\",\n"
				+ "  \"metadata\": [\"lng\", \"lat\", \"time\", \"speed\", \"posMethod\", \"onlineStatus\"],\n"
				+ "  \"pointdata\": [[119.474327, 32.486519, \"2014-04-20 15:41:24\", 60, 1, 2], [119.474327, 32.486519, \"2014-04-20 15:42:24\", 60, 1, 2], [119.474327, 32.486519, \"2014-04-20 15:43:24\", 60, 1, 2]]\n"
				+ "}";
		return response;
	}

}
