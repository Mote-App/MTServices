package com.cl.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cl.models.repository.PostRepository;

import views.NewPostDto;

@Controller
public class FileUploadController {

	@Autowired PostRepository _postRepo;
		
	
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

	@RequestMapping(value="/upload_post", method=RequestMethod.POST)
	public @ResponseBody String handlePostUpload(@RequestParam("new_post_dto") NewPostDto newPostDto,
            @RequestParam("file") MultipartFile file){
		
		if (!file.isEmpty()) {
        	String path = "/var/www/html/img/posts/";
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
		
		return null;
	}
	
}



