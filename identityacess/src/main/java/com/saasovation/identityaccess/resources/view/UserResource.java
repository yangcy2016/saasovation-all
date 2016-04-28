package com.saasovation.identityaccess.resources.view;

import com.saasovation.identityaccess.application.AccessService;
import com.saasovation.identityaccess.domain.model.identity.User;
import com.saasovation.identityaccess.resources.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


@RequestMapping(path="/tenants/{tenantId}/users")
@Controller
public class UserResource {
	
	@Autowired
	private AccessService accessService;
	
	@RequestMapping(path="/{username}/inRole/{role}",method=RequestMethod.GET)
	@ResponseBody
	public Object getUserInRole(@PathVariable("tenantId")String tenantId,
								@PathVariable("username")String username,
								@PathVariable("role")String role){
		User user = null;
		user = accessService.userInRole(tenantId, username, role);
		Response resp = new Response();
		if(user != null){
			resp.setData((Object)user);
		}
		resp.setErrorCode("000000");
		resp.setErrorMsg("success");
		resp.setRespDate(new Date());
		return resp;
	}
	
}
