package com.wgsoft.performance.model;

import com.wgsoft.common.model.BaseVO;

// default package

/**
 * PerformanceIndexId entity. @author MyEclipse Persistence Tools
 */

public class PerformanceIndex extends BaseVO implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2452213520215793416L;
	private String indexid;
	private String item;
	private String itemDetail;
	private String indexContent;
	private Double indexScore;
	private Double assessScore;

	// Constructors

	/** default constructor */
	public PerformanceIndex() {
	}

	/** full constructor */
	public PerformanceIndex(String indexid, String item, String itemDetail, String indexContent, Double indexScore) {
		this.indexid = indexid;
		this.item = item;
		this.itemDetail = itemDetail;
		this.indexContent = indexContent;
		this.indexScore = indexScore;
	}

	// Property accessors

	public Double getAssessScore() {
		return assessScore;
	}

	public void setAssessScore(Double assessScore) {
		this.assessScore = assessScore;
	}

	public String getIndexid() {
		return this.indexid;
	}

	public void setIndexid(String indexid) {
		this.indexid = indexid;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getItemDetail() {
		return this.itemDetail;
	}

	public void setItemDetail(String itemDetail) {
		this.itemDetail = itemDetail;
	}

	public String getIndexContent() {
		return this.indexContent;
	}

	public void setIndexContent(String indexContent) {
		this.indexContent = indexContent;
	}

	public Double getIndexScore() {
		return this.indexScore;
	}

	public void setIndexScore(Double indexScore) {
		this.indexScore = indexScore;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PerformanceIndex))
			return false;
		PerformanceIndex castOther = (PerformanceIndex) other;

		return ((this.getIndexid() == castOther.getIndexid()) || (this.getIndexid() != null
				&& castOther.getIndexid() != null && this.getIndexid().equals(castOther.getIndexid())))
				&& ((this.getItem() == castOther.getItem()) || (this.getItem() != null && castOther.getItem() != null && this
						.getItem().equals(castOther.getItem())))
				&& ((this.getItemDetail() == castOther.getItemDetail()) || (this.getItemDetail() != null
						&& castOther.getItemDetail() != null && this.getItemDetail().equals(castOther.getItemDetail())))
				&& ((this.getIndexContent() == castOther.getIndexContent()) || (this.getIndexContent() != null
						&& castOther.getIndexContent() != null && this.getIndexContent().equals(
						castOther.getIndexContent())))
				&& ((this.getIndexScore() == castOther.getIndexScore()) || (this.getIndexScore() != null
						&& castOther.getIndexScore() != null && this.getIndexScore().equals(castOther.getIndexScore())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getIndexid() == null ? 0 : this.getIndexid().hashCode());
		result = 37 * result + (getItem() == null ? 0 : this.getItem().hashCode());
		result = 37 * result + (getItemDetail() == null ? 0 : this.getItemDetail().hashCode());
		result = 37 * result + (getIndexContent() == null ? 0 : this.getIndexContent().hashCode());
		result = 37 * result + (getIndexScore() == null ? 0 : this.getIndexScore().hashCode());
		return result;
	}

}