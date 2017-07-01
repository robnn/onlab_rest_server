package onlab.rest.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import onlab.rest.spring.dao.*;
import onlab.rest.spring.entities.*;
import org.springframework.stereotype.Component;

@RestController
@Component
public class Controller {
	
	@Autowired
	 InstituteRepository instituteRepository;

	@Autowired
	 UserRepository userRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
	// ------------------------ USER SECTION -----------------------------------------------------
	@RequestMapping("/user/")
	ResponseEntity<Iterable<User>> getAllUsers(){
		if(userRepository.count()==0)
            return new ResponseEntity<Iterable<User>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Iterable<User>>(userRepository.findAll(), HttpStatus.OK);
	}
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
	@RequestMapping(value = "/user/byname/{username}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> getUser(@PathVariable("username") String userName) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
	@RequestMapping(value = "/user/idbyname/{username}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Long> getUserIdByName(@PathVariable("username") String userName) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return new ResponseEntity<Long>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Long>(user.getId(), HttpStatus.OK);
    }
	@RequestMapping(value = "/user/instituteidbyname/{username}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Long> getInstituteIdByName(@PathVariable("username") String userName) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return new ResponseEntity<Long>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Long>(user.getInstituteId(), HttpStatus.OK);
    }
	@RequestMapping(value = "/user/namebyid/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> getNameById(@PathVariable("id") long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(user.getUserName(), HttpStatus.OK);
    }
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
  
        if (userRepository.exists(user.getId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        user.setRole("ROLE_USER");
        userRepository.save(user);
        User newUser = userRepository.findByUserName(user.getUserName()); 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(newUser.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	// ------------------------ INSTITUTE SECTION -----------------------------------------------------
	@RequestMapping("/institute/")
	public ResponseEntity<Iterable<Institute>> getAllInstitutes(){	
		if(instituteRepository.count()==0)
            return new ResponseEntity<Iterable<Institute>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Iterable<Institute>>(instituteRepository.findAll(), HttpStatus.OK);
	}
	@RequestMapping(value="/institute/", method=RequestMethod.POST)
	public ResponseEntity<Void> addInstitute(@RequestBody Institute institute, UriComponentsBuilder ucBuilder){
		instituteRepository.save(institute);
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/institute/{id}").buildAndExpand(institute.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@RequestMapping(value = "/institute/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Institute> getInstitute(@PathVariable("id") Long id) {
        Institute institute = instituteRepository.findOne(id);
        if (institute == null) {
            return new ResponseEntity<Institute>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Institute>(institute, HttpStatus.OK);
    }
	@RequestMapping(value = "/institute/byname/{institutename}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Institute> getInstitute(@PathVariable("institutename") String instituteName) {
        Institute institute = instituteRepository.findByName(instituteName);
        if (institute == null) {
            return new ResponseEntity<Institute>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Institute>(institute, HttpStatus.OK);
    }
	// ------------------------ SUBJECT SECTION -----------------------------------------------------
	@RequestMapping("/subject/")
	public ResponseEntity<Iterable<Subject>> getAllSubjects(){	
		if(subjectRepository.count()==0)
            return new ResponseEntity<Iterable<Subject>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Iterable<Subject>>(subjectRepository.findAll(), HttpStatus.OK);
	}
	@RequestMapping(value="/subject/", method=RequestMethod.POST)
	public ResponseEntity<Void> addSubject(@RequestBody Subject subject, UriComponentsBuilder ucBuilder){
		subjectRepository.save(subject);
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/subject/{id}").buildAndExpand(subject.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@RequestMapping(value = "/subject/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Subject> getSubject(@PathVariable("id") Long id) {
        Subject subject = subjectRepository.findOne(id);
        if (subject == null) {
            return new ResponseEntity<Subject>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Subject>(subject, HttpStatus.OK);
    }
	@RequestMapping(value = "/subject/byname/{subjectname}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Subject> getSubject(@PathVariable("subjectname") String subjectName) {
        Subject subject = subjectRepository.findByName(subjectName);
        if (subject == null) {
            return new ResponseEntity<Subject>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Subject>(subject, HttpStatus.OK);
    }
	@RequestMapping(value = "/subject/byinstitute/{institutename}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Subject>> getSubjectByInstitute(@PathVariable("institutename") String instituteName) {
		Long id = instituteRepository.findByName(instituteName).getId();
        List<Subject> subject = subjectRepository.findByInstituteId(id);
        if (subject == null) {
            return new ResponseEntity<List<Subject>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Subject>>(subject, HttpStatus.OK);
    }
	// ------------------------ COMMENT SECTION -----------------------------------------------------
	@RequestMapping("/comment/")
	public ResponseEntity<Iterable<Comment>> getAllComments(){	
		if(commentRepository.count()==0)
            return new ResponseEntity<Iterable<Comment>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Iterable<Comment>>(commentRepository.findAll(), HttpStatus.OK);
	}
	@RequestMapping(value="/comment/", method=RequestMethod.POST)
	public ResponseEntity<Void> addComment(@RequestBody Comment comment, UriComponentsBuilder ucBuilder){
		commentRepository.save(comment);
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/comment/{id}").buildAndExpand(comment.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@RequestMapping(value = "/comment/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Comment> getComment(@PathVariable("id") Long id) {
        Comment comment = commentRepository.findOne(id);
        if (comment == null) {
            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }
	@RequestMapping(value = "/comment/bysubject/{subjectname}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Comment>> getComment(@PathVariable("subjectname") String subjectName) {
		Long id = subjectRepository.findByName(subjectName).getId();
        List<Comment> comment = commentRepository.findBySubjectId(id);
        if (comment == null) {
            return new ResponseEntity<List<Comment>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Comment>>(comment, HttpStatus.OK);
    }
}
