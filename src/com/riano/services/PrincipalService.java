package com.riano.services;

import com.mongodb.*;

public class PrincipalService {

    public DB connection(){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB database = mongoClient.getDB("evaluacionDB");
        DBCollection collection = database.getCollection("user");
        return database;
    }

    public void remove(String id){

        DB database = connection();
        DBCollection collection = database.getCollection("Person");
        DBCollection collection2 = database.getCollection("user");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("userName", id);
        searchQuery.put("userName" , id);
        collection.remove(searchQuery);
        collection2.remove(searchQuery);
    }

    public void update(String userName, String password, String email, String phone, String newUser){
        DB database = connection();

        DBCollection collection = database.getCollection("Person");
        BasicDBObject query = new BasicDBObject();
        query.put("userName", userName);

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("userName", newUser);
        newDocument.put("password", password);
        newDocument.put("email", email);
        newDocument.put("phone", phone);

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);
        updateUser(userName, password, newUser);

        collection.update(query, updateObject);
    }

    private void updateUser(String userName, String password, String newUser){
        DB database = connection();

        DBCollection collection = database.getCollection("user");
        BasicDBObject query = new BasicDBObject();
        query.put("userName", userName);

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("userName", newUser);
        newDocument.put("password", password);

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);
        collection.update(query, updateObject);
    }
}
