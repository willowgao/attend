package com.wgsoft.system.model;
// default package



/**
 * DataDictionaryId entity. @author MyEclipse Persistence Tools
 */

public class DataDictionary  implements java.io.Serializable {


    // Fields    

     private String datatype;
     private String comments;
     private String dataid;
     private String datacomment;


    // Constructors

    /** default constructor */
    public DataDictionary() {
    }

    
    /** full constructor */
    public DataDictionary(String datatype, String comments, String dataid, String datacomment) {
        this.datatype = datatype;
        this.comments = comments;
        this.dataid = dataid;
        this.datacomment = datacomment;
    }

   
    // Property accessors

    public String getDatatype() {
        return this.datatype;
    }
    
    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getComments() {
        return this.comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDataid() {
        return this.dataid;
    }
    
    public void setDataid(String dataid) {
        this.dataid = dataid;
    }

    public String getDatacomment() {
        return this.datacomment;
    }
    
    public void setDatacomment(String datacomment) {
        this.datacomment = datacomment;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DataDictionary) ) return false;
		 DataDictionary castOther = ( DataDictionary ) other; 
         
		 return ( (this.getDatatype()==castOther.getDatatype()) || ( this.getDatatype()!=null && castOther.getDatatype()!=null && this.getDatatype().equals(castOther.getDatatype()) ) )
 && ( (this.getComments()==castOther.getComments()) || ( this.getComments()!=null && castOther.getComments()!=null && this.getComments().equals(castOther.getComments()) ) )
 && ( (this.getDataid()==castOther.getDataid()) || ( this.getDataid()!=null && castOther.getDataid()!=null && this.getDataid().equals(castOther.getDataid()) ) )
 && ( (this.getDatacomment()==castOther.getDatacomment()) || ( this.getDatacomment()!=null && castOther.getDatacomment()!=null && this.getDatacomment().equals(castOther.getDatacomment()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDatatype() == null ? 0 : this.getDatatype().hashCode() );
         result = 37 * result + ( getComments() == null ? 0 : this.getComments().hashCode() );
         result = 37 * result + ( getDataid() == null ? 0 : this.getDataid().hashCode() );
         result = 37 * result + ( getDatacomment() == null ? 0 : this.getDatacomment().hashCode() );
         return result;
   }   





}