package com.cl.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cl.models.Post;
import com.cl.models.PostCustomTags;
import com.cl.models.PostTags;
import com.cl.models.repository.PostCustomTagsRepository;
import com.cl.models.repository.PostRepository;
import com.cl.models.repository.PostTagsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import views.NewPostDto;

@Controller
public class FileUploadController {

	@Autowired
	EntityManager _entityManager;
	
	@Autowired
	PostTagsRepository _postTagsRepository;
	
	@Autowired
	PostCustomTagsRepository _postCustomTagsRepository; 
	
	@Autowired 
	PostRepository _postRepo;
		
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
        	String path = "/var/www/html/img/profiles/";
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(path + name)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + " into " + "/var/www/html/img/profiles/";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }

	@Transactional
	@RequestMapping(value="/upload_post", method=RequestMethod.POST)
	public @ResponseBody String handlePostUpload(@RequestParam("post") String newPostDtoStr,
            @RequestParam("file") MultipartFile file){
		
		String fileName="";
		
		
		
		if (!file.isEmpty()) {
        	String path = "/var/www/html/img/posts/";
			//String path = "c://developer//research//";
        	try {
        
        		//_entityManager.getTransaction().begin();
        		
        		ObjectMapper mapper = new ObjectMapper();
        		NewPostDto newPostDto =  mapper.readValue(newPostDtoStr,NewPostDto.class);
        		
        		
            	Post post = new Post();
            	post.setUserId(newPostDto.getUserId());
            	post.setCaption(newPostDto.getCaption());
            	post.setLikes(0);
            	post.setPostDate( Calendar.getInstance());
            	post.setPostImgPath("dummy");
            	
            	_postRepo.save(post);
            	
            	List<PostTags> lstPostTags = new ArrayList<PostTags>();
            	
            	for (int i=0; i < newPostDto.getTags().size(); i++){
            		
            		PostTags postTag = new PostTags();
            		postTag.setPostId(post.getId());
            		postTag.setTagId(newPostDto.getTags().get(i));
            		
            		_postTagsRepository.save(postTag);
            		//_postTagsRepository.save(postTag);
            		
            		//lstPostTags.add(postTag);
            	}
            	
            	//post.setLstPostTags(lstPostTags);
            	
            	
            	String csvCustomTags = newPostDto.getCustomTags();
            	if(csvCustomTags != null){
            		
            		List<PostCustomTags> lstPostCustomTags = new ArrayList<PostCustomTags>();
            		
            		String arr[] = csvCustomTags.split(",");
            		
            		for (int i=0; i < arr.length; i++){
            			PostCustomTags postCustomTag = new PostCustomTags();
            			
            			postCustomTag.setPostId(post.getId());
            			postCustomTag.setUserId(newPostDto.getUserId());
            			postCustomTag.setTagName(arr[i]);
            			
            			_postCustomTagsRepository.save(postCustomTag);
            			//_postCustomTagsRepository.save(postCustomTag);
            			//lstPostCustomTags.add(postCustomTag);
            		}
            		
            		//post.setLstPostCustomTags(lstPostCustomTags);
            	}
            	
            	//post = _postRepo.save(post);
            	
            	//_entityManager.getTransaction().commit();
            	
            	fileName = post.getUserId() + "_" + post.getId() + ".jpeg";
            	
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(path + fileName)));
                stream.write(bytes);
                stream.close();
                post.setPostImgPath("img/posts/" + fileName);
                _postRepo.save(post);
                
                return "You successfully uploaded " + fileName + " into " + "/var/www/html/img/posts/";
            } catch (Exception e) {
                return "You failed to upload " + fileName + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + fileName + " because the file was empty.";
        }
		
	}
	
}



