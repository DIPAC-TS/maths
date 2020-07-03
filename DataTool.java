package maths;

public abstract class DataTool {
	public static double interpolate(double[] range, double x, double[] data) {
		int i = 0;
		boolean exit = false;
		while(i < range.length - 1 && exit == false) {
			if(x <= range[i + 1]) {
				exit = true;
			}
			i++;
		}
		double ratio = (x - range[i - 1]) / (range[i] - range[i - 1]);
		return (data[i] - data[i - 1]) * ratio + data[i - 1];
	}
	
	public static double interpolate2D(double[] rangeX, double x, double[] rangeY, double y, double[][] data) {
		double result;
		int i = 0;
		boolean exit = false;
		while(i < rangeY.length - 1 && exit == false) {
			if (y <= rangeY[i + 1]) {
				exit = true;
			}
			i++;
		}
		double ratio, ymin, ymax;
		ratio = (y - rangeY[i - 1]) / (rangeY[i] - rangeY[i - 1]);
		ymin = interpolate(rangeX, x, data[i - 1]);
		ymax = interpolate(rangeX, x, data[i]);
		result = (ymax - ymin) * ratio + ymin;
		return result;
	}
	
	public static double interpolate3D(double rangeX[], double x, double rangeY[], double y, double rangeZ[], double z, double[][][] matrix) {
		double result;
		int i = 0;
		boolean exit = false;
		while(i < rangeZ.length  - 1 && exit == false) {
			if(z < rangeZ[i + 1]) {
				exit = true;
			}
			i++;
		}
		double ratio, zmin, zmax;
		ratio = (x - rangeX[i - 1]) / (rangeX[i] - rangeX[i - 1]);
		zmin = interpolate2D(rangeX, x, rangeY, y, matrix[i - 1]);
		zmax = interpolate2D(rangeX, x, rangeY, y, matrix[i]);
		result = (zmax - zmin) * ratio + zmin;
		return result;
	}
	
	public static void main(String[] args) {
		double[] range1 = {1., 3., 5.};
		double[] range2 = {0., 10., 20.};
		double[][] data = {{12., 14., 16.},
				{9., 11., 13.},
				{4., 5., 6.}};
 		System.out.println(interpolate2D(range1, 5.758, range2, -1., data)); 
	}
}
