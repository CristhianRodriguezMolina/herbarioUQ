package co.gov.jsasociados.util;

import java.io.*;
import java.sql.*;

class GetBlob {
	FileOutputStream image;
	Connection con = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet res = null;
	StringBuffer query = null;
	String driverName = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/herbario";;
	String dbName = "planta";
	String userName = "root";
	String password = "root";

	public GetBlob() {
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url + dbName, userName, password);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select planta.imagen from Planta planta where image_id='3'");
			if (rs.next()) {
				Blob test = rs.getBlob("image");
				InputStream x = test.getBinaryStream();
				int size = x.available();
				OutputStream out = new FileOutputStream("C:\\anu.jpg");
				byte b[] = new byte[size];
				x.read(b);
				out.write(b);
			}
		} catch (Exception e) {
			System.out.println("Exception :" + e);
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static void main(String args[]) throws IOException {
		GetBlob blob = new GetBlob();
	}
}