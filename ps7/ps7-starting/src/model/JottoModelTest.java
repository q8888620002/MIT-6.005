package model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;

public class JottoModelTest {
		
		// Test the makeGuess in JottoModel with input crazy to puzzle #16952, secret word "cargo".
		@Test
		public void makeCorrectGuessTest1() throws MalformedURLException{
			JottoModel jottoModel = new JottoModel();
			String request = jottoModel.makeGuess("crazy");
			String response = "guess 3 1";
			assertEquals(response,request);
			
		}
		
		// Test the makeGuess in JottoModel with input crazy to puzzle #16952, secret word "cargo".
		@Test
		public void makeCorrectGuessTest2() throws MalformedURLException{
			JottoModel jottoModel = new JottoModel();
			String request = jottoModel.makeGuess("cramp");
			String response = "guess 3 1";
					
			assertEquals(response,request);
		}
		
		// Test the makeGuess in JottoModel with upper class input Amigo to puzzle #16952, secret word "cargo".
		@Test
		public void makeCorrectGuessTest3() throws MalformedURLException{
			JottoModel jottoModel = new JottoModel();
			String request = jottoModel.makeGuess("Amigo");
			String response = "error 2: Invalid guess. Length of guess != 5 or guess is not a dictionary word.";
							
			assertEquals(response,request);
		}
		
		// Test the error 2 (length of guess != 5) handler of Jotto model 
		@Test
		public void error2Test1() throws MalformedURLException{
			JottoModel jottoModel = new JottoModel();
			String requset = jottoModel.makeGuess("elephant");
			assertEquals("error 2: Invalid guess. Length of guess != 5 or guess is not a dictionary word."
					, requset);
		}
		
		// Test the error 2 (length of guess != 5) handler of Jotto model 
			@Test
		public void error2Test2() throws MalformedURLException{
			JottoModel jottoModel = new JottoModel();
			String requset = jottoModel.makeGuess("people");
			assertEquals("error 2: Invalid guess. Length of guess != 5 or guess is not a dictionary word."
					, requset);
			}
			
		// Test the error 2 (guess is not in a dictionary word) handler of Jotto model 
	    @Test
		public void error2Test3() throws MalformedURLException{
	    	JottoModel jottoModel = new JottoModel();
			String requset = jottoModel.makeGuess("okob");
			assertEquals("error 2: Invalid guess. Length of guess != 5 or guess is not a dictionary word."
					, requset);
			}
						
		// Test the error 2 (guess is not in a dictionary word) handler of Jotto model 
		@Test
		public void error2Test4() throws MalformedURLException{
			JottoModel jottoModel = new JottoModel();
			String requset = jottoModel.makeGuess("12345");
			assertEquals("error 2: Invalid guess. Length of guess != 5 or guess is not a dictionary word."
					, requset);
			}
		
		// Test the error 0 (guess is not in a dictionary word) handler of Jotto model 
		@Test
		public void error0Test5() throws MalformedURLException{
			JottoModel jottoModel = new JottoModel();
			String requset = jottoModel.makeGuess("@pple");
			assertEquals("error 2: Invalid guess. Length of guess != 5 or guess is not a dictionary word.", requset);
			}
		
		// Test the error 2 (length of guess != 5 ) handler of Jotto model 
		@Test
		public void error2Test6() throws MalformedURLException{
			JottoModel jottoModel = new JottoModel();
			String requset = jottoModel.makeGuess("*bea");
			assertEquals("error 2: Invalid guess. Length of guess != 5 or guess is not a dictionary word.", requset);
			}
		
