package com.mt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import views.TagDto;

import com.mt.models.College;
import com.mt.models.dao.CollegeDao;
import com.mt.models.dao.TagDao;

/**
 * The <code>StaticDataController</code> ...
 * 
 * @author gibranecastillo
 *
 */
@Controller
public class StaticDataController {
	@Autowired
	private CollegeDao _collegeDao;
	
	@Autowired
	private TagDao _tagDao;
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/colleges", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<College> getColleges() {
		List<College> collegeLst = _collegeDao.getAll(); 
		
		return collegeLst;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value="/tags", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public TagDto getTags() {
		TagDto tagDto = new TagDto();
		/*tagDto.setSmarts(_tagDao.getTags("smart"));
		tagDto.setSocials(_tagDao.getTags("social"));
		tagDto.setGenre(_tagDao.getTags("genre"));*/
		tagDto.setTags(_tagDao.getTags());
		
		return tagDto;
	}
}