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
		
		for(int i=1; i<emptyMap.length-1; i++)
		{
			for(int j=1; j<emptyMap[0].length-1; j++)
			{
				if(emptyMap[i][j]!=-1)
				emptyMap[i][j] = minesAround(i, j, emptyMap);
			}
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
					count++;
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
	
	public static int[][] openEmptyAround(int x, int y, int[][] map, int[][] cover)
	{
		for(int i=-1;i<=1;i++)
		{
			for(int j=-1;j<=1;j++)
			{
				if(map[x+i][y+j]==0 && cover[x+i][y+j]==0)
				{
					cover[x+i][y+j]=1;
					cover=openEmptyAround(x+i,y+j,map,cover);
				}else{
					continue;
				}
			}
		}
		return cover;
	}
	

	public static int[][] openCell(int x, int y, int[][] map, int[][] cover){
		int[][] newCover = cover;
		
		if(map[x][y]==-2)
		{
			System.out.println("You can't open a border");
		}else if(map[x][y]==-1)
		{
			newCover[x][y]=1;
			System.out.println("You opened a mine!!!");
		}else if(cover[x][y]==-1){
			System.out.println("You can't open a marked cell. Unmark it first.");
		}else{
			cover[x][y]=1;
			System.out.println("Successfully opened");
			if(map[x][y]==0)
			{
				cover=openEmptyAround(x,y,map,cover);
			}
		}
		
		return newCover ;
	}
	
	public static void main(String[] arg)
	{
		Scanner input = new Scanner(System.in);
		
		System.out.print("How wide should it be:");
		int rangeX = input.nextInt();
		System.out.print("How high should it be:");
		int rangeY = input.nextInt();

		int[][] map = new int[rangeY+2][rangeX+2];
		int[][] cover = new int[rangeY+2][rangeX+2];
		
		int countMines = Math.round(rangeX*rangeY/4);
		boolean boom=false;
		int counterMarks=0;
		int markedMines=0;
		

		System.out.println("There are "+countMines+" mines(1/4 of the field)");
		map = initializeMap(countMines, map);
		
		//for map
		//-2 = border
		//-1 = mine
		//0 = empty
		//number....
		
		//for cover
		//-1=marked
		//0=hidden
		//1=open
		
		
		for(int i=0; i<cover.length;i++)
		{
			for(int j=0; j<cover[0].length;j++)
			{
				cover[i][j] = 0;
			}
		}
		

		while(!boom)
		{
			showMap(cover, map);
			System.out.println("You have "+(countMines+1)+" total marks");
			System.out.println("You have "+((countMines+1)-counterMarks)+"  marks left");
			System.out.print("What row is the cell in?");
			int x=input.nextInt();
			System.out.print("What colomn is the cell in?");
			int y=input.nextInt();
			System.out.println("What to do with it?");
			System.out.println("[1]Open");
			System.out.println("[2]Mark");
			System.out.println("[3]End game");
			int action=input.nextInt();
			if(action == 1)
			{
				cover = openCell(x,y,map,cover);
			}else if(action == 2 )
			{
				if(cover[x][y] != 1)
				{
					if(map[x][y]!=-2)
					{
						if(counterMarks<countMines+1)
						{
							if(cover[x][y]==-1)
							{
								cover[x][y]=0;	
								counterMarks--;
								if(map[x][y]==-1)
								{
									markedMines--;
								}
							}else{
								cover[x][y]=-1;	
								if(map[x][y]==-1)
								{
									markedMines++;
								}
								counterMarks++;
							}
						}else{
							System.out.println("You don't have marks left!");
						}
					}else{
						System.out.println("You can't mark a border");
					}
				}else{
					System.out.println("You can't mark it if it's open");
				}
				
			}else if(action == 3)
			{
				System.out.println("The game is ending");
				for(int i=0; i<cover.length;i++)
				{
					for(int j=0; j<cover[0].length;j++)
					{
						cover[i][j] = 1;
					}
				}
			}else{
				System.out.println("No such function");
			}
			if(markedMines==countMines)
			{
				System.out.println("YOu marked all the mines!!");
				boom=true;
			}
			if(cover[x][y]==1 && map[x][y]==-1)
			{
				boom=true;
			}
		}
		showMap(cover, map);
		
	}
}
