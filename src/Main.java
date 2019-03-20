
import java.util.Scanner;

public class wordcount 
{
	public static void main(String[] args)
	{
		int choose;
		Scanner in = new Scanner(System.in);
		while(true) 
		{
			function.menu();
			choose = in.nextInt();
			switch(choose)
			{
			  case 1:  System.out.print("请输入文件名: ");
			  		   String s = in.nextLine();
			  		   String filename = in.nextLine();
			  		   function.input(filename);break;
			  case 2:  System.out.print("请输入一个或多个单词(以空格隔开):  ");
			  		   s = in.nextLine();
			           String word = in.nextLine();
			           function.frequency(word);break;
			  case 3:  System.out.print("请输入k: ");
			           s=in.nextLine();
			           int k = in.nextInt();
			           function.Highwordcount(k);
			           break;
			  case 4:  function.output();break;
			  case 5:  System.out.println("退出成功！");
				  	   System.exit(0);break;
			  default: System.out.println("命令无效！重新输入！");
			}
		}
		
	}

}
