package com.purefun.fams.core.bo.generate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.purefun.fams.common.util.CommonUtil;
import com.purefun.fams.common.util.StringUtil;
import com.purefun.fams.core.bo.tool.BoConstant;

/**
 * 生成java版的bo装饰类
 * 
 * @Classname: GenerateJAVABOFiles
 * @Description:
 * @author jiang
 * @date 2020-02-06 16:25:55
 */
public class GenerateJAVABOFiles {
	PrintWriter myFileWriter;
	String path = null;
	String protofileDirectory = null;
	String otwfileDirectory = null;
	String builderfileDirectory = null;
	String bopackageName = null;
	String TargetPath = CommonUtil.SpecialWordUtil.resource;
	final String TAB = CommonUtil.CharUtil.tab;
	final String BO = "bo";
	final String BUILDER = "builder";

	public GenerateJAVABOFiles(String path) {
		this.path = path;
	}

	/**
	 * 把内容输出到文件
	 * 
	 * @MethodName: println
	 * @author jiang
	 * @date 2020-02-06 16:26:52
	 * @param str
	 */
	private void println(String str) {
		this.myFileWriter.println(str);
		System.out.println(str);
	}

	/**
	 * 生成文件的入口
	 * 
	 * @MethodName: genFile
	 * @author jiang
	 * @date 2020-02-06 16:27:21
	 * @param directory bo模板的目录
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void genFile(File directory) throws IOException, ClassNotFoundException {
		File flist[] = directory.listFiles();
		if (flist == null || flist.length == 0) {
			System.out.println("NO BO Found");
		} else {
			for (File f : flist) {
				if (f.isDirectory()) {
					genFile(f);
				} else {
					// 按文件获取类名
					String className = f.getName().substring(0, f.getName().indexOf("."));
					// 跳过非bo
					if (!className.contains(BoConstant.SpecialWord.bo_big)
							|| className.contains(BoConstant.SpecialWord.generate))
						continue;

					String filePath = f.getPath().substring(0, f.getPath().lastIndexOf(CommonUtil.CharUtil.re_slash));
					String targetPath = StringUtil.appendFilePath(TargetPath, true,
							filePath.substring(filePath.indexOf(BoConstant.SpecialWord.com)));
					protofileDirectory = StringUtil.appendFilePath(targetPath, false, BoConstant.FilePath.protofile);
					otwfileDirectory = StringUtil.appendFilePath(targetPath, false, BoConstant.FilePath.otwfile);
					String temp = targetPath.substring(targetPath.indexOf(BoConstant.SpecialWord.com),
							targetPath.length());
					bopackageName = temp.replaceAll(CommonUtil.CharUtil.re_slash + CommonUtil.CharUtil.re_slash,
							CommonUtil.CharUtil.point);
					File tardir = new File(protofileDirectory);
					if (!tardir.exists()) {
						tardir.mkdirs();
					}
					File otwdir = new File(otwfileDirectory);
					if (!otwdir.exists()) {
						otwdir.mkdirs();
					}
					// 指向protofile文件
					myFileWriter = new PrintWriter(
							new FileWriter(
									StringUtil.append(protofileDirectory, className, CommonUtil.FileSuffixUtil.proto)),
							true);
					genProFiles(f);

					// 指向otwfile文件
					myFileWriter = new PrintWriter(
							new FileWriter(StringUtil.append(otwfileDirectory, className, CommonUtil.CharUtil.underline,
									BoConstant.SpecialWord.otw_big, CommonUtil.FileSuffixUtil.java)),
							true);
					genOTWContent(className);

					// 生成proto的编码类
					genBuildFile(StringUtil.append(className, CommonUtil.FileSuffixUtil.proto));
				}
			}
		}
	}

	/**
	 * 根据bo模板，生成proto文件
	 * 
	 * @MethodName: genProFiles
	 * @author jiang
	 * @date 2020-02-06 16:34:44
	 * @param f bo的模板类文件
	 * @return
	 * @throws IOException
	 */
	private void genProFiles(File f) throws IOException {
		// TODO Auto-generated method stub
		String className = f.getName().substring(0, f.getName().indexOf("."));
		String fileName = f.getPath();
		InputStream input = new FileInputStream(f);
		InputStreamReader inp = new InputStreamReader(input, "UTF-8");
		BufferedReader br = new BufferedReader(inp);

		println(new StringBuilder().append("import \"google/protobuf/any.proto\";").toString());
		println("");
		println(new StringBuilder().append("option java_outer_classname = \"").append(className).append("_PRO\";")
				.toString());
		println(new StringBuilder().append("option java_package = \"").append(bopackageName).append(".pro")
				.append("\";").toString());
		println("");
		println(new StringBuilder().append("message ").append(className).append(" {").toString());

		String boName = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".")).replace("\\", ".");
		Field[] fileds = null;
		int count = 4;

		try {
			fileds = Class.forName(boName).getFields();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		println(new StringBuilder(TAB).append("optional string uuid = 1;").toString());
		println(new StringBuilder(TAB).append("optional sint64 boid = 2;").toString());
		println(new StringBuilder(TAB).append("optional string destination = 3;").toString());

		for (Field e : fileds) {
			if (e.getName().equalsIgnoreCase("uuid") || e.getName().equalsIgnoreCase("boid")
					|| e.getName().equalsIgnoreCase("destination"))
				continue;// 先设了该值
			StringBuilder fin = new StringBuilder(TAB).append("optional ");
			if (e.getType().equals(java.lang.String.class)) {
				fin.append("string ").append(e.getName()).append(" = ").append(String.valueOf(count++)).append(";");
			} else if (e.getType().equals(double.class) || e.getType().equals(BigDecimal.class)) {
				fin.append("double ").append(e.getName()).append(" = ").append(String.valueOf(count++)).append(";");
			} else if (e.getType().equals(long.class)) {
				fin.append("sint64 ").append(e.getName()).append(" = ").append(String.valueOf(count++)).append(";");
			} else if (e.getType().equals(boolean.class)) {
				fin.append("bool ").append(e.getName()).append(" = ").append(String.valueOf(count++)).append(";");
			} else if (e.getType().equals(float.class)) {
				fin.append("float ").append(e.getName()).append(" = ").append(String.valueOf(count++)).append(";");
			} else if (e.getType().equals(int.class)) {
				fin.append("int32 ").append(e.getName()).append(" = ").append(String.valueOf(count++)).append(";");
			}
			println(fin.toString());
		}
		println("}");

	}

	/**
	 * 根据proto文件生成对应的编码类
	 * 
	 * @MethodName: genBuildFile
	 * @author jiang
	 * @date 2020-02-06 16:38:36
	 * @param fileName proto文件名
	 */
	private void genBuildFile(String fileName) {
//		String strCmd = "./resource/protoc.exe -I=./" + protofileDirectory + " --java_out=./resource/ ./" + protofileDirectory + fileName;  		
		String strCmd = "D:\\software\\protoc-3.11.4-win64\\bin\\protoc.exe -I=./" + protofileDirectory
				+ " --java_out=./resource/ ./" + protofileDirectory + fileName;
		try {
			Runtime.getRuntime().exec(strCmd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成bo模板的装饰类otw
	 * 
	 * @MethodName: genOTWContent
	 * @author jiang
	 * @date 2020-02-06 16:39:21
	 * @param boName bo的类名
	 * @throws ClassNotFoundException
	 */
	private void genOTWContent(String boName) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		String _proName = boName + "_PRO";
		String _otwName = boName + "_OTW";
		StringBuilder builder = new StringBuilder(_proName).append(".").append(boName).append(".Builder");
		String boClassName = bopackageName + "." + boName;

		Class bo = Class.forName(boClassName);
		Field[] fields = bo.getFields();

		/************* 文件头 ******************/
		println(new StringBuilder("package ").append(bopackageName).append(".otw;").toString());
		println("");

		println(new StringBuilder("import ").append(bopackageName).append(".").append(boName).append(";").toString());
		println(new StringBuilder("import ").append(bopackageName).append(".pro.").append(boName).append("_PRO;")
				.toString());
		println(new StringBuilder("import com.google.protobuf.InvalidProtocolBufferException;").toString());
		println(new StringBuilder("import com.purefun.fams.core.bo.commom.ICommon_OTW;").toString());
		println(new StringBuilder("import com.google.protobuf.Any;").toString());

		/************* 文件头 ******************/
		println("");
		println(new StringBuilder("public class ").append(_otwName).append(" implements ICommon_OTW {").toString());

		// 定义变量
		println(new StringBuilder(TAB).append(builder).append(" ").append(BUILDER).append(" = null;").toString());
		println(new StringBuilder(TAB).append(boName).append(" ").append(BO).append(" = null;").toString());
		println("");

		// 定义构造器1
		println(new StringBuilder(TAB).append("public ").append(_otwName).append("() {").toString());
		println(new StringBuilder(TAB).append(TAB).append(BUILDER).append(" = ").append(_proName).append(".")
				.append(boName).append(".").append("newBuilder();").toString());
		println(new StringBuilder(TAB).append(TAB).append(BO).append("= new ").append(boName).append("();").toString());
		println(new StringBuilder(TAB).append(TAB).append(BUILDER).append(".setUuid(bo.uuid);").toString());
		println(new StringBuilder(TAB).append(TAB).append(BUILDER).append(".setBoid(bo.boid);").toString());
		println(new StringBuilder(TAB).append(TAB).append(BUILDER).append(".setDestination(bo.destination);")
				.toString());
		println(new StringBuilder(TAB).append("}").toString());
		println("");

		// 定义构造器2
		println(new StringBuilder(TAB).append("public ").append(_otwName)
				.append("(byte[] message) throws InvalidProtocolBufferException {").toString());
		println(new StringBuilder(TAB).append(TAB).append(BUILDER).append(" = ").append(_proName).append(".")
				.append(boName).append(".").append("newBuilder();").toString());
		println(new StringBuilder(TAB).append(TAB).append(BO).append("= new ").append(boName).append("();").toString());
		println(new StringBuilder(TAB).append(TAB).append(_proName).append(".").append(boName).append(" receive = ")
				.append(_proName).append(".").append(boName).append(".parseFrom(message);").toString());
		genStructSetMethod(fields);
		println(new StringBuilder(TAB).append("}").toString());
		println("");

		// 定义构造器3
		println(new StringBuilder(TAB).append("public ").append(_otwName).append("(").append(boName).append(" bofrom){")
				.toString());
		println(new StringBuilder(TAB).append(TAB).append(BUILDER).append(" = ").append(_proName).append(".")
				.append(boName).append(".").append("newBuilder();").toString());
		println(new StringBuilder(TAB).append(TAB).append(BO).append("= new ").append(boName).append("();").toString());

		genBOStructSetMethod(fields);
		println(new StringBuilder(TAB).append("}").toString());
		println("");

		// 定义serial
		println(new StringBuilder(TAB).append("public byte[] serial() {").toString());
		println(new StringBuilder(TAB).append(TAB).append("return builder.build().toByteArray();").toString());
		println(new StringBuilder(TAB).append("}").toString());
		println("");

		// 定义@Override
		// builder
		println(new StringBuilder(TAB).append("@Override").toString());
		println(new StringBuilder(TAB).append("public com.google.protobuf.GeneratedMessageV3.Builder getBuilder() { ")
				.toString());
		println(new StringBuilder(TAB).append(TAB).append("return builder;").toString());
		println(new StringBuilder(TAB).append("}").toString());
		println("");

		// bo
		println(new StringBuilder(TAB).append("@Override").toString());
		println(new StringBuilder(TAB).append("public ").append(boName).append(" getBo() { ").toString());
		println(new StringBuilder(TAB).append(TAB).append("return bo;").toString());
		println(new StringBuilder(TAB).append("}").toString());
		println("");

		genMethod(fields);

		genToString(_otwName, fields);
		println("}");
	}

	/**
	 * 获取bo各属性的set方法
	 * 
	 * @MethodName: genBOStructSetMethod
	 * @author jiang
	 * @date 2020-02-06 16:40:16
	 * @param fields bo的各属性
	 */
	private void genBOStructSetMethod(Field[] fields) {
		for (Field field : fields) {
			StringBuilder setmethodName = new StringBuilder();
			String fieldName = field.getName();
			StringBuilder first = new StringBuilder(fieldName.substring(0, 1).toUpperCase());
			StringBuilder last = new StringBuilder(fieldName.substring(1));
			setmethodName.append(first).append(last);
			println(new StringBuilder(TAB).append(TAB).append("set").append(setmethodName).append("(").append("bofrom.")
					.append(fieldName).append(");").toString());
		}
	}

	/**
	 * 生成bo的tostring方法
	 * 
	 * @MethodName: genToString
	 * @author jiang
	 * @date 2020-02-06 16:40:39
	 * @param name   bo名称
	 * @param fields bo的各属性
	 */
	private void genToString(String name, Field[] fields) {
		// TODO Auto-generated method stub
		println(new StringBuilder(TAB).append("public String toString() {").toString());
		println(new StringBuilder(TAB).append(TAB).append("return \"").append(name).append(" [\"+").toString());

		println(new StringBuilder(TAB).append(TAB).append(TAB).append("\"").append("uuid").append(" = \" + ")
				.append("getUuid").append("() +").append("\",\" +").toString());
		println(new StringBuilder(TAB).append(TAB).append(TAB).append("\"").append("boid").append(" = \" + ")
				.append("getBoid").append("() +").append("\",\" +").toString());
		println(new StringBuilder(TAB).append(TAB).append(TAB).append("\"").append("destination").append(" = \" + ")
				.append("getDestination").append("() +").append("\",\" +").toString());

		for (Field field : fields) {
			if (field.getName().equalsIgnoreCase("uuid") || field.getName().equalsIgnoreCase("boid")
					|| field.getName().equalsIgnoreCase("destination"))
				continue;// 先设了该值
			StringBuilder fieldName = new StringBuilder(field.getName());
			StringBuilder methodName = new StringBuilder("get");
			StringBuilder first = new StringBuilder(fieldName.substring(0, 1).toUpperCase());
			StringBuilder last = new StringBuilder(fieldName.substring(1));
			methodName.append(first).append(last);
			println(new StringBuilder(TAB).append(TAB).append(TAB).append("\"").append(field.getName())
					.append(" = \" + ").append(methodName).append("() +").append("\",\" +").toString());
		}
		println(new StringBuilder(TAB).append(TAB).append(" \"]\";").toString());
		println(new StringBuilder(TAB).append("}").toString());

	}

	/**
	 * 生成bo属性的各set方法
	 * 
	 * @MethodName: genStructSetMethod
	 * @author jiang
	 * @date 2020-02-06 16:42:52
	 * @param fields
	 */
	private void genStructSetMethod(Field[] fields) {
		// TODO Auto-generated method stub
		for (Field field : fields) {
			StringBuilder setmethodName = new StringBuilder();
			StringBuilder getmethodName = new StringBuilder();
			String fieldName = field.getName();
			StringBuilder first = new StringBuilder(fieldName.substring(0, 1).toUpperCase());
			StringBuilder last = new StringBuilder(fieldName.substring(1));

			String[] str = fieldName.split("_");
			setmethodName.append(first).append(last);

			if (str.length == 1) {// 没有 _
				getmethodName = setmethodName;
			} else {
				StringBuilder all = new StringBuilder();
				for (String each : str) {
					all.append(each.substring(0, 1).toUpperCase());
					all.append(each.substring(1).toString());
				}
				getmethodName.append(all);
			}

			println(new StringBuilder(TAB).append(TAB).append("set").append(setmethodName).append("(")
					.append("receive.get").append(getmethodName).append("());").toString());
		}
	}

	/**
	 * 生成bo装饰类的各方法
	 * 
	 * @MethodName: genMethod
	 * @author jiang
	 * @date 2020-02-06 16:43:24
	 * @param fields bo的属性名
	 */
	private void genMethod(Field[] fields) {
		// TODO Auto-generated method stub
		for (Field field : fields) {
			Class type = field.getType();
			genGetMethod(field, type);
			genSetMethod(field, type);
		}
	}

	/**
	 * 生成bo的属性的set方法
	 * 
	 * @MethodName: genSetMethod
	 * @author jiang
	 * @date 2020-02-06 16:43:56
	 * @param field 属性
	 * @param type  属性的类型
	 */
	private void genSetMethod(Field field, Class type) {
		// TODO Auto-generated method stub
		String fieldName = field.getName();
		StringBuilder methodName = new StringBuilder();
		StringBuilder all = new StringBuilder("set");

		String[] str = fieldName.split("_");
		if (str.length == 1) {// 没有 _
			methodName = all;
		} else {
			StringBuilder first = new StringBuilder(fieldName.substring(0, 1).toUpperCase());
			StringBuilder last = new StringBuilder(fieldName.substring(1));
			methodName.append("set").append(first).append(last);
		}
		for (String each : str) {
			all.append(each.substring(0, 1).toUpperCase());
			all.append(each.substring(1).toString());
		}

		println(new StringBuilder(TAB).append("public void ").append(methodName).append("(").append(type.getName())
				.append(" ").append(field.getName()).append(") {").toString());
		println(new StringBuffer(TAB).append(TAB).append("bo.").append(field.getName()).append(" = ")
				.append(field.getName()).append(";").toString());
		println(new StringBuffer(TAB).append(TAB).append("builder.").append(all).append("(").append(field.getName())
				.append(");").toString());
		println(new StringBuilder(TAB).append("}").toString());
		println("");
	}

	/**
	 * 生成get方法
	 * 
	 * @MethodName: genGetMethod
	 * @author jiang
	 * @date 2020-02-06 16:44:33
	 * @param field 属性
	 * @param type  属性类型
	 */
	private void genGetMethod(Field field, Class type) {
		// TODO Auto-generated method stub
		String fieldName = field.getName();

		StringBuilder methodName = new StringBuilder();
		StringBuilder all = new StringBuilder("get");

		String[] str = fieldName.split("_");
		if (str.length == 1) {// 没有 _
			methodName = all;
		} else {
			StringBuilder first = new StringBuilder(fieldName.substring(0, 1).toUpperCase());
			StringBuilder last = new StringBuilder(fieldName.substring(1));
			methodName.append("get").append(first).append(last);
		}

		for (String each : str) {
			all.append(each.substring(0, 1).toUpperCase());
			all.append(each.substring(1).toString());
		}
		println(new StringBuilder(TAB).append("public ").append(type.getName()).append(" ").append(methodName)
				.append("() {").toString());
		println(new StringBuilder(TAB).append(TAB).append("return ").append(BUILDER).append(".").append(all)
				.append("()").append(";").toString());
		println(new StringBuilder(TAB).append("}").toString());
		println("");
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		GenerateJAVABOFiles writer = new GenerateJAVABOFiles("src/main/java/");
		writer.path = "src/main/java/";
		writer.genFile(new File(writer.path));
	}
}
