import java.util.ArrayList;

public class SingleVariableLinearRegression implements Runnable{
	
	private ArrayList<Point> data;
	private ArrayList<Float> inputs;
	private ArrayList<Float> outputs;
	
	private float theta0;
	private float theta1;
	private float tempTheta0;
	private float tempTheta1;
	private boolean done = false;
	private UI ui;
	private Graph graph;
	
	
	private float alpha = (float) 0.000005;
	
	public void setGraph(Graph graph) {
		this.graph = graph;
	}
	
	public void setUI(UI ui) {
		this.ui = ui;
	}
	
	public SingleVariableLinearRegression(ArrayList<Point> data) {
		this.data = data;
		
		inputs = new ArrayList<Float>();
		outputs = new ArrayList<Float>();
		
		for(Point point : data) {
			inputs.add(point.getX());
			outputs.add(point.getY());
		}
		theta0 = 0;
		theta1 = 0;
	}
	
	public void reset() {
		theta0 = 0;
		done = false;
		theta1 = 0;
	}
	
	public float getTheta0() {
		return theta0;
	}
	
	public float getTheta1() {
		return theta1;
	}

	public Line getLine() {
		return new Line(theta1, theta0);
	}
	
	public void step() {
		gradientDissent();
		if(tempCost() < cost()) {
			theta0 = tempTheta0;
			theta1 = tempTheta1;
		} else {
			done = true;
		}
	}

	private float hypothesis(float input) {
		return theta0 + (theta1 * input);
	}
	
	
	private float cost() {
		int m = data.size();
		
		float cost = 0;
	
		for(Point point : data) {
			cost += (hypothesis(point.getX()) - point.getY()) * (hypothesis(point.getX()) - point.getY());
		}
		
		cost *= (0.5 / m);
		
		return cost;
	
	}
	
	private float tempHypothesis(float input) {
		return tempTheta0 + tempTheta1 * input;
	}
	
	private float tempCost() {
		float m = data.size();
		
		float cost = 0;
	
		for(Point point : data) {
			cost += ((tempHypothesis(point.getX()) - point.getY()) * (tempHypothesis(point.getX()) - point.getY()));
		}
		
		cost *= (0.5 / m);
		
		return cost;
	}
	
	private void gradientDissent() {
		float m = data.size();
		
		float derivativeTheta0 = 0;
		
		for(Point point : data) {
			derivativeTheta0 += (hypothesis(point.getX()) - point.getY());
		}
		derivativeTheta0 *= 1/m;
			
		float derivativeTheta1 = 0;
		
		for(Point point : data) {
			derivativeTheta1 += (hypothesis(point.getX()) - point.getY()) * point.getX();
		}
		derivativeTheta1 *= 1/m;

		
		tempTheta0 = theta0 - (alpha * derivativeTheta0);
		tempTheta1 = theta1 - (alpha * derivativeTheta1);
	}

	public boolean getDone() {
		return done;
	}

	@Override
	public void run() {
		int counter = 0;
		while (!getDone() && counter < 5000) {
			graph.step();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(counter % 20 == 0) {
				ui.setTheta0(getTheta0());
				ui.setTheta1(getTheta1());
			}
			counter ++;
		}
		ui.setTheta0(getTheta0());
		ui.setTheta1(getTheta1());
	}
}
