package com.wgsoft.system.model;



/**
 * RoleMenuId entity. @author MyEclipse Persistence Tools
 */

public class RoleMenuId  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -4056764968867203658L;
	private String menuid;
     private String roleid;


    // Constructors

    /** default constructor */
    public RoleMenuId() {
    }

    
    /** full constructor */
    public RoleMenuId(String menuid, String roleid) {
        this.menuid = menuid;
        this.roleid = roleid;
    }

   
    // Property accessors

    public String getMenuid() {
        return this.menuid;
    }
    
    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getRoleid() {
        return this.roleid;
    }
    
    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof RoleMenuId) ) return false;
		 RoleMenuId castOther = ( RoleMenuId ) other; 
         
		 return ( (this.getMenuid()==castOther.getMenuid()) || ( this.getMenuid()!=null && castOther.getMenuid()!=null && this.getMenuid().equals(castOther.getMenuid()) ) )
 && ( (this.getRoleid()==castOther.getRoleid()) || ( this.getRoleid()!=null && castOther.getRoleid()!=null && this.getRoleid().equals(castOther.getRoleid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getMenuid() == null ? 0 : this.getMenuid().hashCode() );
         result = 37 * result + ( getRoleid() == null ? 0 : this.getRoleid().hashCode() );
         return result;
   }   





}