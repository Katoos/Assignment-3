package eg.edu.alexu.csd.datastructure.iceHockey.cs;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
public class IceHockey implements IPlayersFinder {
	
	public int area = 0;
	public int rowMax = 0;
	public int rowMin = 0;
	public int colMax = 0;
	public int colMin = 0;
	public char [][] pixels;
	public boolean [][] checkedPixels;
	public Point temp = new Point();
	public Point last = new Point();
	public Point [] arrayOfPoints; 
	ArrayList<Point> setOfPoints = new ArrayList<Point>();
	
	public Point search(final int row, final int column, final char team, final int threshold) {

		if (row < 0 || column < 0 || row >= pixels.length || column >= pixels[0].length) {
			return null; 
		}
		else if (pixels[row][column] != team) {
			checkedPixels[row][column] = true;
			return null;
		}
		else if (checkedPixels[row][column]) {
			return null;
		}
		else {
			if (row > rowMax) {
				rowMax = row;
			}
			if (column > colMax) {
				colMax = column;
			}
			if (row < row) {
				rowMin = row;
			}
			if (column < colMin) {
				colMin = column;
			}
			checkedPixels[row][column] = true;
			search(row, column + 1, team, threshold);
			search(row, column - 1, team, threshold);
			search(row + 1, column, team, threshold);
			search(row - 1, column, team, threshold);
			area = area + 4;
		}
		if (area >= threshold) {
			temp.x = colMax + colMin + 1;
			temp.y = rowMax + rowMin + 1;
			return temp;
		}
		else {
			return null;
		}
		
	}
	
	public Point[] findPlayers(final String[] photo, final int team, final int threshold) {
		int i;
		int j;
		char teamx = (char) (team + '0');
		
		if (photo == null) {
			return null;
		}	
		
		pixels = new char[photo.length][photo[0].length()];
		checkedPixels = new boolean[photo.length][photo[0].length()];
		
		for (i = 0; i < photo.length; i++) {
			for (j = 0; j < photo[0].length(); j++) {
				checkedPixels[i][j] = false;
			}
		}
		for (i = 0; i < photo.length; i++) {
			for (j = 0; j < photo[0].length(); j++) {
				pixels[i][j] = photo[i].charAt(j);
			}
		}

		for (i = 0; i < pixels.length; i++) {
			for (j = 0; (j < pixels[0].length); j++) {
				area = 0;
				rowMax = 0;
				colMax = 0;
				colMin = j;
				rowMin = i;
				last = search(i, j, teamx, threshold);
				if (last != null) {
					setOfPoints.add(new Point(last.x,last.y));
				}
			}
		}
		
		Collections.sort(setOfPoints, new PointCompare());
		arrayOfPoints = new Point[setOfPoints.size()];
		if (arrayOfPoints == null) {
			return null;
		} 
		else {
			arrayOfPoints = setOfPoints.toArray(arrayOfPoints);
		}
		setOfPoints.clear();
		return arrayOfPoints;
	}

}

