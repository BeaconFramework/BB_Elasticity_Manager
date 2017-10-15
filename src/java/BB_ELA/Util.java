/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BB_ELA;

import MDBInt.DBMongo;
import MDBInt.MDBIException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * REST Web Service
 *
 * @author giuseppe
 */
@Path("Utils")
public class Util {

    @Context
    private UriInfo context;
    private DBMongo m;
    /**
     * Creates a new instance of testThread
     */
    public Util() {
        this.m=new DBMongo();
        this.m.connectLocale("10.9.240.1");
    }

    /**
     * Retrieves information about twinVM selected 
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/{borrower}/{OriginalVM}/{target_Cloud}")
    @Produces("application/json")
    public String getTwin(@PathParam("borrower") String borrower,@PathParam("OriginalVM") String original,@PathParam("target_Cloud") String cloudId) {
        try{
            String resp=m.findResourceMate(borrower, original,cloudId);
            if(resp==null)
                return this.prepare_error("No twinVM identified for VM "+original+" of the tenant "+borrower+" in the target cloud "+cloudId, "Medium");
            else{
                JSONObject tmp=new JSONObject(resp);
                tmp.remove("_id");
                tmp.remove("insertTimestamp");
                return this.prepare_answer(tmp.toString(0));
            }
        /*}catch(MDBIException e){
            return this.prepare_error("Exception Occurred in MongoDB interaction. Searched twinVM for VM "+original+" of the tenant "+borrower+" in the target cloud "+cloudId+". Exception Obtained:\n"+e.getMessage(), "Medium");
        */
        }catch(JSONException je){
            return "{\"state\":1,\"message\": \"Exception Occurred in JSONObject creation to answer. Searched twinVM for VM "+original+" of the tenant "+borrower+" in the target cloud "+cloudId+". Exception Obtained:\n"+je.getMessage()+"\"error_level\":\"Grave\",\"data\":\"\"}";
        }catch(Exception e){
            return "{\"state\":1,\"message\": \" Generic Exception Occurred. Searched twinVM for VM "+original+" of the tenant "+borrower+" in the target cloud "+cloudId+". Exception Obtained:\n"+e.getMessage()+"\"error_level\":\"Grave\",\"data\":\"\"}";
        }
    }
    
    /**
     * Retrieves information about twinVMs of the selected one
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/{borrower}/{OriginalVM}")
    @Produces("application/json")
    public String getTwinVMs(@PathParam("borrower") String borrower,@PathParam("OriginalVM") String original) {
        try{
            ArrayList<String> resp=m.findALLResourceMate(borrower, original);
            if(resp==null)
                return this.prepare_error("No twinVMs identified for VM "+original+" of the tenant "+borrower, "Medium");
            else
            {
                JSONArray ja=new JSONArray();
                Iterator i=resp.iterator();
                while(i.hasNext())
                    ja.put(new JSONObject((String)i.next()));
                return this.prepare_answer(ja.toString(0));
            }
        /*}catch(MDBIException e){
            return this.prepare_error("Exception Occurred in MongoDB interaction. Searched twinVM for VM "+original+" of the tenant "+borrower+" in the target cloud "+cloudId+". Exception Obtained:\n"+e.getMessage(), "Medium");
        */
        }catch(JSONException je){
            return "{\"state\":1,\"message\": \"Exception Occurred in JSONObject creation to answer. Searched twinVMs for VM "+original+" of the tenant "+borrower+". Exception Obtained:\n"+je.getMessage()+"\"error_level\":\"Grave\",\"data\":\"\"}";
        }catch(Exception e){
            return "{\"state\":1,\"message\": \" Generic Exception Occurred. Searched twinVMs for VM "+original+" of the tenant "+borrower+". Exception Obtained:\n"+e.getMessage()+"\"error_level\":\"Grave\",\"data\":\"\"}";
        }
    }
    
    /**
     * Retrieves information about twinVMs of the selected one
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/{borrower}/{templateUUID}")
    @Produces("application/json")
    public String getTwinVMsfromTemplate(@PathParam("borrower") String borrower,@PathParam("templateUUID") String original) {
        try{
            ArrayList<String> resp=m.findALLResourceMatefromTemplate(borrower, original);
            if(resp==null)
                return this.prepare_error("No twinVMs identified for VM "+original+" of the tenant "+borrower, "Medium");
            else
            {
                JSONArray ja=new JSONArray();
                Iterator i=resp.iterator();
                while(i.hasNext())
                    ja.put(new JSONObject((String)i.next()));
                return this.prepare_answer(ja.toString(0));
            }
        /*}catch(MDBIException e){
            return this.prepare_error("Exception Occurred in MongoDB interaction. Searched twinVM for VM "+original+" of the tenant "+borrower+" in the target cloud "+cloudId+". Exception Obtained:\n"+e.getMessage(), "Medium");
        */
        }catch(JSONException je){
            return "{\"state\":1,\"message\": \"Exception Occurred in JSONObject creation to answer. Searched twinVMs for template "+original+" of the tenant "+borrower+". Exception Obtained:\n"+je.getMessage()+"\"error_level\":\"Grave\",\"data\":\"\"}";
        }catch(Exception e){
            return "{\"state\":1,\"message\": \" Generic Exception Occurred. Searched twinVMs for template "+original+" of the tenant "+borrower+". Exception Obtained:\n"+e.getMessage()+"\"error_level\":\"Grave\",\"data\":\"\"}";
        }
    }
    /**
     * Retrieves information about twinVM selected 
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/locateVM/{borrower}/{VM}")
    @Produces("application/json")
    public String getInfoVM(@PathParam("borrower") String borrower,@PathParam("VM") String original) {
        try{
            String resp=m.findInfoVM(borrower, original);
            if(resp==null)
                return this.prepare_error("No twinVM valid identified for VM "+original+" of the tenant "+borrower, "Medium");
            else{
                JSONObject tmp=new JSONObject(resp);
                tmp.remove("_id");
                tmp.remove("insertTimestamp");
                return this.prepare_answer(tmp.toString(0));
            }
        /*}catch(MDBIException e){
            return this.prepare_error("Exception Occurred in MongoDB interaction. Searched twinVM for VM "+original+" of the tenant "+borrower+" in the target cloud "+cloudId+". Exception Obtained:\n"+e.getMessage(), "Medium");
        */
        }catch(JSONException je){
            return "{\"state\":1,\"message\": \"Exception Occurred in JSONObject creation to answer. Searched twinVM valid for VM "+original+" of the tenant "+borrower+". Exception Obtained:\n"+je.getMessage()+"\"error_level\":\"Grave\",\"data\":\"\"}";
        }catch(Exception e){
            return "{\"state\":1,\"message\": \" Generic Exception Occurred. Searched twinVM valid for VM "+original+" of the tenant "+borrower+". Exception Obtained:\n"+e.getMessage()+"\"error_level\":\"Grave\",\"data\":\"\"}";
        }
    }
    
    /**
     * PUT method for updating or creating an instance of testThread
     * @param content representation for the resource
     */
    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
    
    private String prepare_answer(String data) throws JSONException{
        JSONObject err=new JSONObject();
        err.put("state", 0);
        err.put("message", "None");
        err.put("error_level", "None");
        err.put("data",data );
        return err.toString(0);
    }
    private String prepare_error(String cause, String strenght) throws JSONException{
        JSONObject err=new JSONObject();
        err.put("state", 1);
        err.put("message", cause);
        err.put("error_level", strenght);
        err.put("data","" );
        return err.toString(0);
    }
}
