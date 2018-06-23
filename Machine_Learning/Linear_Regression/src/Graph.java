import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Graph extends JPanel{

	private JPanel panel;
	
	private JLabel xAxisLabel;
	private JLabel yAxisLabel;
	
	private JButton draw;
	
	private int dividerWidth;
	private int dividerHeight;
	
	private ArrayList<Point> points;
	
	public Graph(String xAxisLabel, String yAxisLabel, ArrayList<Point> points) {
	
		
		this.setBackground(Color.LIGHT_GRAY);
		
		//Axis Labels
		this.xAxisLabel = new JLabel(xAxisLabel);
		this.yAxisLabel = new JLabel(yAxisLabel);
		
		this.add(this.xAxisLabel);
		this.add(this.yAxisLabel);
	
		this.dividerHeight = this.getHeight() / 10;
		this.dividerWidth = this.getWidth() / 10;
		
		//Points
		this.points = points;
		
		//Button
		this.draw = new JButton("draw");
		this.add(draw);
		this.draw.addActionListener(e -> drawPoints());
	
	}
	
	
	public void drawLine(Graphics g, Line l) {
		g.drawLine( (int) l.getX1(), (int) l.getX2(), (int) l.getY1(), (int) l.getY2());
	}	
	
	
	private void drawPoints() {
		for(Point point : points) {
			drawPoint(getGraphics(), point);
		}
	}
	
	private void drawPoint(Graphics g, Point p) {
		g.fillOval((int) p.getX(), this.getHeight() - (int)  p.getY(), 6, 6);
		g.drawOval((int)  p.getX(), this.getHeight() - (int)  p.getY(), 6, 6);
	}
	
	public JPanel getPanel() {
		return this.panel;
	}
}
