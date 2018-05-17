package cn.zhaojisys.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Character.Subset;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import cn.zhaojisys.pojo.ExtractApply;
import cn.zhaojisys.pojo.Oilrecord;
import cn.zhaojisys.pojo.Tyredatails;
import cn.zhaojisys.pojo.Vipuserinfo;
import cn.zhaojisys.pojo.VipuserinfoModel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class Constants {
//	public final static String SYSINTERLOG = "userLogin";
	public final static int pageSize = 50;
	public final static Integer logicId=1;
	public final static String ERROR="";
	public static final String USER_SESSION = "userSession";//员工登陆gasstion记录
	public final static String DEV_USER_SESSION = "devUserSession";//站点登陆记录
//	手机session
	public final static String VIPUSERINFO = "VipuserinfoSession";  //当前登录会员
	public final static String GASSTION = "gasstion";  //站点登录
	
	//截取()中的值
	public static String getValueByString(String len){
		int index=len.indexOf("(");
		return len.substring(index+1,len.length()-1);
	}
	//Date转String
	public static String getDateToString(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	public static Date getStringDate(String date){
		Date newdate=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(date==null||date.equals("")){
				return null;
			}
			newdate=sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newdate;
	}
	public static Date getStringDateTime(String date){
		Date newdate=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if(date==null||date.equals("")){
				return null;
			}
			newdate=sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newdate;
	}
	public static String getNewDate(){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	//取当前系统毫秒
	public static String getNewDateByMsec(){
		Date date=new Date();
		long time=date.getTime();
		return String.valueOf(time);
	}
	//字符串拼接用Buffer效率
	public static String getStringBuffer(String s,String ss){
		StringBuffer sbf=new StringBuffer();
		sbf.append(s);
		sbf.append(ss);
		return sbf.toString();
	}
	//用$拼接字符串
	public static String getStringBuffer$(String s,String ss){
		StringBuffer sbf=new StringBuffer();
		sbf.append(s);
		sbf.append("#");
		sbf.append(ss);
		return sbf.toString();
	}
	//丢数组拼
	public static String getStringarray(String[] s){
		StringBuffer sbf=new StringBuffer();
		for (int i = 0; i < s.length; i++) {
			if(i==0){
				sbf.append(s[i]);
				continue;
			}
			sbf.append("|");
			sbf.append(s[i]);
		}
		return sbf.toString();
	}
	//取吨(总L/参数)
	public static int getTon(int gross,int unitConversion){
		return gross/unitConversion;
	}
	//取余升(总L%参数)
	public static int getLeave(int gross,int unitConversion){
		return gross%unitConversion;
	}
	//取总升(总L-总L%参数)
	public static int getAll(int gross,int unitConversion){
		return gross-gross%unitConversion;
	}
	//生成二维码方法
	public static String getEncode(String filePath,String build) throws WriterException, IOException{
		//String filePath = "E://2wei/"; 
		String fileName = getStringBuffer(getNewDateByMsec(), ".png");//定义名字(存数据库字段)
		String content =build;//生成二维码
		int width = 1200; // 图像宽度  E
        int height = 1200; // 图像高度  
        String format = "png";// 图像类型  
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  
                BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵  
        Path path = FileSystems.getDefault().getPath(filePath, fileName);  
        MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像 
        System.out.println("ok");
        return fileName;
	}
	/** 
     * 解析二维码 
     */  
    public static void testDecode() {  
        String filePath ="E://2wei/1519880805864.png";  
        BufferedImage image;  
        try {  
            image = ImageIO.read(new File(filePath));  
            LuminanceSource source = new BufferedImageLuminanceSource(image);  
            Binarizer binarizer = new HybridBinarizer(source);  
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);  
            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();  
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");  
            Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码  
           /* System.out.println("图片中内容：  ");  
            System.out.println("author： " + result.getText());  //content的值
            System.out.println("图片中格式：  ");  
            System.out.println("encode： " + result.getBarcodeFormat());  */
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (NotFoundException e) {  
            e.printStackTrace();  
        }  
    }  
    //给地址删文件
    public static boolean deleteQrcode(String path){
    	File file=new File(path);
    	return file.delete();
    }
    //拆分字符串返回数组
    public static String[] splitString(String s){
    	return s.split(",");
    }
    public static String change(int d) {
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(d);
	}
    public static String change(String d) {
    	if (d.length()>3) {
    		DecimalFormat df = new DecimalFormat("#,###");
        	return df.format(Integer.parseInt(d));
		}
    	return d;
    }
    public static void main(String[] args) {
    	System.out.println(Constants.change(1234567890));
	}
    
	// 获取日期，转换成(月.日)的格式输出
	public static String changeDate(String str) {
		SimpleDateFormat myFmt0 = new SimpleDateFormat("M.d");
		SimpleDateFormat myFmt00 = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		try {
			now = myFmt00.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return myFmt0.format(now);
	}

	// 获取日期，只输出月份
	public static String changeDateMonth(String str) {
		SimpleDateFormat myFmt0 = new SimpleDateFormat("M");
		SimpleDateFormat myFmt00 = new SimpleDateFormat("yyyy-MM");
		Date now = new Date();
		try {
			now = myFmt00.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return myFmt0.format(now).toString();
	}

	// 天
	/**
	 * 当月总燃油收入量 返回结果为少哪几天的集合
	 * 
	 * @param sffList
	 * @return
	 */
	public static List<Oilrecord> muchDataIsOk(List<Oilrecord> sffList) {
		Date date2 = new Date();
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		String day1 = format2.format(date2).substring(0, 8);// 2018-02-

		SimpleDateFormat format = new SimpleDateFormat("d");
		String curdate = format.format(date2);// 2018年2月8日09:16:22===》》》9
		int day = Integer.parseInt(curdate);
		List<Oilrecord> newlistOilrecord = new ArrayList<Oilrecord>();

		List<Integer> integers = new ArrayList<Integer>();
		int count = 0;
		int num = sffList.size();
		for (int j = 1; j <= day; j++) {
			if (num > 0) {
				for (int i = 0; i < sffList.size(); i++) {
					int len = sffList.get(i).getOpDate().lastIndexOf(".");
					String nameString = sffList.get(i).getOpDate().substring(len + 1);// 2.3===>>>>3
					if (String.valueOf(j).equals(nameString)) {
						count = 1;//
						num = sffList.size();
						sffList.remove(0);
						break;
					}
				}
			}

			if (count != 1) {
				integers.add(j);
			}
			count = 0;
		}
		for (int i = 0; i < integers.size(); i++) {
			if (integers.get(i) < 10) {
				Oilrecord addoil = new Oilrecord();
				addoil.setOperationTime(day1 + "0" + integers.get(i) + " 00:00:01");// 2018-02-08
																					// 15:15:50
				addoil.setIncome(0);// 当月总燃油收入量设置为0
				addoil.setExpenditure(0);
				addoil.setRemaining(0);
				addoil.setId(0);
				newlistOilrecord.add(addoil);
			} else {
				Oilrecord addoil = new Oilrecord();
				addoil.setOperationTime(day1 + integers.get(i) + " 00:00:01");
				addoil.setIncome(0);// 当月总燃油收入量设置为0
				addoil.setRemaining(0);
				addoil.setExpenditure(0);
				addoil.setId(0);
				newlistOilrecord.add(addoil);
			}
		}
		return newlistOilrecord;
	}
	public static List<ExtractApply> muchDataIsOkExtractApply(List<ExtractApply> sffList) {
		Date date2 = new Date();
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		String day1 = format2.format(date2).substring(0, 8);// 2018-02-
		
		SimpleDateFormat format = new SimpleDateFormat("d");
		String curdate = format.format(date2);// 2018年2月8日09:16:22===》》》9
		int day = Integer.parseInt(curdate);
		List<ExtractApply> newlistOilrecord = new ArrayList<ExtractApply>();
		
		List<Integer> integers = new ArrayList<Integer>();
		int count = 0;
		int num = sffList.size();
		for (int j = 1; j <= day; j++) {
			if (num > 0) {
				for (int i = 0; i < sffList.size(); i++) {
					int len = sffList.get(i).getOpDate().lastIndexOf(".");
					String nameString = sffList.get(i).getOpDate().substring(len + 1);// 2.3===>>>>3
					if (String.valueOf(j).equals(nameString)) {
						count = 1;//
						num = sffList.size();
						sffList.remove(0);
						break;
					}
				}
			}
			
			if (count != 1) {
				integers.add(j);
			}
			count = 0;
		}
		for (int i = 0; i < integers.size(); i++) {
			if (integers.get(i) < 10) {
				ExtractApply addoil = new ExtractApply();
				addoil.setApproveData( Constants.getStringDateTime(day1 + "0" + integers.get(i) + " 00:00:01"));// 2018-02-08
				// 15:15:50
				addoil.setAmountDrawn(0);// 当月总燃油收入量设置为0
				newlistOilrecord.add(addoil);
			} else {
				ExtractApply addoil = new ExtractApply();
				addoil.setApproveData( Constants.getStringDateTime(day1 + integers.get(i) + " 00:00:01"));// 2018-02-08
				// 15:15:50
				addoil.setAmountDrawn(0);// 当月总燃油收入量设置为0
				newlistOilrecord.add(addoil);
			}
		}
		return newlistOilrecord;
	}

	public static List<Tyredatails> changeTyredatails(List<Tyredatails> tyre) {
		Date date2 = new Date();
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		String day1 = format2.format(date2).substring(0, 8);// 2018-02-
		SimpleDateFormat format = new SimpleDateFormat("d");
		String curdate = format.format(date2);// 2018年2月8日09:16:22===》》》9
		int day = Integer.parseInt(curdate);
		List<Tyredatails> newlistTyredatails = new ArrayList<Tyredatails>();
		List<Integer> integers = new ArrayList<Integer>();
		int count = 0;
		int num = tyre.size();
		for (int j = 1; j <= day; j++) {
			if (num > 0) {
				for (int i = 0; i < tyre.size(); i++) {
					int len = tyre.get(i).getOpDate().lastIndexOf(".");
					String nameString = tyre.get(i).getOpDate().substring(len + 1);// 2.3===>>>>3
					if (String.valueOf(j).equals(nameString)) {
						count = 1;//
						num = tyre.size();
						tyre.remove(0);
						break;
					}
				}
			}

			if (count != 1) {
				integers.add(j);
			}
			count = 0;
		}
		for (int i = 0; i < integers.size(); i++) {
			if (integers.get(i) < 10) {
				Tyredatails addTyre = new Tyredatails();
				addTyre.setOperationData(day1 + "0" + integers.get(i) + " 00:00:01");// 2018-02-08
				addTyre.setExpenditure(0);	
				addTyre.setId(0);// 15:15:50
				addTyre.setIncome(0);// 当月总燃油收入量设置为0
				newlistTyredatails.add(addTyre);
			} else {
				Tyredatails addTyre = new Tyredatails();
				addTyre.setOperationData(day1 + integers.get(i) + " 00:00:01");
				addTyre.setExpenditure(0);
				addTyre.setId(0);
				addTyre.setIncome(0);// 当月总燃油收入量设置为0
				newlistTyredatails.add(addTyre);
			}
		}
		return newlistTyredatails;
	}

	public static List<Vipuserinfo> changeVipuserinfos(List<Vipuserinfo> vipInfo) {
		Date date2 = new Date();
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		String day1 = format2.format(date2).substring(0, 8);// 2018-02-
		SimpleDateFormat format = new SimpleDateFormat("d");
		String curdate = format.format(date2);// 2018年2月8日09:16:22===》》》9
		int day = Integer.parseInt(curdate);
		List<Vipuserinfo> newlistVipuserinfo = new ArrayList<Vipuserinfo>();
		List<Integer> integers = new ArrayList<Integer>();
		int count = 0;
		int num = vipInfo.size();
		for (int j = 1; j <= day; j++) {
			if (num > 0) {
				for (int i = 0; i < vipInfo.size(); i++) {
					int len = vipInfo.get(i).getOpDate().lastIndexOf(".");
					String nameString = vipInfo.get(i).getOpDate().substring(len + 1);// 2.3===>>>>3
					if (String.valueOf(j).equals(nameString)) {
						count = 1;//
						num = vipInfo.size();
						vipInfo.remove(0);
						break;
					}
				}
			}

			if (count != 1) {
				integers.add(j);
			}
			count = 0;
		}
		for (int i = 0; i < integers.size(); i++) {
			if (integers.get(i) < 10) {
				Vipuserinfo addVip = new Vipuserinfo();
				addVip.setCreateTime(day1 + "0" + integers.get(i) + " 00:00:01");// 2018-02-08
																					// 15:15:50
				addVip.setId(0);// 当月总燃油收入量设置为0
				newlistVipuserinfo.add(addVip);
			} else {
				Vipuserinfo addVip = new Vipuserinfo();
				addVip.setCreateTime(day1 + integers.get(i) + " 00:00:01");
				addVip.setId(0);// 当月总燃油收入量设置为0
				newlistVipuserinfo.add(addVip);
			}
		}
		return newlistVipuserinfo;
	}
	public static List<VipuserinfoModel> changeVipuserinfosModel(List<VipuserinfoModel> vipInfo) {
		Date date2 = new Date();
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		String day1 = format2.format(date2).substring(0, 8);// 2018-02-
		SimpleDateFormat format = new SimpleDateFormat("d");
		String curdate = format.format(date2);// 2018年2月8日09:16:22===》》》9
		int day = Integer.parseInt(curdate);
		List<VipuserinfoModel> newlistVipuserinfo = new ArrayList<VipuserinfoModel>();
		List<Integer> integers = new ArrayList<Integer>();
		int count = 0;
		int num = vipInfo.size();
		for (int j = 1; j <= day; j++) {
			if (num > 0) {
				for (int i = 0; i < vipInfo.size(); i++) {
					int len = vipInfo.get(i).getOpDate().lastIndexOf(".");
					String nameString = vipInfo.get(i).getOpDate().substring(len + 1);// 2.3===>>>>3
					if (String.valueOf(j).equals(nameString)) {
						count = 1;//
						num = vipInfo.size();
						vipInfo.remove(0);
						break;
					}
				}
			}
			
			if (count != 1) {
				integers.add(j);
			}
			count = 0;
		}
		for (int i = 0; i < integers.size(); i++) {
			if (integers.get(i) < 10) {
				VipuserinfoModel addVip = new VipuserinfoModel();
				addVip.setCreateTime(day1 + "0" + integers.get(i) + " 00:00:01");// 2018-02-08
				// 15:15:50
				addVip.setId(0);// 当月总燃油收入量设置为0
				newlistVipuserinfo.add(addVip);
			} else {
				VipuserinfoModel addVip = new VipuserinfoModel();
				addVip.setCreateTime(day1 + integers.get(i) + " 00:00:01");
				addVip.setId(0);// 当月总燃油收入量设置为0
				newlistVipuserinfo.add(addVip);
			}
		}
		return newlistVipuserinfo;
	}
	// 月
	/**
	 * 当月总燃油收入量 返回结果为少哪几天的集合
	 * 
	 * @param sffList
	 * @return
	 */
	public static List<Oilrecord> yearMuchDataIsOk(List<Oilrecord> sffList) {
		Date date2 = new Date();
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH) + 1;// 当到当前月份
		String day1 = format2.format(date2).substring(0, 5);// 2018-02-
		SimpleDateFormat format = new SimpleDateFormat("M");
		String curdate = format.format(date2);// 2018年2月8日09:16:22===》》》2
		int day = Integer.parseInt(curdate);
		List<Oilrecord> newlistOilrecord = new ArrayList<Oilrecord>();

		List<Integer> integers = new ArrayList<Integer>();
		int count = 0;
		int num = sffList.size();
		if (month <= 9) {
			for (int j = 1; j <= day; j++) {
				if (num > 0) {
					for (int i = 0; i < sffList.size(); i++) {
						int len = sffList.get(i).getOpDate().lastIndexOf(".");
						String nameString = sffList.get(i).getOpDate().substring(len - 1, len);// 2.3===>>>>3
						if (String.valueOf(j).equals(nameString)) {
							count = 1;//
							num = sffList.size();
							sffList.remove(0);
							break;
						}
					}
				}

				if (count != 1) {
					integers.add(j);
				}
				count = 0;
			}
		} else {
			for (int j = 1; j <= day; j++) {
				if (num > 0) {
					for (int i = 0; i < sffList.size(); i++) {
//						int len = sffList.get(i).getOpDate().lastIndexOf(".");
//						String nameString = sffList.get(i).getOpDate().substring(len - 2, len);// 2.3===>>>>3
//						System.out.println(nameString + "nameString");
						if (String.valueOf(j).equals(sffList.get(i).getMonthDate())) {
							count = 1;//
							num = sffList.size();
							sffList.remove(0);
							break;
						}
					}
				}

				if (count != 1) {
					integers.add(j);
				}
				count = 0;
			}
		}
		for (int i = 0; i < integers.size(); i++) {
			if (integers.get(i) < 10) {
				Oilrecord addoil = new Oilrecord();// 2018-01 00:00:01
				addoil.setOperationTime(day1 + "0" + integers.get(i) + "-01 00:00:01");// 2018-02-08 15:15:50
				addoil.setIncome(0);// 当月总燃油收入量设置为0
				addoil.setExpenditure(0);
				addoil.setId(0);
				newlistOilrecord.add(addoil);
			} else {
				Oilrecord addoil = new Oilrecord();
				addoil.setOperationTime(day1 + integers.get(i) + "-01 00:00:01");
				addoil.setIncome(0);// 当月总燃油收入量设置为0
				addoil.setExpenditure(0);
				addoil.setId(0);
				newlistOilrecord.add(addoil);
			}
		}
		return newlistOilrecord;
	}
	public static List<ExtractApply> yearMuchDataIsOkExtractApply(List<ExtractApply> sffList) {
		Date date2 = new Date();
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH) + 1;// 当到当前月份
		String day1 = format2.format(date2).substring(0, 5);// 2018-02-
		SimpleDateFormat format = new SimpleDateFormat("M");
		String curdate = format.format(date2);// 2018年2月8日09:16:22===》》》2
		int day = Integer.parseInt(curdate);
		List<ExtractApply> newlistOilrecord = new ArrayList<ExtractApply>();
		
		List<Integer> integers = new ArrayList<Integer>();
		int count = 0;
		int num = sffList.size();
		if (month <= 9) {
			for (int j = 1; j <= day; j++) {
				if (num > 0) {
					for (int i = 0; i < sffList.size(); i++) {
						int len = sffList.get(i).getOpDate().lastIndexOf(".");
						String nameString = sffList.get(i).getOpDate().substring(len - 1, len);// 2.3===>>>>3
						if (String.valueOf(j).equals(nameString)) {
							count = 1;//
							num = sffList.size();
							sffList.remove(0);
							break;
						}
					}
				}
				
				if (count != 1) {
					integers.add(j);
				}
				count = 0;
			}
		} else {
			for (int j = 1; j <= day; j++) {
				if (num > 0) {
					for (int i = 0; i < sffList.size(); i++) {
//						int len = sffList.get(i).getOpDate().lastIndexOf(".");
//						String nameString = sffList.get(i).getOpDate().substring(len - 2, len);// 2.3===>>>>3
//						System.out.println(nameString + "nameString");
						if (String.valueOf(j).equals(sffList.get(i).getMonthDate())) {
							count = 1;//
							num = sffList.size();
							sffList.remove(0);
							break;
						}
					}
				}
				
				if (count != 1) {
					integers.add(j);
				}
				count = 0;
			}
		}
		for (int i = 0; i < integers.size(); i++) {
			if (integers.get(i) < 10) {
				ExtractApply addoil = new ExtractApply();// 2018-01 00:00:01
				addoil.setApproveData(Constants.getStringDateTime((day1 + "0" + integers.get(i) + "-01 00:00:01")));// 2018-02-08 15:15:50
				addoil.setAmountDrawn(0);// 当月总燃油收入量设置为0
				newlistOilrecord.add(addoil);
			} else {
				ExtractApply addoil = new ExtractApply();// 2018-01 00:00:01
				addoil.setApproveData(Constants.getStringDateTime((day1+ integers.get(i) + "-01 00:00:01")));// 2018-02-08 15:15:50
				addoil.setAmountDrawn(0);// 当月总燃油收入量设置为0
				newlistOilrecord.add(addoil);
			}
		}
		return newlistOilrecord;
	}

	public static List<Tyredatails> yearChangeTyredatails(List<Tyredatails> tyre) {
		Date date2 = new Date();
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH) + 1;// 当到当前月份
		String day1 = format2.format(date2).substring(0, 5);// 2018-02-
		SimpleDateFormat format = new SimpleDateFormat("M");
		String curdate = format.format(date2);// 2018年2月8日09:16:22===》》》2
		int day = Integer.parseInt(curdate);
		List<Tyredatails> newlistOilrecord = new ArrayList<Tyredatails>();

		List<Integer> integers = new ArrayList<Integer>();
		int count = 0;
		int num = tyre.size();
		if (month <= 9) {
			for (int j = 1; j <= day; j++) {
				if (num > 0) {
					for (int i = 0; i < tyre.size(); i++) {
//						int len = tyre.get(i).getOpDate().lastIndexOf(".");
//						String nameString = tyre.get(i).getOpDate().substring(len - 1, len);// 2.3===>>>>3
//						System.out.println(nameString + "nameString");
						if (String.valueOf(j).equals(tyre.get(i).getMonthDate())) {
							count = 1;//
							num = tyre.size();
							tyre.remove(0);
							break;
						}
					}
				}

				if (count != 1) {
					integers.add(j);
				}
				count = 0;
			}
		} else {
			for (int j = 1; j <= day; j++) {
				if (num > 0) {
					for (int i = 0; i < tyre.size(); i++) {
						int len = tyre.get(i).getOpDate().lastIndexOf(".");
						String nameString = tyre.get(i).getOpDate().substring(len - 2, len);// 2.3===>>>>3
						if (String.valueOf(j).equals(nameString)) {
							count = 1;//
							num = tyre.size();
							tyre.remove(0);
							break;
						}
					}
				}

				if (count != 1) {
					integers.add(j);
				}
				count = 0;
			}
		}
		for (int i = 0; i < integers.size(); i++) {
			if (integers.get(i) < 10) {
				Tyredatails addoil = new Tyredatails();// 2018-01 00:00:01
				addoil.setOperationData(day1 + "0" + integers.get(i) + "-01 00:00:01");// 2018-02-08 15:15:50
				addoil.setIncome(0);// 当月总燃油收入量设置为0
				addoil.setExpenditure(0);
				addoil.setId(0);
				newlistOilrecord.add(addoil);
			} else {
				Tyredatails addoil = new Tyredatails();
				addoil.setOperationData(day1 + integers.get(i) + "-01 00:00:01");
				addoil.setIncome(0);// 当月总燃油收入量设置为0
				addoil.setExpenditure(0);
				addoil.setId(0);
				newlistOilrecord.add(addoil);
			}
		}
		return newlistOilrecord;
	}

	public static List<Vipuserinfo> yearChangeVipuserinfos(List<Vipuserinfo> vipInfo) {
		Date date2 = new Date();
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH) + 1;// 当到当前月份
		String day1 = format2.format(date2).substring(0, 5);// 2018-02-
		SimpleDateFormat format = new SimpleDateFormat("M");
		String curdate = format.format(date2);// 2018年2月8日09:16:22===》》》2
		int day = Integer.parseInt(curdate);
		List<Vipuserinfo> newlistOilrecord = new ArrayList<Vipuserinfo>();

		List<Integer> integers = new ArrayList<Integer>();
		int count = 0;
		int num = vipInfo.size();
		if (month <= 9) {
			for (int j = 1; j <= day; j++) {
				if (num > 0) {
					for (int i = 0; i < vipInfo.size(); i++) {
//						int len = vipInfo.get(i).getOpDate().lastIndexOf(".");
//						String nameString = vipInfo.get(i).getOpDate().substring(len - 1, len);// 2.3===>>>>3
//						System.out.println(nameString + "nameString");
						if (String.valueOf(j).equals(vipInfo.get(i).getMonthDate())) {
							count = 1;//
							num = vipInfo.size();
							vipInfo.remove(0);
							break;
						}
					}
				}

				if (count != 1) {
					integers.add(j);
				}
				count = 0;
			}
		} else {
			for (int j = 1; j <= day; j++) {
				if (num > 0) {
					for (int i = 0; i < vipInfo.size(); i++) {
						int len = vipInfo.get(i).getOpDate().lastIndexOf(".");
						String nameString = vipInfo.get(i).getOpDate().substring(len - 2, len);// 2.3===>>>>3
						if (String.valueOf(j).equals(nameString)) {
							count = 1;//
							num = vipInfo.size();
							vipInfo.remove(0);
							break;
						}
					}
				}

				if (count != 1) {
					integers.add(j);
				}
				count = 0;
			}
		}
		for (int i = 0; i < integers.size(); i++) {
			if (integers.get(i) < 10) {
				Vipuserinfo addoil = new Vipuserinfo();// 2018-01 00:00:01
				addoil.setCreateTime(day1 + "0" + integers.get(i) + "-01 00:00:01");// 2018-02-08 15:15:50
				addoil.setId(0);
				newlistOilrecord.add(addoil);
			} else {
				Vipuserinfo addoil = new Vipuserinfo();
				addoil.setCreateTime(day1 + integers.get(i) + "-01 00:00:01");
				addoil.setId(0);
				newlistOilrecord.add(addoil);
			}
		}
		return newlistOilrecord;
	}
	public static List<Oilrecord> yearChangeOilrecordsinfos(List<Oilrecord> vipInfo) {
		Date date2 = new Date();
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH) + 1;// 当到当前月份
		String day1 = format2.format(date2).substring(0, 5);// 2018-02-
		SimpleDateFormat format = new SimpleDateFormat("M");
		String curdate = format.format(date2);// 2018年2月8日09:16:22===》》》2
		int day = Integer.parseInt(curdate);
		List<Oilrecord> newlistOilrecord = new ArrayList<Oilrecord>();

		List<Integer> integers = new ArrayList<Integer>();
		int count = 0;
		int num = vipInfo.size();
		if (month <= 9) {
			for (int j = 1; j <= day; j++) {
				if (num > 0) {
					for (int i = 0; i < vipInfo.size(); i++) {
//						int len = vipInfo.get(i).getOpDate().lastIndexOf(".");
//						String nameString = vipInfo.get(i).getOpDate().substring(len - 1, len);// 2.3===>>>>3
//						System.out.println(nameString + "nameString");
						if (String.valueOf(j).equals(vipInfo.get(i).getMonthDate())) {
							count = 1;//
							num = vipInfo.size();
							vipInfo.remove(0);
							break;
						}
					}
				}

				if (count != 1) {
					integers.add(j);
				}
				count = 0;
			}
		} else {
			for (int j = 1; j <= day; j++) {
				if (num > 0) {
					for (int i = 0; i < vipInfo.size(); i++) {
						int len = vipInfo.get(i).getOpDate().lastIndexOf(".");
						String nameString = vipInfo.get(i).getOpDate().substring(len - 2, len);// 2.3===>>>>3
						if (String.valueOf(j).equals(nameString)) {
							count = 1;//
							num = vipInfo.size();
							vipInfo.remove(0);
							break;
						}
					}
				}

				if (count != 1) {
					integers.add(j);
				}
				count = 0;
			}
		}
		for (int i = 0; i < integers.size(); i++) {
			if (integers.get(i) < 10) {
				Oilrecord addoil = new Oilrecord();// 2018-01 00:00:01
				addoil.setOperationTime(day1 + "0" + integers.get(i) + "-01 00:00:01");// 2018-02-08 15:15:50
				addoil.setId(0);
				addoil.setIncome(0);
				addoil.setRemaining(0);
				newlistOilrecord.add(addoil);
			} else {
				Oilrecord addoil = new Oilrecord();
				addoil.setOperationTime(day1 + integers.get(i) + "-01 00:00:01");
				addoil.setId(0);
				addoil.setIncome(0);
				addoil.setRemaining(0);
				newlistOilrecord.add(addoil);
			}
		}
		return newlistOilrecord;
	}

}