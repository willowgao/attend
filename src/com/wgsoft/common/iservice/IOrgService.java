package com.wgsoft.common.iservice;

import java.util.List;

import com.wgsoft.common.model.Organization;

public interface IOrgService {

	List<Organization> getOrgs(String orgId);
}
