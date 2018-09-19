import java.util.ArrayList;
import java.util.List;

public class Algorithms {
		
	public static void main(String[] args) {
	
		
		List<Point> list = new ArrayList<>();
		list.add(new Point(0,0));
		list.add(new Point(0,20));
		list.add(new Point(2,5));
		list.add(new Point(5,17));
		list.add(new Point(11,4));
		list.add(new Point(16,6));
		list.add(new Point(20,1));
		list.add(new Point(25,0));
		list.add(new Point(25,20));
		
		System.out.println(grandRectangle(list));
		
	}
	
	static int grandRectangle(List<Point> list){
		int airMax = 0;
		int minOrdonne;
		int air;
		
		Point p1 = null;
		Point p2 = null;
		
		for (Point point1 : list) {
			for (Point point2 : list) {
				if(!point1.equals(point2)){
					minOrdonne = minOrdonne(point1, point2, list);
					air = airRectangle(point1.x, point2.x, minOrdonne);
					if(airMax < air){
						airMax = air;
						p1 = point1;
						p2 = point2;
					}
				}
			}
		}
		System.out.println("Point 1 : "+p1.x+","+p1.y);
		System.out.println("Point 2 : "+p2.x+","+p2.y);
		
		return airMax;
	}
	
	static int minOrdonne(Point point1, Point point2,List<Point> list){
		boolean premier = true;
		int ordonne = 0;
		for (Point point : list) {
			if(point.x > point1.x && point.x < point2.x){
				if(premier){
					ordonne = point.y;
					premier = false;
				}else if(ordonne > point.y){
					ordonne = point.y;
				}
			}
		}
		return ordonne;
	}
	
	static int airRectangle(int x1, int x2, int ordonne){
		return (x2-x1)*ordonne;
	}
	
}
