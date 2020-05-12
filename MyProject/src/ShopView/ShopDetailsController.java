package ShopView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import ShopView.Data.IProductManage;
import ShopView.Data.ProductManageImpl;
import ShopView.Service.CommonService;
import ShopView.Service.CommonServiceImpl;
import ShopView.Service.ShopDetailsService;
import ShopView.Service.ShopDetailsServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class ShopDetailsController extends Controller implements Initializable{
	private Parent root;
	private CommonService comServ;
	private ShopDetailsService shopDetailServ;
	private IProductManage productManage;
	@Override
	public void setRoot(Parent root) {
		this.root =root;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comServ =new CommonServiceImpl();
		shopDetailServ = new ShopDetailsServiceImpl();
		productManage =new ProductManageImpl();
	}
	
	public int isCheck() {
		System.out.println("=========isCheck=======");
		int returnNum=0;
		ArrayList<String> cmb = new ArrayList<String>();
		cmb.add(0, "#colorCmb");
		cmb.add(1, "#sizeCmb");
		cmb.add(2, "#qtyCmb");
		for(int i=0;i<cmb.size();i++) {
		if(shopDetailServ.isComboBox(root, cmb.get(i))==0)	//false면
			returnNum+= 0;
		else
			returnNum+= 1;
		}
		if(returnNum<3) {
			System.out.println(returnNum);
			return 0;
		}
		return 1;
	}

	public void reviewProc() {
		
	}
	public void qnaProc() {
		
	}
	public void buyProc() {
		if(isCheck()==0) {
			comServ.MsgBox("메시지", "다시 시도", "상품 옵션을 선택하세요");
			return;
		}
		else
			comServ.MsgBox("메시지", "성공", "결제요청되었습니다.");
	}
	public void cartProc() {
		if(isCheck()==0) {
			comServ.MsgBox("메시지", "다시 시도", "상품 옵션을 선택하세요");
			return;
		}
		else
			comServ.MsgBox("메시지", "성공", "장바구니에 추가되었습니다.");
	}

}
