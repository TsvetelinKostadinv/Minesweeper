import java.util.Random;
import java.util.Scanner;

public class minesweeper {
	public static int[][] initializeMap(int howManyMines, int[][] emptyMap)
	{
		Random random = new Random();
		for(int i=0; i<emptyMap.length;i++)
		{
			emptyMap[i][0] = -2;
			emptyMap[i][emptyMap[0].length-1] = -2;
			
		}
		for(int i=0;i<emptyMap[0].length;i++)
		{
			emptyMap[0][i] = -2;
			emptyMap[emptyMap.length-1][i] = -2;
		}
		
		for(int i=1; i<emptyMap.length-1; i++)
		{
			for(int j=1; j<emptyMap[0].length-1; j++)
			{
				emptyMap[i][j] = minesAround(i, j, emptyMap);
			}
		}
		for(int i =0;i<=howManyMines;i++ )
		{
			int randX = random.nextInt(emptyMap.length);
			int randY = random.nextInt(emptyMap[0].length);
			while(emptyMap[randX][randY]==-1 || emptyMap[randX][randY]==-2)
			{
				randX = random.nextInt(emptyMap.length);
				randY = random.nextInt(emptyMap[0].length);
			}
			emptyMap[randX][randY] = -1;
		}
		return emptyMap;
	}
	
	public static void showMap(boolean[][] cover, int[][] map)
	{
		for(int i=0; i<map.length; i++)
		{
			for(int j=0; j<map[0].length; j++)
			{
				if(map[i][j]==-2)
				{
					System.out.print("# ");
				}else if(!cover[i][j])
				{
					if(map[i][j]==-1)
					{
						System.out.print("X ");
						
					}else
					{
						System.out.print(minesAround(i, j, map)+" ");
					}
				}else{
					System.out.print("[]");
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
		
		
		System.out.print("How wide should it be:");
		int rangeX = input.nextInt();
		System.out.print("How high should it be:");
		int rangeY = input.nextInt();
		int[][] map = new int[rangeX][rangeY];
		boolean[][] cover = new boolean[rangeX][rangeY];
		
//		for(int i=0; i<map.length;i++)
//		{
//			for(int j=0; j<map[0].length;j++)
//			{
//				cover[i][j] = true;
//			}
//		}
		
		int countMines = rangeX*rangeY/4;
		System.out.println("There are "+countMines+" mines");
		//-2 = border
		//-1 = mine
		//0 = empty
		//number....
		map = initializeMap(countMines, map);
		
		
		showMap(cover, map);
		
	}
}
