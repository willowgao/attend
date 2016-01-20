package com.wgsoft.system.model;
// default package



/**
 * UserMenuId entity. @author MyEclipse Persistence Tools
 */

public class UserMenuId  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -1459833705380352477L;
	private String menuid;
     private String userid;


    // Constructors

    /** default constructor */
    public UserMenuId() {
    }

    
    /** full constructor */
    public UserMenuId(String menuid, String userid) {
        this.menuid = menuid;
        this.userid = userid;
    }

   
    // Property accessors

    public String getMenuid() {
        return this.menuid;
    }
    
    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getUserid() {
        return this.userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof UserMenuId) ) return false;
		 UserMenuId castOther = ( UserMenuId ) other; 
         
		 return ( (this.getMenuid()==castOther.getMenuid()) || ( this.getMenuid()!=null && castOther.getMenuid()!=null && this.getMenuid().equals(castOther.getMenuid()) ) )
 && ( (this.getUserid()==castOther.getUserid()) || ( this.getUserid()!=null && castOther.getUserid()!=null && this.getUserid().equals(castOther.getUserid()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getMenuid() == null ? 0 : this.getMenuid().hashCode() );
         result = 37 * result + ( getUserid() == null ? 0 : this.getUserid().hashCode() );
         return result;
   }   





}