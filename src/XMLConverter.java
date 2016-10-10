import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class XMLConverter {

	private static final String inputFile = "C:/Users/Anesu/workspace/Test/src/input.txt"; //path of the file that will be used to generate the xml
	private static final String outputFile = "xmldata.xml"; //path to the file that will contain the xml data
	
	public static void main(String[] args){
		BufferedReader in = null;
		PrintWriter out = null;
		
		try{
			in = new BufferedReader(new FileReader(new File(inputFile)));
			out = new PrintWriter(new File(outputFile));
		}catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
		
		System.out.println("Reading points...");
		List<Point> points = readPoints(in);
		
		System.out.println("Writing points...");
		writePointsToFile(points, out);
		
		System.out.println("done...");
		
		try {
			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	private static List<Point> readPoints(BufferedReader in){
		List<Point> points = new ArrayList<>();
		String line;
		try {
			while((line = in.readLine()) != null){
				if(line.trim().equals(""))
					break;
				
				String[] vals = line.split("\\s+");
				String dx, dy;
				dx = vals[0].trim();
				dy = vals[1].trim();
				
				points.add(new Point(Double.parseDouble(dx), Double.parseDouble(dy)));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return points;
	}
	
	private static void writePointsToFile(List<Point> points, PrintWriter out){
		final String openingTag = "<point>";
		final String closingTag = "</point>";
		
		System.out.println(points.size());
		int count = 0;
		for(int i = 0; i < points.size(); i++){
			Point point = points.get(i);
			String line = openingTag + point.getX() + "," + point.getY() + closingTag;
			
			out.println(line);
			count++;
		}
		
		System.out.println(count);
	}
}
