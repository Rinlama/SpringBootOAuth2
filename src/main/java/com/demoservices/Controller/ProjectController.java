package com.demoservices.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.demoservices.FileStorage.FileStorageService;
import com.demoservices.FileStorage.UploadFileResponse;
import com.demoservices.Model.Projects;
import com.demoservices.Services.ProjectService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class ProjectController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	FileStorageService fileStorageService;

	@RequestMapping(value="/projects",method=RequestMethod.GET)
	public List<Projects> getAllProjects(){	   
		logger.debug("Debugging log");
		return projectService.getAllProject();
	}
	@RequestMapping(value="/projects/{projectid}",method=RequestMethod.GET)
	public Optional<Projects> getProjects(@PathVariable("projectid")String id){
		return projectService.getProjectById(id);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/projectsbypageable")
	public Page<Projects> getAllArtistByPageable(org.springframework.data.domain.Pageable page){	
		return projectService.GetAllProjectsByPagebale(page);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/search/{searchvalue}")
	public List<Projects> searchResult(@PathVariable("searchvalue") String searchvalue){
		return  projectService.searchProjects(searchvalue);
	}
	
	
	@RequestMapping(method=RequestMethod.DELETE,value="/deleteprojects/{projectid}")
	public ResponseEntity<?> deleteproject(@PathVariable("projectid")String projectid){
		
		//find file to delete
		try{
			if(!projectService.getProjectById(projectid).equals(null)){
				Optional<Projects> project=projectService.getProjectById(projectid);
				try{
					fileStorageService.deleteFile(project.get().getLogoName());
				}catch(Exception ex){}
				try{
					fileStorageService.deleteFile(project.get().getVideosName());
				}catch(Exception ex){}
				try{
					fileStorageService.deleteFile(project.get().getPdfName());
				}catch(Exception ex){}
				try{
					fileStorageService.deleteFile(project.get().getZipName());
				}catch(Exception ex){}		
			}
		}catch(Exception ex){}
	
		
		projectService.RemoveProjects(projectid);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("content-type", "application/json");
		
		HashMap<String, String> map=new HashMap();
		map.put("projectid", projectid);
		
	   return ResponseEntity.ok().headers(responseHeaders)
			   .body(map);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/updateprojects/{projectid}")
	public Projects update(@RequestBody Projects project){	
		return projectService.UpdateProjects(project);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/addprojects")
	public Projects fileupload(@RequestHeader("Authorization") String encoding,@RequestParam("name") String name,@RequestParam("consultantName") 
	String consultantName,@RequestParam("description") String description,
	@RequestParam("videoslink") String videosLink
	,@RequestParam(value="logo",required=false) MultipartFile logo,
	@RequestParam(value="zip",required=false) MultipartFile zip,
	@RequestParam(value="pdf",required=false) MultipartFile pdf,
	@RequestParam(value="videos",required=false) MultipartFile videos){
		
	
		    String filelogopath=uploadFileServiceController(logo);
		    String filezippath=uploadFileServiceController(zip);
		    String filepdfpath=uploadFileServiceController(pdf);
		    String filevideospath=uploadFileServiceController(videos);
		    
		    String logopath=filelogopath.split(",")[0];
		    String zippath=filezippath.split(",")[0];
		    String pdfpath=filepdfpath.split(",")[0];
		    String vidoespath=filevideospath.split(",")[0];
		    
		    String logoName=filelogopath.split(",")[1];
		    String zipName=filezippath.split(",")[1];
		    String pdfName=filepdfpath.split(",")[1];
		    String videosName=filevideospath.split(",")[1];
		
		    logger.debug("Debugging log");
			 
	        Projects project= new Projects();
	        project.setProjectName(name);
	        project.setConsultantName(consultantName);
	        project.setDescription(description);
	        project.setVideosLink(videosLink);
	        
	        project.setLogo(logopath);
	        project.setZip(zippath);
	        project.setPdf(pdfpath);
	        project.setVideos(vidoespath);
	        
	        project.setLogoName(logoName);
	        project.setPdfName(pdfName);
	        project.setVideosName(videosName);
	        project.setZipName(zipName);
	        
	        return projectService.AddProjects(project);
	}
	
	public String uploadFileServiceController(MultipartFile file){
		  if(file!= null){
			  String logoName = fileStorageService.storeFile(file);
		      String fileViewUri=ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploads/").path(logoName).toUriString();
		      
		      return fileViewUri + "," + logoName;
		  }else{
			  return " , ";
		  }
	}
	
	
	
}
