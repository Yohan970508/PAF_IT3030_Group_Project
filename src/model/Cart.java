package model;
import java.sql.*;
public class Cart {

	public Connection connect() {
		System.out.println("Inside connect method");
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart_management", "root", "");
			System.out.println("successfully connected");
		} catch (Exception e) {
			System.out.println("not connected");
			e.printStackTrace();
		}
		return con;
	}
	public String insertCart(String CID,String Item_Code, String Item_Name, String Price, String Qty) {
		String output = " ";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database.";

			}

			// create a prepared statement
			String query = " insert into cart_system(`CID`,`Item_Code`,`Item_Name`,`Price`,`Qty`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Item_Code);;
			preparedStmt.setString(3, Item_Name);
			preparedStmt.setString(4, Price);
			preparedStmt.setString(5, Qty);;
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the user.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readCart() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>ID</th><th>Item Code</th><th>Item Name</th><th>Price</th><th>Quantity</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from cart_system";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set

			while (rs.next()) {
				String CID = Integer.toString(rs.getInt("CID"));
				String Item_Code = rs.getString("Item_Code");
				String Item_Name = rs.getString("Item_Name");
				String Price = rs.getString("Price");
				String Qty = rs.getString("Qty");
			

				// Add into the html table
				output+="<tr><td>" + CID + "</td>";
				output += "<td>" + Item_Code + "</td>";
				output += "<td>" + Item_Name + "</td>";
				output += "<td>" + Price + "</td>";
				output += "<td>" + Qty + "</td>";
				
				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"reg.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"pID\" type=\"hidden\" value=\"" + CID + "\">" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String updateCart(String CID,String Item_Code, String Item_Name, String Price, String Qty){
		    
		    String output = "";

		    try{

		           Connection con = connect();
		           if (con == null){
		           return "Error while connecting to the database for updating.";
		           }
		           
		           // create a prepared statement
		 
					
		           String query = "UPDATE cart_system SET Item_Code=?,Item_Name=?,Price=?,Qty=? WHERE CID=?";
		           PreparedStatement preparedStmt = con.prepareStatement(query);

		           preparedStmt.setString(1, Item_Code);
		           preparedStmt.setString(2,Item_Name );
		           preparedStmt.setString(3, Price);
		           preparedStmt.setString(4, Qty);
		           preparedStmt.setString(5, CID);
		         
		         

		           // execute the statement
		           preparedStmt.execute();
		           con.close();

		           output = "Updated successfully";
		           


		        }catch(Exception e){
		        	output = "Error while updating the value.";
					System.err.println(e.getMessage());
		        }

		        return output;

		    }

			public String deleteCart(String dCID) {

				String output = "";

				try { 

					Connection con = connect();
					if (con == null) {

						return "Error while connecting to the database for deleting.";
					}

					// create a prepared statement
					String query = "delete from cart_system where CID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setString(1, dCID);
					// execute the statement
					preparedStmt.execute();
					con.close();

					output = "Deleted successfully";

				} catch (Exception e) {

					output = "Error while deleting the value.";
					System.err.println(e.getMessage());
				}

				return output;
			}
}
