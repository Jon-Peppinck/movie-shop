import java.io.* ;
import java.sql.*;
import java.util.*;
import java.sql.Date;

public class Movie_Shop {
	public static void main(String args[]) {
		// Database Connection Details
		// database: "<database_name>", username: "<username>", password: <password>
		Connection conn = null;
		try
		{
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost/<database_name>";
			conn = DriverManager.getConnection(url,"<username>", "<password>");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			System.exit(2);
		}
		// Init input reader
		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader bufRead = new BufferedReader(istream);
		// Init variables
		String db_name = "";
		String db_user = "";
		String db_password = "";
		String m_id = "";
		int m_id_int = -1;
		String m_title = "";
		String d_fname = "";
		String d_lname = "";
		String m_genre = "";
		String media_medium = "";
		String m_release_date = "";
		// Default date "9999-12-12" will be set to null if no data entered for that field later
		java.sql.Date m_release_date_date = java.sql.Date.valueOf( "9999-12-12" ); // "yyyy-mm-dd"
		String m_studio_name = "";
		String m_retail_price = "";
		double m_retail_price_double = -1.0;
		String m_current_stock = "";
		int m_current_stock_int = -1;

		try {
			// Database input required to match db connection details
			while(!db_name.equals("<database_name>")) {
				System.out.print("database: ");
				db_name = bufRead.readLine();
			}
			while(!db_user.equals("<username>")) {
				System.out.print("user: ");
				db_user = bufRead.readLine();
			}
			while(!db_password.equals("<password>")) {
				System.out.print("password: ");
				db_password = bufRead.readLine();
			}
			// Check ID is an integer and isn't empty
			System.out.println();
						int h = 0;
			while(m_id_int == -1 || h < 1) {

				try {
					System.out.println("Please enter the id for the new movie: ");
					m_id = bufRead.readLine();
					m_id_int = Integer.parseInt(m_id);
					h++;
				}	catch (NumberFormatException err) {
					System.out.println("\n" + err);
				}
			}	
			// Check title isn't empty
			while(m_title.trim().isEmpty()) {
				System.out.println("Please enter the title for the new movie: ");
				m_title = bufRead.readLine();
			}
			// Check directors first name only consists of letters and isn't empty
			while(d_fname.trim().isEmpty() || !d_fname.chars().allMatch(Character::isLetter)) {
				System.out.println("Please enter the director's first name: ");
				d_fname = bufRead.readLine();
			}
			// Check directors last name only consists of letters and isn't empty
			while(d_lname.trim().isEmpty() || !d_lname.chars().allMatch(Character::isLetter)) {
				System.out.println("Please enter the director's last name: ");
				d_lname = bufRead.readLine();
			}
			// Check that a particular genre is entered
			while(!m_genre.equalsIgnoreCase("action") && !m_genre.equalsIgnoreCase("adventure") && !m_genre.equalsIgnoreCase("comedy") && !m_genre.equalsIgnoreCase("romance") && !m_genre.equalsIgnoreCase("science fiction") && !m_genre.equalsIgnoreCase("documentary") && !m_genre.equalsIgnoreCase("drama") && !m_genre.equalsIgnoreCase("horror")) {
				System.out.println("Please enter the genre of the movie: ");
				m_genre = bufRead.readLine();
				if(m_genre.equalsIgnoreCase("action")) {
						m_genre = "Action";
				} else if(m_genre.equalsIgnoreCase("adventure")) {
						m_genre = "Adventure";
				} else if(m_genre.equalsIgnoreCase("comedy")) {
						m_genre = "Comedy";
				} else if(m_genre.equalsIgnoreCase("romance")) {
						m_genre = "Romance";
				} else if(m_genre.equalsIgnoreCase("science fiction")) {
						m_genre = "Science Fiction";
				} else if(m_genre.equalsIgnoreCase("documentary")) {
						m_genre = "Documentary";
				} else if(m_genre.equalsIgnoreCase("drama")) {
						m_genre = "Drama";
				} else if(m_genre.equalsIgnoreCase("horror")) {
						m_genre = "Horror";
				}
			}
			// Check that a particular media medium is entered
			while(!media_medium.equalsIgnoreCase("dvd") && !media_medium.equalsIgnoreCase("blu-ray")) {
				System.out.println("Please enter the media type: ");
				media_medium = bufRead.readLine();
				if(media_medium.equalsIgnoreCase("dvd")) {
						media_medium = "DVD";
				} else if(media_medium.equalsIgnoreCase("blu-ray")) {
						media_medium = "Blu-Ray";
				}
			}
			// Check that release date is either empty or is of form yyyy-mm-dd - optional
			// If m_releae_date is default - i.e. empty, set the date to null
			int i = 0;
			while(i < 1) {
				try {
					System.out.println("Please enter the movies's release date (yyyy-mm-dd): ");
					m_release_date = bufRead.readLine();
					if (m_release_date.trim().isEmpty()) {
							m_release_date_date = null;
							i++;
					}
					m_release_date_date = Date.valueOf(m_release_date);
					i++;
				} catch (IllegalArgumentException err) {
						if (!m_release_date.trim().isEmpty()) {
							System.out.println("\n" + err);
						}       
					}
			}
			// Movie studio is optional to enter
			int j = 0;
			while(j < 1) {
				System.out.println("Please enter the movies's studio: ");
				m_studio_name = bufRead.readLine();
				j++;
			}
			// Check that retail price is a double, not empty and greater than 0
			int k = 0;
			while(m_retail_price_double == -1.0 || m_retail_price_double <= 0 || k < 1) {
				try {
					System.out.println("Please enter the retail price of the movie: ");
					m_retail_price = bufRead.readLine();
					m_retail_price_double = Double.parseDouble(m_retail_price);
					k++;
				}	catch (NumberFormatException err) {
					System.out.println("\n" + err);
				}
			}
			// Check that current stock is an integer, not empty, and greater than or equal to 0
			int l = 0;
			while(m_current_stock_int == -1 || m_current_stock_int < 0 || l < 1) {
				try {
					System.out.println("Please enter the number of copies in stock: ");
					m_current_stock = bufRead.readLine();
					m_current_stock_int = Integer.parseInt(m_current_stock);
					l++;
				}	catch (NumberFormatException err) {
					System.out.println("\n" + err);
				}
			}
		} catch (IOException err) {
			System.out.println(err);
		} catch(NumberFormatException err) {
			System.out.println(err);
		}
		// Insert a new row of data
		System.out.println("\n****Inserting a New Movie*****");
		System.out.println();
		// Specify the query
		Statement stmt = null;
		try {
			// Create a new statement object - notice the additional arguments for inserting
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			// Get all records in the Movie table
			ResultSet uprs = stmt.executeQuery("SELECT * FROM Movie");
			// Check unique movie ID
			// Loops through each tuple from Movie relation
			while (uprs.next()) {
				int current_m_id_int = uprs.getInt("movie_id"); // movie_id value from current tuple
				try {
					// If movie ID equal to a value from the db, repeat input until unique ID
					while(m_id_int == current_m_id_int) {
						System.out.println("Please enter the id for the new movie: ");
						m_id = bufRead.readLine();
						m_id_int = Integer.parseInt(m_id);
						uprs.beforeFirst(); // Since new ID - loop through tuple ID's again
					}
				} catch (IOException err) {
						System.out.println(err);
				} catch (NumberFormatException err) {
						System.out.println(err);
				} 
			}
			// Create a new row in the ResultSet object
			uprs.moveToInsertRow();
			// Add new movie information to the new row of data
			uprs.updateInt("movie_id", m_id_int);
			uprs.updateString("movie_title", m_title);
			uprs.updateString("director_last_name", d_lname);
			uprs.updateString("director_first_name", d_fname);
			uprs.updateString("genre", m_genre);
			uprs.updateString("media_type", media_medium);
			uprs.updateDate("release_date", m_release_date_date);
			uprs.updateString("studio_name", m_studio_name );
			uprs.updateDouble("retail_price", m_retail_price_double);
			uprs.updateInt("current_stock", m_current_stock_int);
			// Insert the new row of data to the database
			uprs.insertRow();
			// Move the cursor back to the start of the ResultSet object
			uprs.beforeFirst();
			conn.close();
			System.out.println("\nSuccess! A new entry for " + m_title + " has been entered into the database.");
		} catch (SQLException e ) {
			System.out.println(e);
			System.exit(1);      
		}
	}
}
