/**Copyright 2016, University of Messina.
*
*   Licensed under the Apache License, Version 2.0 (the "License");
*   you may not use this file except in compliance with the License.
*   You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
*   Unless required by applicable law or agreed to in writing, software
*   distributed under the License is distributed on an "AS IS" BASIS,
*   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*   See the License for the specific language governing permissions and
*   limitations under the License.
*/
package BB_ELA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author giuseppe
 */
@Path("/elaman")
public class ActivatorElaManager {

    @Context
    private UriInfo context;
    static final Logger LOGGER = Logger.getLogger(ActivatorElaManager.class);
    /**
     * Creates a new instance of ActivatorElaManager
     */
    public ActivatorElaManager() {
        //MongoDB connector
        
    }

    /**
     * Retrieves representation of an instance of BB_ELA.ActivatorElaManager
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of ActivatorElaManager
     * @param content representation for the resource
     */
    @POST
    @Consumes("application/json")
    public void activateElaManager(String content) {
        try {
            ElasticityManagerSimple ela= new ElasticityManagerSimple();
            JSONObject jo = new JSONObject(content);
            String tenant=jo.getString("tenant");
            String stack=jo.getString("stack");
            JSONObject tmpMap=(JSONObject)jo.get("geographical_Map" );
            String userFederation=jo.getString("userF" );
            
            String passwordFederation=jo.getString("passF");
            String mingap=jo.getString("minGap");
            String firstC=jo.getString("firstC");
            String manifestName=jo.getString("manifestName");
            try {
                LOGGER.debug(tmpMap.toString());
                ela=ela.startMonitoringThreads(tenant, stack, this.fromJSONtoMAP(tmpMap), userFederation,passwordFederation,mingap,firstC,manifestName );
            } catch (ElasticityPolicyException ex) {
                LOGGER.error("error occurred in startMonitoringThreads: "+ex.getMessage());
            }
        } catch (JSONException ex) {
            LOGGER.debug(ex.getMessage());
        }
    }
    
    
    private HashMap<String,ArrayList<ArrayList<String>>> fromJSONtoMAP(JSONObject tmpMap)throws JSONException{
        try{
            HashMap<String,ArrayList<ArrayList<String>>> result= new HashMap<String, ArrayList<ArrayList<String>>>();
            //NOTE: 09/06/2017 - ADD translation Logic!
            /*
            federation => 
            [
            [
            "{ \"idmEndpoint\" : \"http://ctrl-t2:5000/v2.0\" , \"cloudId\" : \"CETIC\" , \"name\" : \"Cetic asbl\" , \"description\" : \"Cetic Bruxelles\" , \"geometry\" : { \"coordinates\" : [ 4.047071 , 50.803721] , \"type\" : \"Point\"} , \"insertTimestamp\" : 1456747123297 , \"gap\" : \"+2\"}"
            ],
            [
            "{ \"idmEndpoint\" : \"http://ctrl-t1:5000/v2.0\" , \"cloudId\" : \"UME\" , \"name\" : \"University of Messina\" , \"description\" : \"Datacenter Papardo\" , \"geometry\" : { \"coordinates\" : [ 15.434675 , 38.193164] , \"type\" : \"Point\"} , \"insertTimestamp\" : 1456747123142 , \"gap\" : \"+5\"}"
            ]
            ]
            */
            Iterator it=tmpMap.keys();
            while (it.hasNext()){
                String stacktmp=(String)it.next();
                JSONArray exttmp=new JSONArray();
                ArrayList<ArrayList<String>> extmap=new ArrayList<ArrayList<String>>();
                
                exttmp=(JSONArray)tmpMap.get(stacktmp);
                for(int i=0;i<exttmp.length();i++){
                    JSONArray innerJA= exttmp.getJSONArray(i);
                    ArrayList<String> inttmp=new ArrayList<String>();
                    for (int j=0;j<innerJA.length();j++){
                        String elem=innerJA.getString(j);
                        inttmp.add(elem);
                    }
                    extmap.add(inttmp);
                }
                result.put(stacktmp, extmap);
            }
            return result;
            }
        catch(JSONException je){
            LOGGER.error("Exception Occurred in translate operation from JSON to ArrayList");
            throw je;
        }
    }
}

