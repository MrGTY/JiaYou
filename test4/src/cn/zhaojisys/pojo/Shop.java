package cn.zhaojisys.pojo;

import java.util.Date;

//轮胎商品表
public class Shop {

	private Integer id;
	private String brand;//品牌
	private String specifications;//规格
	private String material;//材质
	private String marque;//商品型号
	private String price;//价格
	private String pathimg;//上传图片
	private Integer gId;//关联站点id
	private Date creatData;//创建时间
	

	public Date getCreatData() {
		return creatData;
	}
	public void setCreatData(Date creatData) {
		this.creatData = creatData;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPathimg() {
		return pathimg;
	}
	public void setPathimg(String pathimg) {
		this.pathimg = pathimg;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getgId() {
		return gId;
	}
	public void setgId(Integer gId) {
		this.gId = gId;
	}
	
}
