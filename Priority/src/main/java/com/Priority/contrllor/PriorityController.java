package com.priority.contrllor;



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
import com.priority.model.Priority;
import com.priority.service.PriorityService;
import com.priority.util.CustomErrorType;


@RestController
public class PriorityController {

	public static final Logger logger = LoggerFactory.getLogger(PriorityController.class);
	
	@Autowired
	private PriorityService priorityService;
	

	//----------CreateArea API-------------------------------------------------------------------------------
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value="/saveArea",method = RequestMethod.POST) 
    public ResponseEntity<?> createArea(@RequestBody Priority priority,UriComponentsBuilder ucBuilder) {
        
    	logger.info("createArea Method Starts...");
        HttpHeaders headers= null;
        try {
        	// If there is already an Id in database.
	        if (priorityService.isPriorityDataExist(priority)) {
	            logger.error("Unable to create. A Area with Name {} already exist", priority.getAreaName());
	            return new ResponseEntity(new CustomErrorType("Unable to create Area. A Area with Name " + 
	            		priority.getAreaName() + " already exist,Please Create New Area!!!"),HttpStatus.CONFLICT);
	        }
	        //  New Data to be inserted in database.
	        if(priority.getPriorityId() == null) {
	        		priorityService.savePriorityData(priority);
	        }
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
	@RequestMapping(value="/getAreaList",method = RequestMethod.GET)
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
