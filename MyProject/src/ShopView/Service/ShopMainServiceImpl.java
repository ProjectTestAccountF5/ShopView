package ShopView.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ShopView.ProductInfo;
import ShopView.ShopMainController;
import ShopView.Data.IProductManage;
import ShopView.Data.ProductManageImpl;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ShopMainServiceImpl implements ShopMainService{
	
	private ProductManageImpl manImpl;
	private CommonService comServ;
	private ShopDetailsService shopDetailServ;
	private IProductManage prodManage;
	@Override
	public Parent OpenPrdDetails() {	//버튼 클릭시 실행
		System.out.println("============MainService===========");
		comServ =new CommonServiceImpl();
		Stage prdDetails =new Stage();
		Parent form =comServ.showWindow(prdDetails, "../shopDetailsView.fxml");

		return form;
	}
	

}
