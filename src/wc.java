/**
 * 
 */
package wc;
import java.io.*;
import java.util.Scanner;
/**
 * @author chase_mengdi
 *
 */
public class wc {

	/**
	 * @param args
	 */
	//向文件写入字符串
	public static void writeFile(String str,String outFilePath) {  
		try {  
			FileOutputStream out = new FileOutputStream(outFilePath); // 输出文件路径  
			out.write(str.getBytes());  
			out.close();  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
	}  
	//将文本内容读取为一个字符串
	public static String readFile(String filePath) {
		String output = ""; 
		File file = new File(filePath);
		if(file.exists()){
			if(file.isFile()){
				try{
					BufferedReader input = new BufferedReader (new FileReader(file));
					StringBuffer buffer = new StringBuffer();
					String text;
					while((text = input.readLine()) != null)
						buffer.append(text +"/n");
					output = buffer.toString();          
				}
				catch(IOException ioException){
					System.err.println("File Error!");
				}
			}
			else if(file.isDirectory()){
				String[] dir = file.list();
				output += "Directory contents:/n";

				for(int i=0; i<dir.length; i++){
					output += dir[i] +"/n";
				}
			}
		}
		else{
			System.err.println("Does not exist!");
		}
		return output;
	}
	//利用FileInputStream与Scanner计算行数
	public static int calLine(String filePath) throws FileNotFoundException{
		int lineNum=0;
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		Scanner scanner = new Scanner(fis);
		while(scanner.hasNextLine()){
			scanner.nextLine();
			lineNum++;
		}
		return lineNum;
	}
	//计算单词数
	public static int calWord(String str){
		int wordNum = 0;
		String c,lastC=" ";
		for(int i=0;i<str.length();i++){
			c=str.substring(i, i+1);
			if(c.equals("/")){
				c=str.substring(i, i+2);
				i++;
			}
			//System.out.println(i+"="+c);
			//当前字符不为分隔符，且上一字符为分隔符
			if(((!c.equals(","))||(!c.equals(" "))||(!c.equals("/n"))
					&&(lastC.equals(",")||lastC.equals(" ")||lastC.equals("/n"))))
				wordNum++;
			lastC=c;
		}
		return wordNum;
	}
	//计算字符数
	public static int calChar(String str){
		return str.length();
	}
	//主函数，目前只实现了基本功能
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		int charNum,lineNum,wordNum,argc=args.length;
		//String filePath="C:\\Users\\chase_mengdi\\Desktop\\file.txt";
		String op,filePath,outFilePath,str;


		System.out.println("参数数量："+argc);
		//倒数第二个参数不为“-o”，即默认输出到根目录下result.txt
		if (!args[argc-1].equals("-o")) {
			filePath = args[argc];
			outFilePath="result.txt";
			str=readFile(filePath);
			//System.out.println("文本字符串："+str);
			//System.out.println("filepath is "+filePath);
			charNum=calChar(str);
			lineNum=calLine(filePath);
			wordNum=calWord(str);
			writeFile(filePath,outFilePath);
			//System.out.println("源文件路径成功写入！\n");
			//循环读取参数，计算行数、单词数、字符数
			for (int i = 1; i < argc+1; i++) {
				op = args[i];
				System.out.println("args["+i+"] is "+ args[i]);
				if (op.equals("-c")) {
					System.out.println("字符数："+charNum);
					writeFile("字符数："+charNum,outFilePath);
					//System.out.println("字符数成功写入！");
				}
				if (op.equals("-l")) {
					System.out.println("行数："+lineNum);
					writeFile("行数："+lineNum,outFilePath);
					//System.out.println("行数成功写入！");
				}
				if (op.equals("-w")) {
					System.out.println("单词数："+wordNum);
					writeFile("单词数："+wordNum,outFilePath);
					//System.out.println("单词数成功写入！");
				}
			}
		}
		//倒数第二个字符为“-o”，输入文件为用户指定
		else{
			filePath = args[argc-2];
			outFilePath=args[argc];
			str=readFile(filePath);
			//System.out.println("文本字符串："+str);
			//System.out.println("filepath is "+filePath);
			charNum=calChar(str);
			lineNum=calLine(filePath);
			wordNum=calWord(str);
			writeFile(filePath,outFilePath);
			//System.out.println("源文件路径成功写入！\n");
			for (int i = 1; i < argc-1; i++) {
				op = args[i];
				System.out.println("args["+i+"] is "+ args[i]);
				if (op.equals("-c")) {
					System.out.println("字符数："+charNum);
					writeFile("字符数："+charNum,outFilePath);
					//System.out.println("字符数成功写入！");
				}
				if (op.equals("-l")) {
					System.out.println("行数："+lineNum);
					writeFile("行数："+lineNum,outFilePath);
					//System.out.println("行数成功写入！");
				}
				if (op.equals("-w")) {
					System.out.println("单词数："+wordNum);
					writeFile("单词数："+wordNum,outFilePath);
					//System.out.println("单词数成功写入！");
				}
			}
		}
	}
}
