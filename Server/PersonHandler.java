/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Jose
 */
public class PersonHandler {
    List<Person> persons = new ArrayList<Person>(); 
    
    public PersonHandler(){
        persons = new ArrayList<Person>();
    }
    
    public boolean addPerson(Person p){
        for (Person person : persons) {
            if (person.getID().equals(p.getID())) {
                return false;
            }
        }
        persons.add(p);
        return true;
    }
    
    public Person getPerson(String id){
        Person p = null;
        for(int i = 0; i < persons.size(); i++){
            if(persons.get(i).getID().equals(id)){
                p = persons.get(i);
                return p;
            }
        }
        //p = new Person();
        return p;
    }
    
    public boolean checkoutDevice (String patron_id, String device_id){
      boolean status = false;
            for (Person person : persons) {
            if (person.getID().equals(patron_id)) {
                //person.checkoutDevice(device_id);
                status = true;
            }
        }
       
        return status;
    } 
    
     public boolean checkinDevice (String patron_id, String device_id){
      boolean status = false;
            for (Person person : persons) {
            if (person.getID().equals(patron_id)) {
                //person.checkinDevice(device_id);
                status = true;
            }
        }
       
        return status;
    } 
        
    public boolean deletePerson(String id){
        Person p;
        for(int i = 0; i < persons.size(); i++){
            if(persons.get(i).getID().equals(id)){
                 p = persons.get(i);
                 persons.remove(p);
                 return true;
            }
        }
        return false;
    }
    
    public void SerializePerson() {
        try {
            FileOutputStream fileOut = new FileOutputStream("persons.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(persons);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in Persons.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
	
	public void DeserializePersonList( ) {
        try {
            FileInputStream fileIn = new FileInputStream("persons.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            persons = (List<Person>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Person class not found");
            c.printStackTrace();
            return;
        }
        
        System.out.println("Deserialized persons.ser Contents ...");
        for(Person p : persons )
            System.out.println(p.toString());        
    }
}

