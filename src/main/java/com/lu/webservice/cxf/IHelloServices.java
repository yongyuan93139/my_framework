package com.lu.webservice.cxf;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface IHelloServices
{
	public String sayHello(String name);

	public String sayHelloToAll(List<UserInfo> users);

	public String request(String request);
}
