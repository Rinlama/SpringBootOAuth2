package com.demoservices.Model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
//@Data
//@ToString
//@RequiredArgsConstructor
//@NoArgsConstructor
//@AllArgsConstructor
@Table(name="Projects")
public class Projects {
	@Id()
	private String projectId;
	private String projectName;
	private String consultantName;
	private String logo;
	private String videos;
	private String zip;
	private String pdf;
	private String logoName;
	private String videosName;
	private String zipName;
	private String pdfName;
	@Column(length=500)
	private String description;
	
	@Column(length=500)
	private String videosLink;
	
	public Projects() {	super();
	this.projectId=UUID.randomUUID().toString();
		// TODO Auto-generated constructor stub
	}
	public Projects(String projectName, String consultantName, String videos, String zip, String pdf) {
		super();
		this.projectId = this.projectId=UUID.randomUUID().toString();
		this.projectName = projectName;
		this.consultantName = consultantName;
		this.videos = videos;
		this.zip = zip;
		this.pdf = pdf;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getConsultantName() {
		return consultantName;
	}
	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}
	public String getVideos() {
		return videos;
	}
	public void setVideos(String videos) {
		this.videos = videos;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPdf() {
		return pdf;
	}
	public void setPdf(String pdf) {
		this.pdf = pdf;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getLogoName() {
		return logoName;
	}
	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}
	public String getVideosName() {
		return videosName;
	}
	public void setVideosName(String videosName) {
		this.videosName = videosName;
	}
	public String getZipName() {
		return zipName;
	}
	public void setZipName(String zipName) {
		this.zipName = zipName;
	}
	public String getPdfName() {
		return pdfName;
	}
	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}
	public String getVideosLink() {
		return videosLink;
	}
	public void setVideosLink(String videosLink) {
		this.videosLink = videosLink;
	}
	
	
}
