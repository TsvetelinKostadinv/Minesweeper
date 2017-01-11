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
	
	public static void showMap(int[][] cover, int[][] map)
	{
		System.out.print(" ");
		for(int i=0;i<map[0].length; i++)
		{
			if(i<10)
			{
				System.out.print(" "+i+" ");
			}else{
				System.out.print(i+" ");
			}	
		}
		System.out.println();
		for(int i=0; i<map.length; i++)
		{
			System.out.print(i);
			for(int j=0; j<map[0].length; j++)
			{
				if(map[i][j]==-2)
				{
					if(i<10 && j==0)
					{
						System.out.print(" # ");
					}else if(j==0){
						System.out.print("# ");
					}else{
						System.out.print(" # ");
					}
				}else if(cover[i][j]==1)
				{
					if(map[i][j]==-1)
					{
						System.out.print("[X]");
						
					}else
					{
						System.out.print("["+minesAround(i,j,map)+"]");
					}
				}else if(cover[i][j]==0)
				{
					System.out.print("[ ]");
				}else
				{
					System.out.print("[M]");
				}	
			}
			System.out.println();
		}
	}

	
	public static void main(String[] arg)
	{
		Scanner input = new Scanner(System.in);
		
		System.out.print("How wide should it be:");
		int rangeX = input.nextInt();
		System.out.print("How high should it be:");
		int rangeY = input.nextInt();

		int[][] map = new int[rangeX][rangeY];
		int[][] cover = new int[rangeX][rangeY];
		
		boolean boom=false;
		//for map
		//-3 = marked
		//-2 = border
		//-1 = mine
		//0 = empty
		//number....
		
		//for cover
		//-1=marked
		//0=hidden
		//1=open
		for(int i=0; i<map.length;i++)
		{
			for(int j=0; j<map[0].length;j++)
			{
				cover[i][j] = 0;
			}
		}
		int countMines = rangeX*rangeY/10;
		int markedMines=0;
		int marks=0;
		System.out.println("There are "+countMines+" mines(1/10 of the field)");

		map = initializeMap(countMines, map);
		
		
		
		while(!boom)
		{
			showMap(cover, map);
			
			System.out.print("What row is the cell in?");
			int y=input.nextInt();
			System.out.print("What colomn is the cell in?");
			int x=input.nextInt();
			System.out.println("This size includes the borders which are 1 cell on each side");
			System.out.println("What to do with it?");
			System.out.println("[1]Open");
			System.out.println("[2]Mark");
			int action=input.nextInt();
			if(action == 1)
			{
				if(map[x][y]==-2)
				{
					System.out.println("You can't open a border");
				}else if(map[x][y]==-1)
				{
					cover[x][y]=1;
					System.out.println("You opened a mine!!!");
					boom=true;
				}else if(cover[x][y]==-1){
					System.out.println("You can't open a marked cell. Unmark it first.");
				}else{
					System.out.println("Successfully opened");
					cover[x][y]=1;
				}
			}
		
		}
		
	}
}
