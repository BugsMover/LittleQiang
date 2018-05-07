import java.util.Scanner;
public class number {
	private static int sca;
	public static void main(String[] args) {
		System.out.println("输入正整数:");
	Scanner scanner = new Scanner(System.in);
	try {
		sca = scanner.nextInt();
	} catch (Exception e) {
	}finally{}
	scanner.close();
	int a = fornumber(sca);
	int b = 0;
	Boolean flag;
    while(flag=true){
    if(a==1){
        System.out.println("true");
        break;
    	  }else if(a>10){	
    	System.out.println(a); 
    	 b = fornumber(a);
         a=b;
         System.out.println(b);
    	  }else{
    		  System.out.println("flase");
    		 break; 
    	  }
      }
	}
	public static int fornumber(int number){
		int d=0;
		String c = String.valueOf(number);
    	char[] cC = c.toCharArray();
    	for(int i =0;i<cC.length;i++){
    	d =(int) (d+Math.pow(Integer.parseInt(String.valueOf(cC[i])), 2));
	}
		return d;
   }
}
