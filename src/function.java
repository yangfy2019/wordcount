
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
	static Map<String,Integer> staff = new HashMap<String, Integer>();//��ʼ�洢
	static List<Entry<String,Integer>> aList = new ArrayList<Entry<String,Integer>>();//�洢��ֵ����������
	
	public static void input(String filename)
	{
		
			try {
				FileInputStream fis = new FileInputStream(filename);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				String temp="";
				String info="";
				while((temp = br.readLine())!=null)
				{
					String[] str = temp.split("([^a-zA-Z])"); //���˳�ֻ������ĸ��
					
					for(int i=0;i<str.length;i++)
					{
						String word = str[i].trim();
						if(word.length()!=0)    //ȥ������Ϊ0����
							staff.put(word, staff.getOrDefault(word, 0)+1);
					}
				}
				br.close();
				rank();//��ֵ����
				System.out.println("�ļ�����ɹ��������...");
			}catch(Exception e) {
				System.out.println("�ļ������ڣ�����������ȷ�ϣ�");
			}
		}
	
	public static void menu()
	{
		System.out.println("*************************************");
		System.out.println("     1-�����ļ���");
		System.out.println("     2-ָ�����ʴ�Ƶͳ��  ");
		System.out.println("     3-��Ƶ��ͳ��");
		System.out.println("     4-���ı����е�����������Ƶ�� ");
		System.out.println("     5-�˳� ");
		System.out.println("*************************************");
		System.out.println();
		System.out.print("��ѡ��");
	}
	
	public static void frequency(String word)  //ָ�����ʴ�Ƶͳ��
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
					System.out.println(str[i]+":\tû���ҵ��õ��ʣ�");
		}
		System.out.println("��ע��*Ϊ5000��������״ͼ�Ǵ�ż��㣬����ֵ�Ϻ�ʵ��ֵ֮����Щ��");
	}
	
	public static void rank()//��ֵ����
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
	
	public static void Highwordcount(int k)//ͳ�Ƹ�Ƶ�ʻ�
	{
		for(int i=0;i<k;i++)
		{
			System.out.println(aList.get(i).getKey()+"\t"+aList.get(i).getValue());
		}
	}
	public static void output()
	{
		Set<Entry<String,Integer>> mapEntries = staff.entrySet();//�÷���������ֵ��ӳ���ϵ��Ϊ����洢����Set������
		
		List<Entry<String,Integer>> aList1 = new ArrayList<Entry<String,Integer>>(mapEntries);
		//���ֵ�������
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
			out.println("total: "+aList.size()); //����ܴ���
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
		System.out.println("��д��result.txt  �����...");
	}
}
