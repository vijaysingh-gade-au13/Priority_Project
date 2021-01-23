package com.tatsam.contrllor;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tatsam.model.Priority;
import com.tatsam.service.PriorityService;
import com.tatsam.util.CustomErrorType;


@RestController
public class PriorityController {

	public static final Logger logger = LoggerFactory.getLogger(PriorityController.class);
	
	@Autowired
	private PriorityService priorityService;
	

	//----------CreatePriority/Area API-------------------------------------------------------------------------------
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/savePriorityData",method = RequestMethod.POST) 
    public ResponseEntity<?> createPriority(@RequestBody Priority priority, UriComponentsBuilder ucBuilder) {
        
    	logger.info("createPriority Method Starts...");
        Priority priorityObj = null;
    	System.out.println("enter.......................");
        HttpHeaders headers= null;
        try {
	        if (priorityService.isPriorityDataExist(priority)) {
	            logger.error("Unable to create. A Area with id {} already exist", priority.getPriorityId());
	            return new ResponseEntity(new CustomErrorType("Unable to create Area. A Area with id " + 
	            		priority.getPriorityId() + " already exist."),HttpStatus.CONFLICT);
	        }
	        
	        priorityService.savePriorityData(priority);
	        
	        headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/api/priority/{id}").buildAndExpand(priority.getPriorityId()).toUri());
        } catch(Exception ex) {
        	logger.error("Exception in method savePriorityData Method",ex.getMessage());
        	ex.printStackTrace();
        }
        logger.info("createPriority Method Ends...");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    
    //------------- listALLPriorityData/AreaData API-------------------------------------------------------------------- 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getPriorityData",method = RequestMethod.GET)
    public ResponseEntity<List<String>> listAllPriorityData() {
        logger.info("getPriorityData Method Starts...");
        List<String> priorityAreaList = null;
        
        try {
        	priorityAreaList = priorityService.getAllPriorityAreaData();
	        if (priorityAreaList.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NOT_FOUND);
	            // You may decide to return HttpStatus.NO_CONTENT
	        }
        } catch(Exception ex) {
        	logger.error("Exception in getPriorityData Method : ",ex.getMessage());
        	ex.printStackTrace();
        }
        
        logger.info("getPriorityData Method Ends...");
        return new ResponseEntity<List<String>>(priorityAreaList, HttpStatus.OK);
    }

}
