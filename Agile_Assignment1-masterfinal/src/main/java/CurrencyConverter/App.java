package CurrencyConverter;

import java.io.ByteArrayInputStream;
import java.util.*;

public class App {
    static Map<String, String> UsernamesAndPasswords = new HashMap<String, String>();
    static Scanner input = new Scanner(System.in);
    static boolean exitedUnexpectedly = false;

    public static double[][] rates = {
            // USD AUD EURO POUND SGD
            {1, 1.46, 0.9, 0.8, 1.38}, // Usd to USD, AUD,...
            {0.69, 1, 0.62, 0.56, 0.95}, // AUD to USD, AUD ....
            {1.11, 1.61, 1, 0.9, 1.52}, // EURO to USD, AUD ...
            {1.24, 1.8, 1.12, 1, 1.70},
            {0.73, 1.06, 0.66, 0.59, 1}
    };

    public static String showGreeting() {
        String greeting =
                "********************************************************\n"
                        + "********************************************************\n"
                        + "****************** Currency Converter ******************\n"
                        + "********************************************************\n"
                        + "********************************************************\n";
        return greeting;
    }

    public static String showExitGreeting() {
        String greeting =
                "********************************************************\n"
                        + "********************************************************\n"
                        + "************************* BYE **************************\n"
                        + "********************************************************\n"
                        + "********************************************************\n";
        return greeting;
    }

