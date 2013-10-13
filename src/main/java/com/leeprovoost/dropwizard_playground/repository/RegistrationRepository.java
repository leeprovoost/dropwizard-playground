package com.leeprovoost.dropwizard_playground.repository;

import com.leeprovoost.dropwizard_playground.core.Registration;
import com.leeprovoost.dropwizard_playground.service.RegistrationNotFoundException;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

public class RegistrationRepository {

	public static final String COLLECTION_NAME = "registrations";
	
    private JacksonDBCollection<Registration, String> collection;

    public RegistrationRepository(DB mongoDB) {
        final DBCollection dbCollection = mongoDB.getCollection(COLLECTION_NAME);
        this.collection = JacksonDBCollection.wrap(dbCollection, Registration.class, String.class);
    }

    public void drop() {
        collection.drop();
    }

    public Registration findById(String id) {
    	
    	try {
    		Registration foundRegistration = collection.findOneById(id);
    		return foundRegistration;
    	} catch (Exception e) {
    		throw new RegistrationNotFoundException(id);
    	}
    }
    
    /*
     * TODO Implement add versioning of objects
     */
    public Registration findByIdAndModify(String id, Registration registration) {
    	
    	return new Registration();
    }
    
    // TODO refactor DTO
    public Registration insert(Registration registration) {

    	WriteResult<Registration, String> result = collection.insert(registration);
    	//String id = result.getSavedId();
        return result.getSavedObject();
    }


}
