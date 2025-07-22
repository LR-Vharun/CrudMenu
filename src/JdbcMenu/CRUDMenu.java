package JdbcMenu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CRUDMenu {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		try { int a=1;
			while(a==1) {
				System.out.println("Enter your choice number ");
				System.out.println("1 for insert");
				System.out.println("2 for update");
				System.out.println("3 for delete");
				System.out.println("4 for select");
				System.out.println("5 for exit");
				int choice=in.nextInt();
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "vharun", "0000");
				switch(choice) {
				case 1:{ 
						PreparedStatement ps=con.prepareStatement("insert ignore into users (id,name) values (?,?)");
						System.out.println("Enter the id and name to be inserted");
						int id=in.nextInt();
						String name=in.next();
						ps.setInt(1,id);
						ps.setString(2, name);
						int row=ps.executeUpdate();
						System.out.println("Number of rows updated = "+row);
						break;
						
						
				
				}					
				
				case 2:{
					
					PreparedStatement ps=con.prepareStatement("update  users set name=? where id=?");
					System.out.println("Enter the  name to be updated and the id");
					int id=in.nextInt();
					String name=in.next();
					ps.setInt(2,id);
					ps.setString(1, name);
					int row=ps.executeUpdate();
					System.out.println("Number of rows updated = "+row);
					break;
				}
				
				case 3:{
					System.out.print("Enter ID to delete: ");
                    int id = in.nextInt();
                    PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE id = ?");
                    ps.setInt(1, id);
                    int res = ps.executeUpdate();
                    System.out.println(res == 0 ? "No user deleted." : "User deleted.");
                    break;
				}
				
				case 4:{
				Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM users");
                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
				}
                    break;}
				
				case 5:{ a=0;
				break;}
				}con.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
