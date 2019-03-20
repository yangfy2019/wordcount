
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class function 
{
	static Map<String,Integer> staff = new HashMap<String, Integer>();//初始存储
	static List<Entry<String,Integer>> aList = new ArrayList<Entry<String,Integer>>();//存储按值排序后的数据
	
	public static void input(String filename)
	{
		
			try {
				FileInputStream fis = new FileInputStream(filename);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				String temp="";
				String info="";
				while((temp = br.readLine())!=null)
				{
					String[] str = temp.split("([^a-zA-Z])"); //过滤出只含有字母的
					
					for(int i=0;i<str.length;i++)
					{
						String word = str[i].trim();
						if(word.length()!=0)    //去除长度为0的行
							staff.put(word, staff.getOrDefault(word, 0)+1);
					}
				}
				br.close();
				rank();//按值排序
				System.out.println("文件读入成功！请继续...");
			}catch(Exception e) {
				System.out.println("文件不存在！！！请重新确认！");
			}
		}
	
	public static void menu()
	{
		System.out.println("*************************************");
		System.out.println("     1-输入文件名");
		System.out.println("     2-指定单词词频统计  ");
		System.out.println("     3-高频词统计");
		System.out.println("     4-该文本所有单词数量及词频数 ");
		System.out.println("     5-退出 ");
		System.out.println("*************************************");
		System.out.println();
		System.out.print("请选择：");
	}
	
	public static void frequency(String word)  //指定单词词频统计
	{
		String[] str = word.split(" ");
		for(int i=0;i<str.length;i++)
		{
				if(staff.get(str[i])!=null)
				{
					System.out.print(str[i]+"\t");
					for(int j=0;j<staff.get(str[i])/5000+1;j++)
					{
						System.out.print("*");
					}
					System.out.println("\t\t"+staff.get(str[i]));
				}	
				else
					System.out.println(str[i]+":\t没有找到该单词！");
		}
		System.out.println("备注：*为5000，由于柱状图是大概计算，在数值上和实际值之间有些误差。");
	}
	
	public static void rank()//按值排序
	{
		Set<Entry<String,Integer>> mapEntries = staff.entrySet();
		
		aList = new ArrayList<Entry<String,Integer>>(mapEntries);
		
		Collections.sort(aList, new Comparator<Entry<String,Integer>>() {
			 
            @Override
            public int compare(Entry<String, Integer> ele1,
                    Entry<String, Integer> ele2) {
 
                return ele2.getValue().compareTo(ele1.getValue());
            }
        });
 
       /* for(Entry<String,Integer> entry: aList) {
        	System.out.println(entry.getKey() + " : " + entry.getValue());
        } */
	}
	
	public static void Highwordcount(int k)//统计高频词汇
	{
		for(int i=0;i<k;i++)
		{
			System.out.println(aList.get(i).getKey()+"\t"+aList.get(i).getValue());
		}
	}
	public static void output()
	{
		Set<Entry<String,Integer>> mapEntries = staff.entrySet();//该方法将键和值的映射关系作为对象存储到了Set集合中
		
		List<Entry<String,Integer>> aList1 = new ArrayList<Entry<String,Integer>>(mapEntries);
		//按字典序排序
		Collections.sort(aList1, new Comparator<Entry<String,Integer>>() {
			 
            @Override
            public int compare(Entry<String, Integer> ele1,
                    Entry<String, Integer> ele2) {
 
                return ele1.getKey().compareTo(ele2.getKey());
            }
        });
 
		PrintWriter out = null;
		try {
			out = new PrintWriter("result.txt");
			out.println("total: "+aList.size()); //输出总词数
			for(Entry<String,Integer> entry: aList1) 
			{
				out.println(entry.getKey()+"\t"+entry.getValue());
			}
		} catch (FileNotFoundException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		out.close();
		System.out.println("已写到result.txt  请继续...");
	}
}
