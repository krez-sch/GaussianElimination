package gaussian;

import java.io.*;
import java.util.ArrayList;

public class Main
{
	public static void main(String[] args)
	{
		BufferedReader fileReader;
		PrintWriter fileWriter;
		
		try
		{
			System.out.print("Reading file...  ");
			fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("input.txt"))));
			
			String input;
			ArrayList<String> lines = new ArrayList<String>();
			
			while ((input = fileReader.readLine()) != null) {
				input = input.strip();
				if (!input.isEmpty())
				{
					lines.add(input);
				}
			}
			
			fileReader.close();
			System.out.println("Done.");
			
			int[][] potions = new int[lines.size()][];
			String[] temp;
			for (int row=0; row < potions.length; row++)
			{
				temp = lines.get(row).split(":");
				potions[row] = new int[temp.length];
				for (int col=0; col < potions[row].length; col++)
				{
					potions[row][col] = Integer.parseInt(temp[col]);
				}
			}
			
			Matrix witch = new Matrix(potions);
			int[] finalPotions = witch.getSolution();

			System.out.print("Writing file...  ");
			fileWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File("output.txt"))));
			for (int p : finalPotions)
			{
				fileWriter.println(p);
			}
			
			fileWriter.close();
			System.out.println("Done.");
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e);
		}
	}
}
