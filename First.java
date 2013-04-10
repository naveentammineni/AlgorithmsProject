import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class First {
	static String str = "";
	static int m1=0, m2=0;
	public static void main(String args[]) throws FileNotFoundException{
		File file = new File("input1.txt");
        Scanner s = new Scanner(file);
		int n = s.nextInt();
		int k = s.nextInt();
		do{
		str = s.next();
		 m1 = (int) Math.ceil((2*(double)n)/(3*k));
		 m2 = (int) Math.floor((4*n)/(3*k));
		panels(str,n,k);
		n = s.nextInt();
		k = s.nextInt();
		}while(n!=0 && k!=0);
	}
	static void panels(String s, int n, int k){
		int f[][] = new int[n+1][k+1];
		int p[] = new int[n+1];
		int b[] = new int[k+1];
		int cut[][] = new int[n+1][k+1];

		for(int i = 0;i<f.length;i++){
			for(int j =0;j<f[i].length;j++)
				f[i][j] = 1000;
			p[i] =1000;
		}
		f[0][0] = 0;
		//Iterating for the number of panels
		for(int h = 1; h<=k;h++){
			//Iterating through the string
			for(int a = 0; a<=n;a ++ ){
				//Iterating through the min and max values.
  				for(int i =m1; i <= m2 ;i++){
  					//if the r value goes <0 then making it 0.
					int r=(a-i)>0?(a-i):0;
					//Finding out the minimum value in the particular panel for the particular cut
					if( discord(r,a) + f[r][h-1] < f[a][h]){
						f[a][h] = discord(r,a) + f[r][h-1];
						//Caching the previous better cut in the cut array. Used to print the cuts at the end 
						cut[a][h] = r;
					}
				}
			}
		}
		//Printing the Total Discord
		System.out.print(f[n][k]);
		//Tracking back the cut values and printing it in the cut order.
		int j =k,i =n;
		while(j>0){
			b[j] = cut[i][j];
			i = b[j];
			j--;
		}
		for(int l = 2;l < b.length;l++){
			System.out.print(" "+ Math.abs(b[l] - b[l-1]));
		}
		System.out.println("");
	}
	static int discord(int i,int j){
		int dcount = 0, rcount = 0;
		//Ignoring the discord for elements whose difference is not in min and max 
		if((i<j) && (j-i>=m1 && j-i<=m2)){
			for(int k = i; k < j;k++ ){
				if(str.charAt(k) == 'R')
					rcount++;
				else if(str.charAt(k) == 'D')
					dcount++;
			}
			//returning the discard sqaure
			return (rcount - dcount)*(rcount - dcount);
		}
		//returning some max value if the discord is not compatible for the given inputs.
		return 1000;
	}
}