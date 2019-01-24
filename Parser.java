import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Parser {

	public static void main(String[] args) throws IOException {
		//Reads a text file line by line from the target file location.
		FileReader input = new FileReader("U:\\workspace\\449A1\\src\\test.txt");
		BufferedReader bufRead = new BufferedReader(input);
		
		//Stores the buffer/.txt file line into a string.
		String myLine = null;
		
		//INIT block flags.
		boolean name_flag = false;
		boolean forced_flag = false;
		boolean forb_flag = false;
		boolean near_flag = false;
		boolean mach_flag = false;
		
		int i = 0;
		int j = 0;
		int k = 0;
		int m_row = 0;
		
		//Init String/Int arrays.
		String f_array[] = new String[8];
		String forb_array[] = new String[8];
		String near_array[] = new String[8];
		int mach_array[][] = new int[8][8];


		//Reads txt file line by line checking for flags. Once the flag is set for a hard or soft constraint the lines 
		//are appended to an array removing parenthesis and commas.
		
		//TODO check machine and task bounds before being apended into an array.
		while ((myLine = bufRead.readLine()) != null)
		{    
			//Sets name flag to check if there is a name in the following .txt line.
			if (myLine.contains("Name:")) {
					name_flag = true;
					continue;
			}
			//Outputs a parse error if no name is given in the .txt file.
			if (name_flag) {
				if("".equals(myLine.trim())) {
					System.out.println("Error while parsing input file");
					break;
				}
				name_flag = false;
			}
			
			//Sets flag to append forced partial assignment lines into array on next loop iteration.
			if (myLine.contains("forced partial assignment:")) {
				forced_flag = true;
				continue;
			}
			//Appends forced partial assignments into a String array of 8
			//Outputs a parse error if no name is given in text file.
			if (forced_flag) {
				if(i < 8 && "".equals(myLine.trim()) != true) {
					String str_format = myLine.toString().replace(",","").replace("(","").replace(")","").trim(); //removes , ( )
					f_array[i] = str_format;
					i++;
					continue;
					
					//fills in the rest of the array with "null" without iterating through the file read loop.
				}else if ( "".equals(myLine.trim())){
					for (i = i; i < 8; i++){
						f_array[i] = "null";
					}
					forced_flag = false;
					continue;
				}else if (i == 8) {
					forced_flag = false;
					continue;
					
				}
			}
			//forbiden flag check
			if (myLine.contains("forbidden machine:")) {
				forb_flag = true;
				continue;
			}
			
			if (forb_flag) {
				if(j < 8 && "".equals(myLine.trim()) != true) {
					String str_format = myLine.toString().replace(",","").replace("(","").replace(")","").trim();
					forb_array[j] = str_format;
					j++;
					continue;
					
				}else if ( "".equals(myLine.trim())){
					for (j = j; j < 8; j++){
						forb_array[j] = "null";
					}
					forb_flag = false;
					continue;
				}else if (j == 8) {
					forb_flag = false;
					continue;
					
				}
			}
			//TOO NEAR TASK

			if (myLine.contains("too-near tasks:")) {
				near_flag = true;
				continue;
			}
			
			if (near_flag) {
				if(k < 8 && "".equals(myLine.trim()) != true) {
					String str_format = myLine.toString().replace(",","").replace("(","").replace(")","").trim();
					near_array[k] = str_format;
					k++;
					continue;
					
				}else if ( "".equals(myLine.trim())){
					for (k = k; k < 8; k++){
						near_array[k] = "null";
					}
					near_flag = false;
					continue;
				}else if (k == 8) {
					near_flag = false;
					continue;
					
				}
			}
			

			if (myLine.contains("machine penalties:")) {
				mach_flag = true;
				continue;
			}
			
		    String array1[] = myLine.split(" ");
		    String hold = new String();
		    for (int x = 0; x < array1.length; x++) {
		    	hold = array1[x];
		    	mach_array[m_row][x] = Integer.parseInt(hold);		    	
		    }
		    m_row++;
//			
////		    mach_array[x] = array1;
		    
//		    
		    
		    //TEST CASES
//		    System.out.println(Arrays.deepToString(array1));
		    System.out.println(Arrays.deepToString(mach_array));
		    //System.out.println(array1.length);
		    //System.out.println(myLine);
			//System.out.println("Error while parsing input file");
		}
		//error_close:
			System.out.println(Arrays.deepToString(f_array));
			System.out.println(Arrays.deepToString(forb_array));
			System.out.println(Arrays.deepToString(near_array));
			bufRead.close();
		
			
			
	}

}
	