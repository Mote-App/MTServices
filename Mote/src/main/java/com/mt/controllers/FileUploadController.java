package com.mt.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import views.NewPostDto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mt.exception.MtException;
import com.mt.models.Post;
import com.mt.models.PostTags;
import com.mt.models.User;
import com.mt.models.dao.UserDao;
import com.mt.models.repository.PostRepository;
import com.mt.models.repository.PostTagsRepository;

/**
 * The <code>FileUploadController</code> ...
 * 
 * @author gibranecastillo
 *
 */
@Controller
public class FileUploadController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PostTagsRepository _postTagsRepository;
	
	@Autowired 
	PostRepository _postRepo;
	
	@Autowired
	UserDao	_userDao;
	
	/**
	 * 
	 * 
	 * @param name
	 * @param file
	 * @return
	 * @throws MtException
	 */
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public @ResponseBody String handleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) throws MtException {
		if(!file.isEmpty()) {
			String path = "/var/www/html/img/profiles/";
			
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path + name + ".jpg")));
				stream.write(bytes);
				stream.close();
				
				User user = _userDao.getById(Long.parseLong(name));
				user.setProfilePictureUrl("img/profiles/" + name + ".jpg");
				_userDao.update(user);
				
				return "You successfully uploaded " + name;
			} catch(Exception e) {
				throw new MtException("Failed to upload profile image", e.getMessage() + e.getCause());
			}
		} else {
			throw new MtException("You failed to upload " + name + " because the file was empty.","User Defined message");
		}
	}
	
	/**
	 * 
	 * @param newPostDtoStr
	 * @param file
	 * @return
	 * @throws MtException
	 */
	@Transactional
	@RequestMapping(value="/upload_post", method=RequestMethod.POST)
	public @ResponseBody String handlePostUpload(@RequestParam("post") String newPostDtoStr, @RequestParam("file") MultipartFile file) throws MtException {
		String fileName="";
		
		if(!file.isEmpty()) {
			String path = "/var/www/html/img/posts/";
			//String path = "c://developer//research//";
			try {
				log.info("New Post Parameter :" + newPostDtoStr);
				
				ObjectMapper mapper = new ObjectMapper();
				NewPostDto newPostDto =  mapper.readValue(newPostDtoStr,NewPostDto.class);
				
				Post post = new Post();
				post.setPostTypeCode(newPostDto.getPostType());
				post.getProfile().setProfileId(newPostDto.getUserId());
				post.setPostCaption(newPostDto.getCaption());
				post.setPostDate( Calendar.getInstance());
				post.setPostObjectPath("dummy");
				
				_postRepo.save(post);
				
				for(int i = 0; i < newPostDto.getTags().size(); i++) {
					PostTags postTag = new PostTags();
					postTag.setPostId(post.getPostId());
					postTag.setTagId(newPostDto.getTags().get(i));
					
					_postTagsRepository.save(postTag);
				}
				
				//post = _postRepo.save(post);
				fileName = post.getProfile().getProfileId() + "_" + post.getPostId() + ".png";
				
				byte[] bytes = file.getBytes();
				
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path + fileName)));
				stream.write(bytes);
				stream.close();
				
				post.setPostObjectPath("img/posts/" + fileName);
				
				_postRepo.save(post);
				
				return "You successfully uploaded " + fileName;
			} catch(Exception e) {
				//e.printStackTrace();
				throw new MtException("Failed to upload post image", e);
			}
		} else {
			throw new MtException("You failed to upload " + fileName + " because the file was empty.","User Defined message");
		}
	}
}