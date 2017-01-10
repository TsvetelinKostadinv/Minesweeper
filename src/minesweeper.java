<<<<<<< HEAD
import java.util.Random;
import java.util.Scanner;

public class minesweeper {
	
	public static void showMap(int[][] map)
	{
		for(int i=0; i<map.length; i++)
		{
			for(int j=0; j<map[0].length; j++)
			{
				if(map[i][j]==-1)
				{
					System.out.print("X");
					
				}else if(map[i][j]==-2)
				{
					System.out.print("#");
				}else
				{
					System.out.print(minesAround(i, j, map));
				}
				
			}
			System.out.println();
		}
		
	}
	
	public static int minesAround(int x,int y,int[][] map)
	{
		int count=0;
			for(int i = -1;i<=1; i++)
			{
				for(int j = -1;j<=1;j++)
				{
					if(map[x+i][y+j] == -1)
					{
						count ++;
					}
				}
		}
		return count;
	}
	
	public static void main(String[] arg)
	{
		Scanner input = new Scanner(System.in);
		Random random = new Random();
		int countMines = 20;
		System.out.print("How wide should it be:");
		int rangeX = input.nextInt();
		System.out.print("How high should it be:");
		int rangeY = input. nextInt();
		int[][] map = new int[rangeX][rangeY];
		//-2 = border
		//-1 = mine
		//0 = empty
		//number....
		for(int i=0; i<map.length;i++)
		{
			map[i][0] = -2;
			map[i][map[0].length-1] = -2;
			
		}
		for(int i=0;i<map[0].length;i++)
		{
			map[0][i] = -2;
			map[map.length-1][i] = -2;
		}
		for(int i=1; i<map.length-1; i++)
		{
			for(int j=1; j<map[0].length-1; j++)
			{
				map[i][j] = minesAround(i, j, map);
			}
		}
		for(int i =0;i<=countMines;i++ )
		{
			int randX = random.nextInt(map.length);
			int randY = random.nextInt(map[0].length);
			while(map[randX][randY]==-1 || map[randX][randY]==-2)
			{
				randX = random.nextInt(map.length);
				randY = random.nextInt(map[0].length);
			}
			map[randX][randY] = -1;
		}
		showMap(map);
		
	}
}
=======
import java.util.Random;
import java.util.Scanner;

public class minesweeper {
	
	public static void showMap(int[][] map)
	{
		for(int i=0; i<map.length; i++)
		{
			for(int j=0; j<map[0].length; j++)
			{
				if(map[i][j]==-1)
				{
					System.out.print("X");
					
				}else if(map[i][j]==-2)
				{
					System.out.print("#");
				}else
				{
					System.out.print(minesAround(i, j, map));
				}
				
			}
			System.out.println();
		}
		
	}
	
	public static int minesAround(int x,int y,int[][] map)
	{
		int count=0;
			for(int i = -1;i<=1; i++)
			{
				for(int j = -1;j<=1;j++)
				{
					if(map[x+i][y+j] == -1)
					{
						count ++;
					}
				}
		}
		return count;
	}
	
	public static void main(String[] arg)
	{
		Scanner input = new Scanner(System.in);
		Random random = new Random();
		int countMines = 20;
		System.out.print("How wide should it be:");
		int rangeX = input.nextInt();
		System.out.print("How high should it be:");
		int rangeY = input. nextInt();
		int[][] map = new int[rangeX][rangeY];
		//-2 = border
		//-1 = mine
		//0 = empty
		//number....
		for(int i=0; i<map.length;i++)
		{
			map[i][0] = -2;
			map[i][map[0].length-1] = -2;
			
		}
		for(int i=0;i<map[0].length;i++)
		{
			map[0][i] = -2;
			map[map.length-1][i] = -2;
		}
		for(int i=1; i<map.length-1; i++)
		{
			for(int j=1; j<map[0].length-1; j++)
			{
				map[i][j] = minesAround(i, j, map);
			}
		}
		for(int i =0;i<=countMines;i++ )
		{
			int randX = random.nextInt(map.length);
			int randY = random.nextInt(map[0].length);
			while(map[randX][randY]==-1 || map[randX][randY]==-2)
			{
				randX = random.nextInt(map.length);
				randY = random.nextInt(map[0].length);
			}
			map[randX][randY] = -1;
		}
		showMap(map);
		
	}
}
>>>>>>> cf08fae88d64efcb5c604cb32adb744e8c95889f
