import java.util.ArrayList;

public class SingleVariableLinearRegression {
	
	private ArrayList<Point> data;
	private ArrayList<Float> inputs;
	private ArrayList<Float> outputs;
	
	private float theta0;
	private float theta1;
	
	public SingleVariableLinearRegression(ArrayList<Point> data) {
		
		this.data = data;
		
		inputs = new ArrayList<Float>();
		outputs = new ArrayList<Float>();
		
		for(Point point : data) {
			inputs.add(point.getX());
			outputs.add(point.getY());
		}
	}

	private float hypothesis(float input) {
		return theta0 + theta1 * input;
	}
	
	private float cost() {
		int m = data.size();
		
		float cost = 0;
	
		for(Point point : data) {
			cost += hypothesis(point.getX() - point.getY());
		}
		
		cost *= (0.5 / m);
		
		return cost;
	}
}
