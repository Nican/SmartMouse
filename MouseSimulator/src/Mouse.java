import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Mouse {

	public static Runtime runtime = Runtime.getRuntime();
	public Process process;

	public Point position = new Point(MouseMaze.MAZE_BLOCK / 2,
			MouseMaze.MAZE_BLOCK / 2);
	public double rotation = 0.0;

	public int size = MouseMaze.UNITS_PER_MM * 100;

	public MouseSensor frontIR;
	public MouseSensor frontRightIR;
	public MouseSensor frontLeftIR;
	public MouseSensor backRightIR;
	public MouseSensor backLeftIR;
	private Simulator simulator;

	PrintStream outStream;

	public Mouse(Simulator simulator) {
		this.simulator = simulator;

		String programPath = "../SmartMouse/Debug/SmartMouse";

		if (System.getProperty("os.name").contains("Windows")) {
			programPath += ".exe";
		}

		try {
			process = runtime.exec(programPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		outStream = new PrintStream(process.getOutputStream());

		frontIR = getNewSensor(1.0, 0.0, 0.0);

		frontRightIR = getNewSensor(0.8, 0.4, -Math.PI / 2);
		frontLeftIR = getNewSensor(0.8, -0.4, Math.PI / 2);
		backRightIR = getNewSensor(-0.8, 0.4, -Math.PI / 2);
		backLeftIR = getNewSensor(-0.8, -0.4, Math.PI / 2);

	}

	/**
	 * Gets a new sensor given the ratio positions
	 * 
	 * @param x
	 *            ratio from -1.0 to 1.0
	 * @param y
	 *            ratio from -1.0 to 1.0
	 * @return
	 */
	public MouseSensor getNewSensor(double x, double y, double rotation) {

		x *= ((double) size) / 2;
		y *= ((double) size) / 2;

		return new MouseSensor(this, new Point2D.Double(x, y), rotation);
	}

	public void readFromProcess() {
		// Scanner errScanner = new Scanner(process.getErrorStream());

		// while (errScanner.hasNextLine()) {
		// System.err.println(errScanner.nextLine());
		// }

		Scanner scanner = new Scanner(process.getInputStream());

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();

			String key = line.substring(0, 3);
			String value = line.substring(3);

			if (key.equals("po:")) {
				readPosition(value);
				writeSensors();
			} else {
				System.out.println(line);
			}

			simulator.mazeDraw.repaint();
		}

	}

	private void writeSensors() {
		outStream.print("v:");
		outStream.print(frontIR.getTracedLineLength(simulator.maze) + ",");
		outStream.print(frontLeftIR.getTracedLineLength(simulator.maze) + ",");
		outStream.print(backLeftIR.getTracedLineLength(simulator.maze) + ",");
		outStream.print(backRightIR.getTracedLineLength(simulator.maze) + ",");
		outStream
				.print(frontRightIR.getTracedLineLength(simulator.maze) + "\n");

		outStream.flush();
	}

	private void readPosition(String value) {
		String values[] = value.split(",");

		position.x = Integer.parseInt(values[0]);
		position.y = Integer.parseInt(values[1]);
		rotation = Double.parseDouble(values[2]);
	}

}
