package cn.zhaojisys.pojo;

import java.util.Date;

public class Gasstation {
	private Integer id;				//主键
	private String siteName;		//站点名称
	private String code;			//站点账户
	private String password;		//站点密码
	private String province;		//省
	private String city;			//市
	private String county;			//县
	private String detaileAddress;	//详细地址
	private String contact;			//联系人
	private String mobilePhone;		//手机
	private Integer initialOil;		//初始油量
	private String description;		//备注
	private String qrCodeImg;		//二维码地址
	private Integer coverRadius;	//商户覆盖半径
	private String longitude;		//经度
	private String latitude;		//纬度
	private Integer status;			//状态：0、禁用  1、启用
	private Integer gsType;			//0、燃油站点  1、轮胎站点
	private String typrSpec;		//轮胎规格
	private String typrMaterial;	//轮胎材料
	private String tyreBrand;		//轮胎品牌
	private Integer quota;			//可提取额度 燃油为L  轮胎为元
	private Date creatData;			//创建时间
	private Integer logicId;		//逻辑删除1为正常 0为删了
	private Double charge;          //服务费￥/L
	
	private String zong;//总加油量
	
	public String getZong() {
		return zong;
	}
	public void setZong(String zong) {
		this.zong = zong;
	}
	public Double getCharge() {
		return charge;
	}
	public void setCharge(Double charge) {
		this.charge = charge;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTyprSpec() {
		return typrSpec;
	}
	public void setTyprSpec(String typrSpec) {
		this.typrSpec = typrSpec;
	}
	public String getTyprMaterial() {
		return typrMaterial;
	}
	public void setTyprMaterial(String typrMaterial) {
		this.typrMaterial = typrMaterial;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Integer getLogicId() {
		return logicId;
	}
	public void setLogicId(Integer logicId) {
		this.logicId = logicId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getDetaileAddress() {
		return detaileAddress;
	}
	public void setDetaileAddress(String detaileAddress) {
		this.detaileAddress = detaileAddress;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public Integer getInitialOil() {
		return initialOil;
	}
	public void setInitialOil(Integer initialOil) {
		this.initialOil = initialOil;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getQrCodeImg() {
		return qrCodeImg;
	}
	public void setQrCodeImg(String qrCodeImg) {
		this.qrCodeImg = qrCodeImg;
	}
	public Integer getCoverRadius() {
		return coverRadius;
	}
	public void setCoverRadius(Integer coverRadius) {
		this.coverRadius = coverRadius;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getGsType() {
		return gsType;
	}
	public void setGsType(Integer gsType) {
		this.gsType = gsType;
	}
	public String getTyreBrand() {
		return tyreBrand;
	}
	public void setTyreBrand(String tyreBrand) {
		this.tyreBrand = tyreBrand;
	}
	public Integer getQuota() {
		return quota;
	}
	public void setQuota(Integer quota) {
		this.quota = quota;
	}
	public Date getCreatData() {
		return creatData;
	}
	public void setCreatData(Date creatData) {
		this.creatData = creatData;
	}
}
