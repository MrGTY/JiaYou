package cn.zhaojisys.pojo;

public class Paramatersetting {
	private Integer id;
	private Integer minimunWithDrawal;		//最低提取量为1吨 1*unitConversion
	private Integer unitConversion;			//提取单位换算
	private String typrSpec;				//轮胎品牌
	private String typrMaterial;			//轮胎材料
	private String tyreBrand;				//轮胎规格
	private String createData;				//创建时间
	public String getTyreBrand() {
		return tyreBrand;
	}
	public void setTyreBrand(String tyreBrand) {
		this.tyreBrand = tyreBrand;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMinimunWithDrawal() {
		return minimunWithDrawal;
	}
	public void setMinimunWithDrawal(Integer minimunWithDrawal) {
		this.minimunWithDrawal = minimunWithDrawal;
	}
	public Integer getUnitConversion() {
		return unitConversion;
	}
	public void setUnitConversion(Integer unitConversion) {
		this.unitConversion = unitConversion;
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
	public String getCreateData() {
		return createData;
	}
	public void setCreateData(String createData) {
		this.createData = createData;
	}
	
}
