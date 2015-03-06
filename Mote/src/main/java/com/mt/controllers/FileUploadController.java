package com.mt.controllers;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mt.exception.ClException;
import com.mt.models.Post;
import com.mt.models.PostCustomTags;
import com.mt.models.PostTags;
import com.mt.models.User;
import com.mt.models.dao.UserDao;
import com.mt.models.repository.PostCustomTagsRepository;
import com.mt.models.repository.PostRepository;
import com.mt.models.repository.PostTagsRepository;

import views.NewPostDto;

@Controller
public class FileUploadController {

	@Autowired
	PostTagsRepository _postTagsRepository;
	
	@Autowired
	PostCustomTagsRepository _postCustomTagsRepository; 
	
	@Autowired 
	PostRepository _postRepo;
	
	@Autowired
	UserDao	_userDao;
	
		
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file) throws ClException{
        if (!file.isEmpty()) {
        	String path = "/var/www/html/img/profiles/";
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(path + name + ".jpg")));
                stream.write(bytes);
                stream.close();
                
                User user = _userDao.getById(Long.parseLong(name));
                user.setProfilePictureUrl("img/profiles/" + name + ".jpg");
                _userDao.update(user);
                
                return "You successfully uploaded " + name;
            } catch (Exception e) {
               throw new ClException("Failed to upload profile image", e.getMessage() + e.getCause());
            }
        } else {
        	throw new ClException("You failed to upload " + name + " because the file was empty.","User Defined message");
        }
    }

	@Transactional
	@RequestMapping(value="/upload_post", method=RequestMethod.POST)
	public @ResponseBody String handlePostUpload(@RequestParam("post") String newPostDtoStr,
            @RequestParam("file") MultipartFile file) throws ClException{
		
		String fileName="";
		
		if (!file.isEmpty()) {
        	String path = "/var/www/html/img/posts/";
			//String path = "c://developer//research//";
        	try {
        
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
            	
            	
            	fileName = post.getUserId() + "_" + post.getId() + ".jpg";
            	
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(path + fileName)));
                stream.write(bytes);
                stream.close();
                post.setPostImgPath("img/posts/" + fileName);
                _postRepo.save(post);
                
                return "You successfully uploaded " + fileName;
            } catch (Exception e) {
            	throw new ClException("Failed to upload post image", e.getMessage() + e.getCause());
            }
        } else {
        	throw new ClException("You failed to upload " + fileName + " because the file was empty.","User Defined message");
        }
		
	}
	
}