		// Test error response handler 
				@Test
				public void ErrorHandleResponse(){
					JottoModel jottoModel =  new JottoModel();
					String[] results = new String[2];
					results[0] = "Invalid guess ";
					results[1] = "Try again";
					assertArrayEquals(results
							,jottoModel.handleInput("error 2: Invalid guess. Length of guess "
									+ "!= 5 or guess is not a dictionary word."));
					
					// Test error 0:  illegal formatted URL request 
					
					String[] results2 = new String[2];
					 results2[0] = "Illegal request formatted";
					 results2[1] = "Try again";
					assertArrayEquals(results2
							,jottoModel.handleInput("error 0: Ill-formatted request."));
					
					// Test error 1: Non puzzle ID
					String[] result3 = new String[2];
					result3[0] = "No such puzzle ID, please enter a new one";
					result3[1] = "Try again";
					assertArrayEquals(
							result3
							,jottoModel.handleInput("error 1: Non-number puzzle ID."));
				}

				
				//Test correct guess 
				@Test
				public void correctGuess(){
					JottoModel jottoModel =  new JottoModel();
					String[] reStrings = new String[2];
					reStrings[0] = "3";
					reStrings[1] = "2";
					assertArrayEquals(reStrings
							,jottoModel.handleInput("guess 3 2"));
					
					String[] reslut = new String[1];
					reslut[0] = "You are f***king awesome!!";
					 assertArrayEquals(reslut
							 ,jottoModel.handleInput("guess 5 5"));
				}
				
				 @Test
				    public void test1() throws IOException {
				        //Tests when invalid guess whose length is too long.
				        String[] e = new String[2];
				        e[0] =  "Invalid guess ";
				        e[1] = "Try again";
				        JottoModel jottomod = new JottoModel(16952);
				        String[] observed = jottomod.handleInput(jottomod.makeGuess("sedikit"));
				        assertArrayEquals(e, observed);   
				    }
				    
		    //    @Test
			//	    public void test2() throws IOException {
			//	        //Test when inputted puzzle number is bad. Such as a word instead of number.
			//        String[] e = new String[2];
			//	        e[0] =  "Non-number puzzle ID.";
			//	        e[1] = "";
			//        JottoModel jmod = new JottoModel("BillGates");
			//	        String[] observed = jmod.makeGuess("stack");
			//	        assertArrayEquals(e, observed);   
			//		    }
				    
				    @Test
				    public void test3() throws IOException {
				        //Tests when we have no constructor in jottomod and bad input
				        String[] e = new String[2];
				        e[0] =  "Invalid guess ";
				        e[1] = "Try again";
				        JottoModel jmod = new JottoModel();
				        String[] observed = jmod.handleInput(jmod.makeGuess("Wayne Brady"));
				        assertArrayEquals(e, observed);   
				    }
				    
				    @Test
				    public void test4() throws IOException {
				        //tests when we have empty string
				        String[] e = new String[2];
				   	 e[0] = "Illegal request formatted";
					 e[1] = "Try again";
				        JottoModel jmod = new JottoModel();
				        String[] observed = jmod.handleInput(jmod.makeGuess(""));
				        assertArrayEquals(e, observed);   
				    }
				    
				    @Test
				    public void test5() throws IOException {
				        //tests a valid guess.
				        String[] e = new String[2];
				        e[0] =  "3";
				        e[1] = "3";
				        JottoModel jmod = new JottoModel(16952);
				        String[] observed = jmod.handleInput(jmod.makeGuess("carts"));
				        assertArrayEquals(e, observed);   
				    }
				    
				    @Test
				    public void test6() throws IOException {
				        //tests good input with winning word: "cargo"
				        String[] e = new String[1];
				        e[0] = "You are f***king awesome!!";
				        JottoModel jmod = new JottoModel(16952);
				        String[] observed = jmod.handleInput(jmod.makeGuess("cargo"));
				        assertArrayEquals(e, observed);   
				    }
				    
				    @Test
				    public void test7() throws IOException {
				        //tests good input with asterisks
				        String[] e = new String[2];
				        e[0] = "0";
				        e[1] = "0";
				        JottoModel jmod = new JottoModel(16952);
				        String[] observed = jmod.handleInput(jmod.makeGuess("*****"));
				        assertArrayEquals(e, observed); 
				    }
				    
			//	    @Test
			//	    public void test8() throws IOException {
			//	        //tests a negative puzzleNumber
			//	        //We expect it to instead, generate a random PuzzleNumber
			//	        String[] e = new String[2];
			//	        e[0] = "0";
			//	        e[1] = "0"; //not necessarily true, just want numbers
			//	        JottoModel jmod = new JottoModel("-45");
			//	        String[] observed = jmod.makeGuess("zebra");
			//	        System.out.println(observed);
			//	        assertArrayEquals(e,observed);
		    //		        
			//	    }
}
