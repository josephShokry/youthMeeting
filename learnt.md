># learnt:
- swagger 
- mvc integration test 
- mock testing 
- mappers 
- security


***
># todo:
- learn and implement parametrized test cases 



    import org.junit.jupiter.params.ParameterizedTest;
    import org.junit.jupiter.params.provider.CsvSource;

    import static org.junit.jupiter.api.Assertions.assertEquals;
    
    public class MathUtilsTest {
    
        @ParameterizedTest
        @CsvSource({
            "1, 2, 3",   // 1 + 2 = 3
            "0, 0, 0",   // 0 + 0 = 0
            "-1, 1, 0",  // -1 + 1 = 0
            "10, -5, 5"  // 10 + (-5) = 5
        })
        void testAdd(int a, int b, int expected) {
            int result = MathUtils.add(a, b);
            assertEquals(expected, result, "Incorrect result for " + a + " + " + b);
        }
    }

*** 
- add make sure that admin user is present in the application and tests