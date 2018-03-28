import java.io.IOException;

/**
 * This method is to run the program and process the data.
 */
public class W09Practical {
    public static void main(String[] args) throws Exception {
//        if (args.length == 6) {
            boolean runner = true;
            String search = "", query = "", cache = "";
            for (int i = 0; i < args.length; i = i + 2) {
                switch (args[i]) {
                    case ("--search"):
                        try {
                            search = args[i + 1];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Missing value for --search");
                            runner = false;
                        }
                        break;

                    case ("--query"):
                        try {
                            query = args[i + 1];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Missing value for --query");
                            runner = false;
                        }
                        break;

                    case ("--cache"):
                        try {
                            cache = args[i + 1];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Missing value for --cache");
                            runner = false;
                        }
                        break;
                    default:
                        System.out.println("Invalid value for --search: " + args[i]);
                        runner = false;
                        break;
                }
            }

            if (runner) {
                inputProcess input = new inputProcess(search, query, cache);
            } else {
                System.out.println("Malformed command line arguments.");
            }

    }
}
