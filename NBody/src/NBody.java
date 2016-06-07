import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import princeton.StdAudio;
import princeton.StdDraw;

public class NBody extends Application {

	public static final double G = 6.67E-11;

	public double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1-x2, 2)+Math.pow(y1-y2, 2));
	}


	public double force(double m1, double m2, double r) {
		return (1.0 * G * m1 * m2) / (1.0 * r * r);
	}

	/**
	 * Returns the x positions and y positions of bodies
	 * @param totalTime The total amount of universe time to run for
	 * @param timeStep The value of delta t in the equations to calculate position
	 * @param info The scanner with info about the initial conditions of the
	 * bodies
	 * @return an array whose first element is the x positions of the bodies,
	 * and whose second element is the y positions of the bodies at time
	 * t = totalTime
	 */
	public double[][] positions(Scanner info, int totalTime, int timeStep) {

		int N = info.nextInt();
		double univR = info.nextDouble();
		System.out.println("Number of Bodies:  " + N);

		double[] px = new double[N];
		double[] py = new double[N];
		double[] vx = new double[N];
		double[] vy = new double[N];
		double[] fx = new double[N];
		double[] fy = new double[N];
		double[] mass = new double[N];
		String[] image = new String[N];

		double tempForce = 0.0;
		double tempDist = 0.0;
		double tempAx = 0.0;
		double tempAy = 0.0;
		int timeStepNum;

		for (int i = 0 ; i < N ; i++){
			px[i] = info.nextDouble();
			py[i] = info.nextDouble();
			vx[i] = info.nextDouble();
			vy[i] = info.nextDouble();
			mass[i] = info.nextDouble();
			image[i] = info.next();
		}

		if (totalTime % timeStep == 0){
			timeStepNum = totalTime / timeStep;
		} else {
			timeStepNum = totalTime / timeStep + 1;
		}

		for (int j = 0 ; j < timeStepNum ; j++) {
			StdDraw.picture(.5, .5, "data/starfield.jpg");
			StdDraw.setXscale(-univR, univR);
			StdDraw.setYscale(-univR, univR);
			for (int i = 0 ; i < N ; i++){
				StdDraw.picture(px[i],py[i],"data/"+image[i]);
				fx[i] = 0.0;
				fy[i] = 0.0;
				for (int k = 0 ; k < N ; k++){
					if (!(k==i)) {
						tempDist = distance(px[i], py[i], px[k], py[k]);
						tempForce = force(mass[i], mass[k], tempDist);

						fx[i] += tempForce * (px[k]-px[i])/tempDist;
						fy[i] += tempForce * (py[k]-py[i])/tempDist;
					}
				}
				tempAx = fx[i] / mass[i];
				tempAy = fy[i] / mass[i];

				vx[i] += timeStep * tempAx;
				vy[i] += timeStep * tempAy;

				px[i] += timeStep * vx[i];
				py[i] += timeStep * vy[i];
				StdDraw.show();
			}
		}
		double[][] output = new double[N][2]; 

		for (int i = 0 ; i < N ; i++){
			output[i][0] = px[i];
			output[i][1] = py[i];
		}


		return output;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws IOException {
		Scanner info = openFile();
		int time = 100000;
		int dt = 25000;

		System.out.println("Total Time: " + time);
		System.out.println("Time Step : " + dt);

		if (info != null) {
			//StdAudio.play("data/2001.mid");
			NBody myNBody = new NBody();
			double[][] results = myNBody.positions(info, time, dt);
			for(int i = 0; i < results.length; i++) {
				for(int j = 0; j < results[i].length; j++) {
					System.out.print(results[i][j]+" ");
				}
				System.out.println();
			}
			//StdDraw.clear();
			//StdAudio.close();
		}
	}

	/**
	 * Displays file chooser for browsing in the project directory. and opens
	 * the selected file
	 *
	 * @return a new Scanner that produces values scanned from the selected
	 *         file. null if file could not be opened or was not selected
	 * @throws IOException 
	 */
	public static Scanner openFile() throws IOException {
		System.out.println("Opening file chooser");
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Configuration File");
		chooser.setInitialDirectory(new File("data"));
		File file = chooser.showOpenDialog(new Stage());

		if (file != null) {

			try {
				System.out.println("Opening: " + file.getName() + "");
				return new Scanner(file);
			}
			catch (FileNotFoundException fnf) {
				throw new RuntimeException(fnf);
			}
		}
		else {
			System.out.println("File open canceled");    
			return null;
		}
	}
}

//https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
//http://stackoverflow.com/questions/225337/how-do-i-split-a-string-with-any-whitespace-chars-as-delimiters
