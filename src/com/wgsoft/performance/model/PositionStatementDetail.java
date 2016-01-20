package com.wgsoft.performance.model;
// default package



/**
 * PositionStatementDetailId entity. @author MyEclipse Persistence Tools
 */

public class PositionStatementDetail  implements java.io.Serializable {


    // Fields    

     private String ssid;
     private String psid;
     private String statements;
     private String standards;
     private String comments;


    // Constructors

    /** default constructor */
    public PositionStatementDetail() {
    }

	/** minimal constructor */
    public PositionStatementDetail(String ssid, String psid) {
        this.ssid = ssid;
        this.psid = psid;
    }
    
    /** full constructor */
    public PositionStatementDetail(String ssid, String psid, String statements, String standards, String comments) {
        this.ssid = ssid;
        this.psid = psid;
        this.statements = statements;
        this.standards = standards;
        this.comments = comments;
    }

   
    // Property accessors

    public String getSsid() {
        return this.ssid;
    }
    
    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getPsid() {
        return this.psid;
    }
    
    public void setPsid(String psid) {
        this.psid = psid;
    }

    public String getStatements() {
        return this.statements;
    }
    
    public void setStatements(String statements) {
        this.statements = statements;
    }

    public String getStandards() {
        return this.standards;
    }
    
    public void setStandards(String standards) {
        this.standards = standards;
    }

    public String getComments() {
        return this.comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PositionStatementDetail) ) return false;
		 PositionStatementDetail castOther = ( PositionStatementDetail ) other; 
         
		 return ( (this.getSsid()==castOther.getSsid()) || ( this.getSsid()!=null && castOther.getSsid()!=null && this.getSsid().equals(castOther.getSsid()) ) )
 && ( (this.getPsid()==castOther.getPsid()) || ( this.getPsid()!=null && castOther.getPsid()!=null && this.getPsid().equals(castOther.getPsid()) ) )
 && ( (this.getStatements()==castOther.getStatements()) || ( this.getStatements()!=null && castOther.getStatements()!=null && this.getStatements().equals(castOther.getStatements()) ) )
 && ( (this.getStandards()==castOther.getStandards()) || ( this.getStandards()!=null && castOther.getStandards()!=null && this.getStandards().equals(castOther.getStandards()) ) )
 && ( (this.getComments()==castOther.getComments()) || ( this.getComments()!=null && castOther.getComments()!=null && this.getComments().equals(castOther.getComments()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getSsid() == null ? 0 : this.getSsid().hashCode() );
         result = 37 * result + ( getPsid() == null ? 0 : this.getPsid().hashCode() );
         result = 37 * result + ( getStatements() == null ? 0 : this.getStatements().hashCode() );
         result = 37 * result + ( getStandards() == null ? 0 : this.getStandards().hashCode() );
         result = 37 * result + ( getComments() == null ? 0 : this.getComments().hashCode() );
         return result;
   }   





}