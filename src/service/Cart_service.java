package service;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Cart;
@Path("/Cart")
public class Cart_service {

		Cart cobject = new Cart();
        @GET
		@Path("get")
		@Produces(MediaType.TEXT_PLAIN)
		public String getIt() {
			return "Got it yohan!";

		
	}
    	@GET
    	@Path("Cart_Read")
    	@Produces(MediaType.TEXT_HTML)

    	public String readcart() {
    		return cobject.readCart();
    	}

    	@POST
    	@Path("/add")
    	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    	@Produces(MediaType.TEXT_PLAIN)
    	public String insertCart(
    			@FormParam("CID") String CID,
    			@FormParam("Item_Code") String Item_Code,
    			@FormParam("Item_Name") String Item_Name, 
    			@FormParam("Price") String Price, 
    			@FormParam("Qty") String Qty )
    			 {

    		String output = cobject.insertCart(CID, Item_Code, Item_Name, Price, Qty);
    		return output;
    	}
    	@PUT
    	@Path("/update_Cart")
    	@Consumes(MediaType.APPLICATION_JSON)
    	@Produces(MediaType.TEXT_PLAIN)
    	public String updateCart(String CartData) {

    		// Convert the input string to a JSON object
    		JsonObject updateObj = new JsonParser().parse(CartData).getAsJsonObject();

    		// Read the values from the JSON object
    		String CID = updateObj.get("CID").getAsString();
    		String Item_Code = updateObj.get("Item_Code").getAsString();
    		String Item_Name = updateObj.get("Item_Name").getAsString();
    		String Price = updateObj.get("Price").getAsString();
    		String Qty = updateObj.get("Qty").getAsString();
    		

    		String output = cobject.updateCart(CID, Item_Code, Item_Name, Price, Qty);
    		return output;

    	}

    	@DELETE
    	@Path("/delete")
    	@Consumes(MediaType.APPLICATION_XML)
    	@Produces(MediaType.TEXT_PLAIN)
    	public String deleteCart(String CartData) {
    		// Convert the input string to an XML document
    		Document doc = Jsoup.parse(CartData, "", Parser.xmlParser());
    		// Read the value from the element <itemID>
    		String dCID = doc.select("CID").text();
    		String output = cobject.deleteCart(dCID);
    		return output;
    	}

}
