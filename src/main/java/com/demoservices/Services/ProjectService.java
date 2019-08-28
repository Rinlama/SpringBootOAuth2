package com.demoservices.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demoservices.Model.Projects;
import com.demoservices.Repository.ProjectRepository;

@Service
public class ProjectService {
  
	@Autowired
	ProjectRepository projectRepository;
	
	public List<Projects> getAllProject(){
		List<Projects> list=new ArrayList();
		projectRepository.findAll().forEach(p->{
			list.add(p);
		});
		
		return list;
	}
	
	public Optional<Projects> getProjectById(String id){
		return projectRepository.findById(id);
	}
	
	public Projects AddProjects(Projects project){
		return projectRepository.save(project);
	}
	
	public Projects UpdateProjects(Projects project){
		Optional<Projects> p=projectRepository.findById(project.getProjectId());
		if(p!=null){
			Projects temp=new Projects();
			temp.setProjectId(project.getProjectId());
			temp.setProjectName(project.getProjectName());
			temp.setConsultantName(project.getConsultantName());
			temp.setDescription(project.getDescription());
			temp.setPdf(p.get().getPdf());
			temp.setPdfName(p.get().getPdfName());
			temp.setVideos(p.get().getVideos());
			temp.setVideosName(p.get().getVideosName());
			temp.setLogo(p.get().getLogo());
			temp.setLogoName(p.get().getLogoName());
			temp.setZip(p.get().getZip());
			temp.setZipName(p.get().getZipName());
			temp.setVideosLink(project.getVideosLink());
			
			return projectRepository.save(temp);
		}else{
			return null;
		}
	}

	public Page<Projects> GetAllProjectsByPagebale(Pageable page){
		return projectRepository.findAll(page);
	}
	
	public String RemoveProjects(String projectid){
		 projectRepository.deleteById(projectid);
		 return projectid;
	}
	
	public List<Projects> searchProjects(String search){
		 return projectRepository.searchresultIgnoreCase(search);
		
	}
	
	
}
