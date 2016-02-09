package piwords;

import static org.junit.Assert.*;

import org.junit.Test;

public class BaseTranslatorTest {
    @Test
    public void basicBaseTranslatorTest() {
        // Expect that .01 in base-2 is .25 in base-10
        // (0 * 1/2^1 + 1 * 1/2^2 = .25)
        int[] input = {0, 1};
        int[] expectedOutput = {2, 5};
        assertArrayEquals(expectedOutput,
                          BaseTranslator.convertBase(input, 2, 10, 2));
    }
    
    @Test
    public void BaseTranslatorTestNull() {
    	int[] input = null;
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 10, 16, 5));;
    }
    
    
    // precision = 0
    
    // baseA = 1
    
    // baseB = -2
    @Test
    public void BaseTranslatorTest001() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 1, baseB = -2, precision = 0;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, -2, 0));
    }
    
    @Test
    public void BaseTranslatorTest002() {
    	// digits = [0, 0, 0, 0, 0, 0], baseA = 1, baseB = -2, precision = 0;
    	int[] input = {0, 0, 0, 0, 0, 0};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, -2, 0));
    }
    
    @Test
    public void BaseTranslatorTest003() {
    	// digits = [1, 4, 16, 64, 256, 1024], baseA = 1, baseB = -2, precision = 0;
    	int[] input = {1, 4, 16, 64, 256, 1024};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, -2, 0));
    }
    
    // baseB = 2
    @Test
    public void BaseTranslatorTest004() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 1, baseB = 2, precision = 0;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, 2, 0));
    }
    
    @Test
    public void BaseTranslatorTest005() {
    	// digits = [0, 0, 0, 0, 0, 0], baseA = 1, baseB = 2, precision = 0;
    	int[] input = {0, 0, 0, 0, 0, 0};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, 2, 0));
    }
    
    @Test
    public void BaseTranslatorTest006() {
    	// digits = [1, 4, 16, 64, 256, 1024], baseA = 1, baseB = 2, precision = 0;
    	int[] input = {1, 4, 16, 64, 256, 1024};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, 2, 0));
    }
    
    // baseB = 16
    @Test
    public void BaseTranslatorTest007() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 1, baseB = 16, precision = 0;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, 16, 0));
    }
    
    @Test
    public void BaseTranslatorTest008() {
    	// digits = [0, 0, 0, 0, 0, 0], baseA = 1, baseB = 16, precision = 0;
    	int[] input = {0, 0, 0, 0, 0, 0};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, 16, 0));
    }
    
    @Test
    public void BaseTranslatorTest009() {
    	// digits = [1, 4, 16, 64, 256, 1024], baseA = 1, baseB = 16, precision = 0;
    	int[] input = {1, 4, 16, 64, 256, 1024};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, 16, 0));
    }
    
    
    
    // baseA = 2
    
    // baseB = -2
    @Test
    public void BaseTranslatorTest010() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 2, baseB = -2, precision = 0;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, -2, 0));
    }
    
    @Test
    public void BaseTranslatorTest011() {
    	// digits = [1, 0, 1, 0, 1, 0], baseA = 2, baseB = -2, precision = 0;
    	int[] input = {1, 0, 1, 0, 1, 0};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, -2, 0));
    }
    
    @Test
    public void BaseTranslatorTest012() {
    	// digits = [2, 3, 9, 27, 81, 243], baseA = 2, baseB = -2, precision = 0;
    	int[] input = {2, 3, 9, 27, 81, 243};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, -2, 0));
    }
    
    // baseB = 2
    @Test
    public void BaseTranslatorTest013() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 2, baseB = 2, precision = 0;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, 2, 0));
    }
    
    @Test
    public void BaseTranslatorTest014() {
    	// digits = [1, 0, 1, 0, 1, 0], baseA = 2, baseB = 2, precision = 0;
    	int[] input = {1, 0, 1, 0, 1, 0};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, 2, 0));
    }
    
    @Test
    public void BaseTranslatorTest015() {
    	// digits = [2, 3, 9, 27, 81, 243], baseA = 2, baseB = 2, precision = 0;
    	int[] input = {2, 3, 9, 27, 81, 243};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, 2, 0));
    }
    
    // baseB = 16
    @Test
    public void BaseTranslatorTest016() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 2, baseB = 16, precision = 0;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, 16, 0));
    }
    
    @Test
    public void BaseTranslatorTest017() {
    	// digits = [1, 0, 1, 0, 1, 0], baseA = 2, baseB = 16, precision = 0;
    	int[] input = {1, 0, 1, 0, 1, 0};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, 16, 0));
    }
    
    @Test
    public void BaseTranslatorTest018() {
    	// digits = [2, 3, 9, 27, 81, 243], baseA = 2, baseB = 16, precision = 0;
    	int[] input = {2, 3, 9, 27, 81, 243};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, 16, 0));
    }
    
    
    
    // baseA = 8
    
    // baseB = -2
    @Test
    public void BaseTranslatorTest019() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 8, baseB = -2, precision = 0;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, -2, 0));
    }
    
    @Test
    public void BaseTranslatorTest020() {
    	// digits = [3, 7, 4, 1, 5, 6], baseA = 8, baseB = -2, precision = 0;
    	int[] input = {3, 7, 4, 1, 5, 6};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, -2, 0));
    }
    
    @Test
    public void BaseTranslatorTest021() {
    	// digits = [8, 12, 16, 20, 24, 28], baseA = 8, baseB = -2, precision = 0;
    	int[] input = {8, 12, 16, 20, 24, 28};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, -2, 0));
    }
    
    // baseB = 2
    @Test
    public void BaseTranslatorTest022() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 8, baseB = 2, precision = 0;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, 2, 0));
    }
    
    @Test
    public void BaseTranslatorTest023() {
    	// digits = [3, 7, 4, 1, 5, 6], baseA = 8, baseB = 2, precision = 0;
    	int[] input = {3, 7, 4, 1, 5, 6};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, 2, 0));
    }
    
    @Test
    public void BaseTranslatorTest024() {
    	// digits = [8, 12, 16, 20, 24, 28], baseA = 8, baseB = 2, precision = 0;
    	int[] input = {8, 12, 16, 20, 24, 28};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, 2, 0));
    }
    
    // baseB = 16
    @Test
    public void BaseTranslatorTest025() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 8, baseB = 16, precision = 0;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, 16, 0));
    }
    
    @Test
    public void BaseTranslatorTest026() {
    	// digits = [3, 7, 4, 1, 5, 6], baseA = 8, baseB = 16, precision = 0;
    	int[] input = {3, 7, 4, 1, 5, 6};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, 16, 0));
    }
    
    @Test
    public void BaseTranslatorTest027() {
    	// digits = [8, 12, 16, 20, 24, 28], baseA = 8, baseB = 16, precision = 0;
    	int[] input = {8, 12, 16, 20, 24, 28};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, 16, 0));
    }
    
    
    
    
    
    // precision = 1
    
    // baseA = 1
    
    // baseB = -2
    @Test
    public void BaseTranslatorTest028() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 1, baseB = -2, precision = 1;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, -2, 1));
    }
    
    @Test
    public void BaseTranslatorTest029() {
    	// digits = [0, 0, 0, 0, 0, 0], baseA = 1, baseB = -2, precision = 1;
    	int[] input = {0, 0, 0, 0, 0, 0};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, -2, 1));
    }
    
    @Test
    public void BaseTranslatorTest030() {
    	// digits = [1, 4, 16, 64, 256, 1024], baseA = 1, baseB = -2, precision = 1;
    	int[] input = {1, 4, 16, 64, 256, 1024};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, -2, 1));
    }
    
    // baseB = 2
    @Test
    public void BaseTranslatorTest031() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 1, baseB = 2, precision = 1;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, 2, 1));
    }
    
    @Test
    public void BaseTranslatorTest032() {
    	// digits = [0, 0, 0, 0, 0, 0], baseA = 1, baseB = 2, precision = 1;
    	int[] input = {0, 0, 0, 0, 0, 0};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, 2, 1));
    }
    
    @Test
    public void BaseTranslatorTest033() {
    	// digits = [1, 4, 16, 64, 256, 1024], baseA = 1, baseB = 2, precision = 1;
    	int[] input = {1, 4, 16, 64, 256, 1024};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, 2, 1));
    }
    
    // baseB = 16
    @Test
    public void BaseTranslatorTest034() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 1, baseB = 16, precision = 1;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, 16, 1));
    }
    
    @Test
    public void BaseTranslatorTest035() {
    	// digits = [0, 0, 0, 0, 0, 0], baseA = 1, baseB = 16, precision = 1;
    	int[] input = {0, 0, 0, 0, 0, 0};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, 16, 1));
    }
    
    @Test
    public void BaseTranslatorTest036() {
    	// digits = [1, 4, 16, 64, 256, 1024], baseA = 1, baseB = 16, precision = 1;
    	int[] input = {1, 4, 16, 64, 256, 1024};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, 16, 1));
    }
    
    
    
    // baseA = 2
    
    // baseB = -2
    @Test
    public void BaseTranslatorTest037() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 2, baseB = -2, precision = 1;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, -2, 1));
    }
    
    @Test
    public void BaseTranslatorTest038() {
    	// digits = [1, 0, 1, 0, 1, 0], baseA = 2, baseB = -2, precision = 1;
    	int[] input = {1, 0, 1, 0, 1, 0};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, -2, 1));
    }
    
    @Test
    public void BaseTranslatorTest039() {
    	// digits = [2, 3, 9, 27, 81, 243], baseA = 2, baseB = -2, precision = 1;
    	int[] input = {2, 3, 9, 27, 81, 243};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, -2, 1));
    }
    
    // baseB = 2
    @Test
    public void BaseTranslatorTest040() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 2, baseB = 2, precision = 1;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, 2, 1));
    }
    
    @Test
    public void BaseTranslatorTest041() {
    	// digits = [1, 0, 1, 0, 1, 0], baseA = 2, baseB = 2, precision = 1;
    	int[] input = {1, 0, 1, 0, 1, 0};
    	int[] expectedOutput = {1};
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, 2, 1));
    }
    
    @Test
    public void BaseTranslatorTest042() {
    	// digits = [2, 3, 9, 27, 81, 243], baseA = 2, baseB = 2, precision = 1;
    	int[] input = {2, 3, 9, 27, 81, 243};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, 2, 1));
    }
    
    // baseB = 16
    @Test
    public void BaseTranslatorTest043() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 2, baseB = 16, precision = 1;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, 16, 1));
    }
    
    @Test
    public void BaseTranslatorTest044() {
    	// digits = [1, 0, 1, 0, 1, 0], baseA = 2, baseB = 16, precision = 1;
    	int[] input = {1, 0, 1, 0, 1, 0};
    	int[] expectedOutput = {10};
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, 16, 1));
    }
    
    @Test
    public void BaseTranslatorTest045() {
    	// digits = [2, 3, 9, 27, 81, 243], baseA = 2, baseB = 16, precision = 1;
    	int[] input = {2, 3, 9, 27, 81, 243};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, 16, 1));
    }
    
    
    
    // baseA = 8
    
    // baseB = -2
    @Test
    public void BaseTranslatorTest046() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 8, baseB = -2, precision = 1;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, -2, 1));
    }
    
    @Test
    public void BaseTranslatorTest047() {
    	// digits = [3, 7, 4, 1, 5, 6], baseA = 8, baseB = -2, precision = 1;
    	int[] input = {3, 7, 4, 1, 5, 6};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, -2, 1));
    }
    
    @Test
    public void BaseTranslatorTest048() {
    	// digits = [8, 12, 16, 20, 24, 28], baseA = 8, baseB = -2, precision = 1;
    	int[] input = {8, 12, 16, 20, 24, 28};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, -2, 1));
    }
    
    // baseB = 2
    @Test
    public void BaseTranslatorTest049() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 8, baseB = 2, precision = 1;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, 2, 1));
    }
    
    @Test
    public void BaseTranslatorTest050() {
    	// digits = [3, 7, 4, 1, 5, 6], baseA = 8, baseB = 2, precision = 1;
    	int[] input = {3, 7, 4, 1, 5, 6};
    	int[] expectedOutput = {0};
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, 2, 1));
    }
    
    @Test
    public void BaseTranslatorTest051() {
    	// digits = [8, 12, 16, 20, 24, 28], baseA = 8, baseB = 2, precision = 1;
    	int[] input = {8, 12, 16, 20, 24, 28};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, 2, 1));
    }
    
    // baseB = 16
    @Test
    public void BaseTranslatorTest052() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 8, baseB = 16, precision = 1;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, 16, 1));
    }
    
    @Test
    public void BaseTranslatorTest053() {
    	// digits = [3, 7, 4, 1, 5, 6], baseA = 8, baseB = 16, precision = 1;
    	int[] input = {3, 7, 4, 1, 5, 6};
    	int[] expectedOutput = {7};
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, 16, 1));
    }
    
    @Test
    public void BaseTranslatorTest054() {
    	// digits = [8, 12, 16, 20, 24, 28], baseA = 8, baseB = 16, precision = 1;
    	int[] input = {8, 12, 16, 20, 24, 28};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, 16, 1));
    }
    
    
    
    // precision = 3
    
    // baseA = 1
    
    // baseB = -2
    @Test
    public void BaseTranslatorTest055() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 1, baseB = -2, precision = 3;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, -2, 3));
    }
    
    @Test
    public void BaseTranslatorTest056() {
    	// digits = [0, 0, 0, 0, 0, 0], baseA = 1, baseB = -2, precision = 3;
    	int[] input = {0, 0, 0, 0, 0, 0};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, -2, 3));
    }
    
    @Test
    public void BaseTranslatorTest057() {
    	// digits = [1, 4, 16, 64, 256, 1024], baseA = 1, baseB = -2, precision = 3;
    	int[] input = {1, 4, 16, 64, 256, 1024};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, -2, 3));
    }
    
    // baseB = 2
    @Test
    public void BaseTranslatorTest058() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 1, baseB = 2, precision = 3;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, 2, 3));
    }
    
    @Test
    public void BaseTranslatorTest059() {
    	// digits = [0, 0, 0, 0, 0, 0], baseA = 1, baseB = 2, precision = 3;
    	int[] input = {0, 0, 0, 0, 0, 0};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, 2, 3));
    }
    
    @Test
    public void BaseTranslatorTest060() {
    	// digits = [1, 4, 16, 64, 256, 1024], baseA = 1, baseB = 2, precision = 3;
    	int[] input = {1, 4, 16, 64, 256, 1024};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, 2, 3));
    }
    
    // baseB = 16
    @Test
    public void BaseTranslatorTest061() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 1, baseB = 2, precision = 3;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, 2, 3));
    }
    
    @Test
    public void BaseTranslatorTest062() {
    	// digits = [0, 0, 0, 0, 0, 0], baseA = 1, baseB = 2, precision = 3;
    	int[] input = {0, 0, 0, 0, 0, 0};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, 2, 3));
    }
    
    @Test
    public void BaseTranslatorTest063() {
    	// digits = [1, 4, 16, 64, 256, 1024], baseA = 1, baseB = 2, precision = 3;
    	int[] input = {1, 4, 16, 64, 256, 1024};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 1, 2, 3));
    }
    
    
    
    // baseA = 2
    
    // baseB = -2
    @Test
    public void BaseTranslatorTest064() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 2, baseB = -2, precision = 3;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, -2, 3));
    }
    
    @Test
    public void BaseTranslatorTest065() {
    	// digits = [1, 0, 1, 0, 1, 0], baseA = 2, baseB = -2, precision = 3;
    	int[] input = {1, 0, 1, 0, 1, 0};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, -2, 3));
    }
    
    @Test
    public void BaseTranslatorTest066() {
    	// digits = [2, 3, 9, 27, 81, 243], baseA = 2, baseB = -2, precision = 3;
    	int[] input = {2, 3, 9, 27, 81, 243};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, -2, 3));
    }
    
    // baseB = 2
    @Test
    public void BaseTranslatorTest067() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 2, baseB = 2, precision = 3;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, 2, 3));
    }
    
    @Test
    public void BaseTranslatorTest068() {
    	// digits = [1, 0, 1, 0, 1, 0], baseA = 2, baseB = 2, precision = 3;
    	int[] input = {1, 0, 1, 0, 1, 0};
    	int[] expectedOutput = {1, 0, 1};
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, 2, 3));
    }
    
    @Test
    public void BaseTranslatorTest069() {
    	// digits = [2, 3, 9, 27, 81, 243], baseA = 2, baseB = 2, precision = 3;
    	int[] input = {2, 3, 9, 27, 81, 243};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, 2, 3));
    }
    
    // baseB = 16
    @Test
    public void BaseTranslatorTest070() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 2, baseB = 16, precision = 3;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, 16, 3));
    }
    
    @Test
    public void BaseTranslatorTest071() {
    	// digits = [1, 0, 1, 0, 1, 0], baseA = 2, baseB = 16, precision = 3;
    	int[] input = {1, 0, 1, 0, 1, 0};
    	int[] expectedOutput = {10, 8, 0};
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, 16, 3));
    }
    
    @Test
    public void BaseTranslatorTest072() {
    	// digits = [2, 3, 9, 27, 81, 243], baseA = 2, baseB = 16, precision = 3;
    	int[] input = {2, 3, 9, 27, 81, 243};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 2, 16, 3));
    }
    
    
    
    // baseA = 8
    
    // baseB = -2
    @Test
    public void BaseTranslatorTest073() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 8, baseB = -2, precision = 3;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, -2, 3));
    }
    
    @Test
    public void BaseTranslatorTest074() {
    	// digits = [3, 7, 4, 1, 5, 6], baseA = 8, baseB = -2, precision = 3;
    	int[] input = {3, 7, 4, 1, 5, 6};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, -2, 3));
    }
    
    @Test
    public void BaseTranslatorTest075() {
    	// digits = [8, 12, 16, 20, 24, 28], baseA = 8, baseB = -2, precision = 3;
    	int[] input = {8, 12, 16, 20, 24, 28};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, -2, 3));
    }
    
    // baseB = 2
    @Test
    public void BaseTranslatorTest076() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 8, baseB = 2, precision = 3;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, 2, 3));
    }
    
    @Test
    public void BaseTranslatorTest077() {
    	// digits = [3, 7, 4, 1, 5, 6], baseA = 8, baseB = 2, precision = 3;
    	int[] input = {3, 7, 4, 1, 5, 6};
    	int[] expectedOutput = {0, 1, 1};
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, 2, 3));
    }
    
    @Test
    public void BaseTranslatorTest078() {
    	// digits = [8, 12, 16, 20, 24, 28], baseA = 8, baseB = 2, precision = 3;
    	int[] input = {8, 12, 16, 20, 24, 28};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, 2, 3));
    }
    
    // baseB = 16
    @Test
    public void BaseTranslatorTest079() {
    	// digits = [-3, 0, -1, -4, -1, -5], baseA = 8, baseB = 16, precision = 3;
    	int[] input = {-3, 0, -1, -4, -1, -5};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, 16, 3));
    }
    
    @Test
    public void BaseTranslatorTest080() {
    	// digits = [3, 7, 4, 1, 5, 6], baseA = 8, baseB = 16, precision = 3;
    	int[] input = {3, 7, 4, 1, 5, 6};
    	int[] expectedOutput = {7, 14, 1};
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, 16, 3));
    }
    
    @Test
    public void BaseTranslatorTest081() {
    	// digits = [8, 12, 16, 20, 24, 28], baseA = 8, baseB = 16, precision = 3;
    	int[] input = {8, 12, 16, 20, 24, 28};
    	int[] expectedOutput = null;
    	assertArrayEquals(expectedOutput,
    			BaseTranslator.convertBase(input, 8, 16, 3));
    }
}
