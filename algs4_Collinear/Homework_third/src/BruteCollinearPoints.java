import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	private int  numofSeg=0;
	private ArrayList<LineSegment> segment = new ArrayList<LineSegment>();
	
	public BruteCollinearPoints(Point[] points){    // finds all line segments containing 4 points
		if(points==null)
			throw new java.lang.NullPointerException();
		int Length=points.length;
		Point[] copy = new Point[Length];
		for (int i = 0; i < Length; i++) {
	          copy[i] = points[i];
		}
		Arrays.sort(copy);
		for(int i=0;i<Length-1;i++){
			if(copy[i]==null)
				throw new java.lang.NullPointerException();
			if(copy[i].compareTo(copy[i+1])==0)
				throw new java.lang.IllegalArgumentException();
		}
		for(int i=0;i<Length-3;i++)
			for(int j=i+1;j<Length-2;j++)
				for(int k=j+1;k<Length-1;k++)
					for(int l=k+1;l<Length;l++){
						if(copy[i].slopeTo(copy[j])==copy[i].slopeTo(copy[l])&&copy[i].slopeTo(copy[j])==copy[j].slopeTo(copy[k])){
								segment.add(new LineSegment(copy[i], copy[l]));
								numofSeg++;
						}	//if
					}
	}
	
	public int numberOfSegments(){	      // the number of line segments
		return numofSeg;
	}
	public LineSegment[] segments() {     // the line segments
		LineSegment[] results = new LineSegment[numofSeg];
		for (int i = 0; i < numofSeg; i++) {
			results[i] = segment.get(i);
		}
		return results;
	} 
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        // read the n points from a file
        In in = new In("./collinear/input9.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

