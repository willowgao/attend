package com.wgsoft.system.model;



/**
 * RoleMenu entity. @author MyEclipse Persistence Tools
 */

public class RoleMenu  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 2583031887756067804L;

	private RoleMenuId id;

     private String menuid;
     private String roleid;
     private String roleorg;


    // Constructors

    /** default constructor */
    public RoleMenu() {
    }

	/** minimal constructor */
    public RoleMenu(RoleMenuId id) {
        this.id = id;
    }
    
    /** full constructor */
    public RoleMenu(RoleMenuId id, String roleorg) {
        this.id = id;
        this.roleorg = roleorg;
    }
    

   
    // Property accessors

    public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public RoleMenuId getId() {
        return this.id;
    }
    
    public void setId(RoleMenuId id) {
        this.id = id;
    }

    public String getRoleorg() {
        return this.roleorg;
    }
    
    public void setRoleorg(String roleorg) {
        this.roleorg = roleorg;
    }
   








}