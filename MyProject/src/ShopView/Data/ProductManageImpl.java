package ShopView.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ShopView.ProductInfo;

public class ProductManageImpl implements IProductManage{
	final static String DRIVER="org.sqlite.JDBC";
	final static String DB ="jdbc:sqlite:src/ShopView/Data/project.db";
	Connection conn;
	public ProductManageImpl() {
		try {
			Class.forName(DRIVER);
			conn=DriverManager.getConnection(DB);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<ProductInfo> getProductInfo(int prodNum) {
		String sql ="SELECT * "+
				"FROM productInfo "+
				"WHERE prodNum like ? "
				;
		List<ProductInfo> lstProductInfo =new ArrayList<ProductInfo>();
		
		try {
			PreparedStatement pStmt =conn.prepareStatement(sql);
			
			pStmt.setInt(1, prodNum);
			ResultSet rs =pStmt.executeQuery();
			
			while(rs.next()) {
				ProductInfo productInfo =new ProductInfo();
				
				productInfo.setPrdName(rs.getString("prdName"));
				productInfo.setPrice(rs.getString("price"));
				productInfo.setDcprice(rs.getString("dcprice"));
				productInfo.setColor(rs.getString("color"));
				productInfo.setPrdsize(rs.getString("prdsize"));
				productInfo.setQty(rs.getInt("qty"));
				productInfo.setStock(rs.getInt("stock"));
				productInfo.setTag(rs.getString("tag"));
				productInfo.setScore(rs.getDouble("score"));
				productInfo.setImgsrc(rs.getString("imgsrc"));
				productInfo.setImgdetail(rs.getString("imgdetail"));
				productInfo.setProdNum(rs.getInt("prodNum"));
				
				lstProductInfo.add(productInfo);
				
				pStmt.close();
				conn.close();
//				System.out.println(productInfo.getColor()+" productManage db연결");
//				System.out.println(lstProductInfo.get(0).getColor()+"productManage lst추가");
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			return null;
		}
		return lstProductInfo;
		
	}

}
