package ShopView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.xml.ws.Binding;

import ShopView.Data.IProductManage;
import ShopView.Data.ProductManageImpl;
import ShopView.Service.CommonService;
import ShopView.Service.CommonServiceImpl;
import ShopView.Service.ShopDetailsService;
import ShopView.Service.ShopDetailsServiceImpl;
import ShopView.Service.ShopMainService;
import ShopView.Service.ShopMainServiceImpl;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ShopMainController extends Controller implements Initializable{
	@FXML ScrollPane scrollPane;
	@FXML GridPane gridPane;
	@FXML ImageView prdImg1;
	@FXML Button prdBtn2;
	private Parent root;
	private CommonService comServ;
	private ShopMainService shopMainServ;
	private ShopDetailsService shopDetailServ;
	private IProductManage prodManage ;
	private ObjectBinding<Bounds> bounds;
	

	@Override
	public void setRoot(Parent root) {
		this.root= root;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comServ =new CommonServiceImpl();
		shopMainServ =new ShopMainServiceImpl();
		shopDetailServ =new ShopDetailsServiceImpl();
		getNode();
		
	}
	public void OpenPrdDetails(ArrayList<Integer> pos) {	//Ŭ���� ��
		System.out.println("======shopMainController======");
		
		ArrayList<Integer> location =new ArrayList<Integer>();	//gridpane�� position�� ��������
		location.addAll(pos);
		for(int i=0;i<pos.size();i++)
			System.out.println(pos.get(i));
		
		
		prodManage =new ProductManageImpl();
		List<ProductInfo> lstProdInfo = new ArrayList<ProductInfo>();
		lstProdInfo.addAll(prodManage.getProductInfo( pos.get(0) ));
		Parent form =shopMainServ.OpenPrdDetails();	//shopMainServiceImpl
		
//		String [] colorItems= {lstProdInfo.get(0).getColor()};
//		String [] sizeItems= {lstProdInfo.get(0).getPrdsize()};
		
		comServ.DivideCom(lstProdInfo.get(0).getColor());
		comServ.DivideCom(lstProdInfo.get(0).getPrdsize());

		shopDetailServ.AddComboBox(form, comServ.DivideCom(lstProdInfo.get(0).getColor()), "#colorCmb");	//����
		shopDetailServ.AddComboBox(form, comServ.DivideCom(lstProdInfo.get(0).getPrdsize()), "#sizeCmb");		//������
		shopDetailServ.AddComboBox(form, Arrays.asList("1"), "#qtyCmb");			//����
		shopDetailServ.AddTitleLbl(form, lstProdInfo.get(0).getPrdName(), "#prdName");	//��ǰ��
		shopDetailServ.AddScoreLbl(form, lstProdInfo.get(0).getScore(), "#scoreLbl");
		shopDetailServ.AlmoSoldOutLbl(form, lstProdInfo.get(0).getStock(), "#almoSoldOutLbl");	//����ľ� �� ǰ���ӹ� ǥ��
		shopDetailServ.AddPriceLbl(form, lstProdInfo.get(0).getPrice(), lstProdInfo.get(0).getDcprice(), "#priceLbl", "#dcpriceLbl");
		shopDetailServ.AddImgView(form, "DetailImg/"+lstProdInfo.get(0).getImgdetail(), "#detailImgView");//�󼼻���
		shopDetailServ.AddImgView(form, lstProdInfo.get(0).getImgsrc(), "#prdImgView");//��ǥ����
		System.out.println(prdBtn2.getText());
		
	}
	
	public void getNode() {
		
		gridPane.setOnMouseClicked(e->{
			ArrayList<Integer> pos =new ArrayList<Integer>();
			Parent root =(Parent)e.getSource();
			gridPane =(GridPane)root.getScene().getRoot();
			Node clickedChild =e.getPickResult().getIntersectedNode();
			
			/*
			 * //binding(�̿ϼ�) ObjectBinding<Bounds> boundsInPaneBinding =
			 * Bindings.createObjectBinding(() -> { Bounds nodeLocal =
			 * prdImg1.getBoundsInLocal(); Bounds nodeScene =
			 * prdImg1.localToScene(nodeLocal); Bounds nodePane =
			 * gridPane.sceneToLocal(nodeScene); return nodePane ; },
			 * prdImg1.boundsInLocalProperty(), prdImg1.localToSceneTransformProperty(),
			 * gridPane.localToSceneTransformProperty()); //
			 * clickedChild.parentProperty().isEqualTo(prdImg1); //
			 * clickedChild.getParent();
			 */			
			int index =gridPane.getChildren().indexOf(clickedChild);
			System.out.println("index : "+index);
			pos.add(index);
			try {
				int column =gridPane.getColumnIndex(gridPane.getChildren().get(index));
				System.out.println("column : "+column);
				pos.add(column);
			} catch (NullPointerException e2) {
				int column =0;
				System.out.println("column : "+column);
			}
			try {
				int row =gridPane.getRowIndex(gridPane.getChildren().get(index));
				System.out.println("row : "+row);
				pos.add(row);
			} catch (NullPointerException e2) {
				int row =0;
				System.out.println("row : "+row);
			}
			OpenPrdDetails(pos);
		});
	}
	
	
	/*
	 * private class MyEventHandler implements EventHandler<Event>{
	 * 
	 * @Override public void handle(Event evt) {
	 * System.out.println(((Control)evt.getSource()).getId()); } } public Node
	 * getGidPaneNode(int col, int row) { for(Node node : gridPane.getChildren()) {
	 * if(gridPane.getColumnIndex(node)==col && gridPane.getRowIndex(node)==row)
	 * return node; } return null; }
	 */

}