    public static String whoAreYou() {
        String identity;
        while (true) {
            System.out.println("Who are you? (ADMIN / USER)");
            identity = input.nextLine();
            if (identity.toLowerCase().trim().equals("admin") || identity.toLowerCase().trim().equals("user")) {
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
        return identity;
    }

    public static void accVerification(String account) {
        if (account.equals("admin")) {
            adminAcc();
        } else {
            userAcc();
        }
    }

    public static void updateCurrency() {
        while (true) {
            System.out.println("Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE]");
            String stringFrom = input.nextLine();

            if (stringFrom.toLowerCase().equals("exit")) {
                exitedUnexpectedly = true;
                return;
            }
            CurrenciesIndex from = findIndex(stringFrom);

            if (from == null) {
                continue;
            }

            System.out.println("Select TO Currency: (USD, AUD, EURO, POUND, SGD) [CASE INSENSITIVE]");
            String stringTo = input.nextLine();

            CurrenciesIndex to = findIndex(stringTo);

            if (to == null) {
                continue;
            }

            if (from == to) {
                System.out.println("You need to select two different Currencies!\n");
                continue;
            }

            System.out.println(
                    String.format(
                            "Current rate is %.2f. What is the new rate from %s to %s ?",
                            rates[from.getIdx()][to.getIdx()], from, to));

            double newRate = validInputTester();

            rates[from.getIdx()][to.getIdx()] = newRate;
            rates[to.getIdx()][from.getIdx()] = 1 / newRate;
            System.out.println(String.format("The rate from %s to %s is now %.2f", from, to, newRate));

            String command = "Would you like update different rate? Y or N";
            boolean yesNo = yesOrNo(command);
            if (!yesNo) {
                return;
            }
            input.nextLine();
        }
    }
    public static double validInputTester() {
        String amount;
        double d = -1;
        boolean notNum = false;
        while(true) {
            amount = input.nextLine();
            try {
                d = Double.parseDouble(amount);
            } catch (Exception e) {
                notNum = true;
            }
            if(notNum){
                System.out.println("You must provided valid number!");
                notNum = false;
            }else if(!notNum && d<= 0){
                System.out.println("Invalid amount provided, try again!");
            }else{
                break;
            }
        }
        return d;
    }

    public static boolean yesOrNo(String command) {
        System.out.println(command);
        while (true) {
            String userInput = input.next();
            if (userInput.toUpperCase().equals("Y")) {
                return true;
            } else if (userInput.toUpperCase().equals("N")) {
                return false;
            } else {
                System.out.println("Invalid input entered. Try again!");
                continue;
            }
        }
    }

    public static void adminAcc() {
        String adminOption;
        while (true) {
            System.out.println("What do you want to do? (Check Rate / Update Currency / exit)");
            adminOption = input.nextLine();
            if (adminOption.toLowerCase().equals("exit")) {
                return;
            } else if (adminOption.toLowerCase().equals("check rate")) {
                driver();
                if(!exitedUnexpectedly) {
                    input.nextLine();
                }
                exitedUnexpectedly = false;
            } else if (adminOption.toLowerCase().equals("update currency")) {
                updateCurrency();
                if(!exitedUnexpectedly){
                    input.nextLine();
                }
                exitedUnexpectedly = false;
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    public static void userAcc() {
        String userOption;
        while (true) {
            System.out.println("What do you want to do? (Check Rate / exit)");
            userOption = input.nextLine();
            if (userOption.toLowerCase().equals("exit")) {
                return;
            } else if (userOption.toLowerCase().equals("check rate")) {
                driver();
                if(!exitedUnexpectedly){
                    input.nextLine();
                }
                exitedUnexpectedly = false;
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    public static String wrongCurrencyMessage(String currency) {
        String message = String.format("%s is not a valid currency\nPlease enter again!", currency);
        return message;
    }

    public static Double sum() {
        List<Pair<CurrenciesIndex, Double>> sumList = new ArrayList<>();

        System.out.println("How many currencies you want to sum up? ");
        int x;
        do {
            try {
                x = input.nextInt();
                input.nextLine();
                if (x > 3) {
                    System.out.println("The maximum number you can sum is 3\nPlease enter again");
                } else if (x <= 0) {
                    System.out.println("You have entered an invalid number\nPlease try again");
                }
            } catch (Exception e) {
                x = 4;
                input.nextLine();
                System.out.println("You must input a number\nPlease try again");
            }
        } while (x > 3 | x <= 0);

        for (int i = 0; i < x; i++) {
            CurrenciesIndex currenciesIndex = null;
            double amount = 0;

            do {
                System.out.println(String.format("Enter the %d currency and the amount", i + 1));
                String[] currencyList = input.nextLine().split(" "); // Handle length of array < 1

                if (currencyList.length == 1) {
                    System.out.println("Invalid input, Please provide with a Currency and Amount!");
                    continue;
                }
                currenciesIndex = findIndex(currencyList[0]);
                if (currenciesIndex == null) {
                    System.out.println(wrongCurrencyMessage(currencyList[0]));
                }
                try {
                    amount = Double.parseDouble(currencyList[1]);
                    if (amount < 0) {
                        System.out.println("Invalid Amount given");
                        continue;
                    }
                } catch (Exception e) {
                    System.out.println("input must be a number");
                    continue;
                }
            } while (currenciesIndex == null | amount < 0);

            sumList.add(new Pair<>(currenciesIndex, amount));
        }

        System.out.printf("Enter the currency you would like to convert to:\n");

        CurrenciesIndex toCurrency = toCurrency();
        if(toCurrency == null){
            return null;
        }

        double answer = sumHelper(sumList, toCurrency);
        System.out.println("Answer is " + answer);
        return answer;
    }

    public static double sumHelper(
            List<Pair<CurrenciesIndex, Double>> sumList, CurrenciesIndex currency) {

        double sum = 0;

        for (Pair i : sumList) {
            sum +=
                    ((double) i.getValue())
                            * rates[((CurrenciesIndex) i.getKey()).getIdx()][currency.getIdx()];
        }
        return sum;
    }

    public static CurrenciesIndex fromCurrency() {
        CurrenciesIndex fromCurrency;
        do {
            System.out.println("Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] ");
            String inputString = input.nextLine();

            if (inputString.toLowerCase().contains("exit")) {
                return null;
            } else if (inputString.toLowerCase().contains("sum")) {

            }
            fromCurrency = findIndex(inputString.strip());
        } while (fromCurrency == null);

        return fromCurrency;
    }

    public static CurrenciesIndex toCurrency() {
        CurrenciesIndex fromCurrency;
        do {
            System.out.println("Select To Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] ");
            String inputString = input.nextLine();

            if (inputString.toLowerCase().contains("exit")) {
                return null;
            }

            fromCurrency = findIndex(inputString.strip());
        } while (fromCurrency == null);

        return fromCurrency;
    }


    public static double amountToConvert(CurrenciesIndex from, CurrenciesIndex to) {
        System.out.println(String.format("How much %s you want to convert to %s?", from, to));

        double amount = validInputTester();

        return amount;
    }

    public static double convert(CurrenciesIndex from, CurrenciesIndex to, double amount) {
        double answer = amount * rates[from.getIdx()][to.getIdx()];
        System.out.println(String.format("%.2f %s is %.2f %s!\n", amount, from, answer, to));
        return answer;
    }

    public static String menu() {
        System.out.println("Would you like to convert or sum the money? Enter exit to exit.");
        String command = input.nextLine();

        switch (command.toLowerCase().strip()) {
            case "convert":
                return "convert";
            case "sum":
                return "sum";
            case "exit":
                return "exit";
            default:
            {
                System.out.println("Invalid Input Please try again!");
                return menu();
            }
        }
    }

    public static void driver() {
        while (true) {
            String todo = menu();
            if (todo.equals("convert")) {
                CurrenciesIndex from = fromCurrency();
                if(from == null){
                    exitedUnexpectedly = true;
                    return;
                }
                CurrenciesIndex to = toCurrency();
                if(to == null){
                    exitedUnexpectedly = true;
                    return;
                }
                double amount = amountToConvert(from, to);
                convert(from, to, amount);
            } else if (todo.equals("sum")) {
                if(sum()==null)
                    exitedUnexpectedly = true;
                    return;
            } else if (todo.equals("exit")) {
                exitedUnexpectedly = true;
                return;
            }

            String command = "Would you like to make another conversion or Sum? Y or N";
            boolean yesNo = yesOrNo(command);
            if (!yesNo) {
                return;
            }

            input.nextLine();
        }
    }

    public static void main(String[] args) {

        System.out.println(showGreeting());
        accVerification(whoAreYou());
        System.out.println(showExitGreeting());
    }

    public static CurrenciesIndex findIndex(String string) {
        switch (string.toLowerCase()) {
            case "usd":
                return CurrenciesIndex.USD;
            case "aud":
                return CurrenciesIndex.AUD;
            case "euro":
                return CurrenciesIndex.EURO;
            case "pound":
                return CurrenciesIndex.POUND;
            case "sgd":
                return CurrenciesIndex.SGD;
            default:
                System.out.println("Please select a valid currency!");
                return null;
        }
    }

    enum CurrenciesIndex {
        USD(0),
        AUD(1),
        EURO(2),
        POUND(3),
        SGD(4);

        private int idx;

        CurrenciesIndex(int i) {
            idx = i;
        }

        public int getIdx() {
            return idx;
        }
    }
}
