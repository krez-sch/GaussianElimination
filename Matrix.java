package gaussian;

public class Matrix {
	
	/**
	 * The matrix solution rounded to integers.
	 */
	private int[] solutionInt;
	
	/**
	 * The matrix solution.
	 */
	private double[] solution;
	
	/**
	 * The input numbers are integers, but we use double to avoid approximations in math when solving.
	 */
	private double[][] matrix;
	
	/**
	 * The input matrix for the assignment is square, so we can store the row and column size in 1 variable.
	 */
	private final int matrixSize;
	
	public Matrix(int[][] matrix)
	{
		matrixSize  = matrix.length-1;
		solution    = new double[matrixSize];
		this.matrix = new double[matrixSize][matrixSize];
		
		for (int row=0; row<this.matrix.length; row++)
		{
			solution[row] = matrix[0][row];
			for (int col=0; col < matrixSize; col++)
			{
				// it's easier to flip the array for the matrix
				// the first row of input was used for the total, so we increment by 1 and start there
				this.matrix[row][col] = matrix[col+1][row];
			}
		}
	}
	
	public Matrix(double[][] matrix)
	{
		matrixSize  = matrix.length-1;
		solution    = matrix[0];
		this.matrix = new double[matrixSize][matrixSize];
		
		for (int row=0; row<matrixSize; row++)
		{
			for (int col=0; col < matrixSize; col++)
			{
				// it's easier to flip the array for the matrix
				// the first row of input was used for the total, so we increment by 1 and start there
				this.matrix[row][col] = matrix[col+1][row];
			}
		}
	}
	
	/**
	 * Solve the matrix using Gaussian Elimination
	 */
	private void solve()
	{
		double baseDiag, baseRow;
		
		/*
		 * Set all values in the matrix not along the diagonal equal to 0, adjust the row accordingly.
		 */
		for (int run=0; run < matrixSize; run++)
		{
			baseDiag = matrix[run][run];
			for (int row=0; row < matrixSize; row++)
			{
				if (row != run)
				{
					baseRow = matrix[row][run] / baseDiag;
					for (int col=0; col < matrixSize; col++)
					{
						matrix[row][col] -= matrix[run][col] * baseRow;
					}
					
					solution[row] -= solution[run] * baseRow;
				}
			}
		}
		
		solutionInt = new int[matrixSize];
		
		/*
		 * Reduce all values in the matrix to 1, the resulting numbers will be the final answer.
		 * The numbers given will result in integers, so we can round those accordingly.
		 */
		for (int pos=0; pos < matrixSize; pos++)
		{
			solution[pos]     = matrix[pos][pos];
			matrix[pos][pos] /= matrix[pos][pos];
			solutionInt[pos]  = (int) Math.round(solution[pos]);
		}
	}
	
	public int[] getSolution()
	{
		if (solutionInt == null)
		{
			solve();
		}
		return solutionInt;
	}

}
