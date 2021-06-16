import java.util.*;
public class maze {
		public static void main(String [] args) {
			int maze [][]=  {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
			int []start = {0,4};
			int [] destination = {4,4};
			theMaze1 m = new theMaze1();
			System.out.println("Result " + m.theMaze2(maze, start, destination));
		}
}

class theMaze1 extends finalMaze{

	protected boolean theMaze2(int maze [][], int []start, int [] destination) {

		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(start[0], start[1]));

		maze[start[0]][start[1]] = -1;

		return bfs(q, maze, start, destination) ;
	}
}
interface mazeInt{
	boolean bfs(Queue<Point> q, int maze [][], int []start, int [] destination);
}

abstract class finalMaze implements mazeInt{
	public static int dirs [][] = {{-1,0},{1,0},{0,1},{0,-1}};

	public boolean bfs(Queue<Point> q, int maze [][], int []start, int [] destination) {


		while(!q.isEmpty()) {
			Point p = q.poll();

			for(int i = 0; i < dirs.length; i++) {
				int row = p.row;
				int col = p.col;

				while(row >=0 && row < maze.length && col >= 0 && col < maze[0].length) {
					row += dirs[i][0];
					col += dirs[i][1];
				}
				row -= dirs[i][0];
				col -= dirs[i][1];

				if(row == destination[0] && col == destination[1]) return true;
				if(maze[row][col] == 0) {
					maze[row][col] = -1;
					q.offer(new Point(row, col));
				}

			}
		}
		return false;
	}

}


class Point{
	int row;
	int col;
	public Point(int row,int col){
		this.row = row;
		this.col = col;
	}
}
