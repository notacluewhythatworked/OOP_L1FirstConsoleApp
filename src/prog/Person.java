package prog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Person {
    private String prefix;
    private String name;
    private String suffix;
    private String age;
    private String ageDifference;
    private boolean isInvalid = true;
    String error = "Whoops. We screwed that one up. Please try that again.";

    //el magic array for storing the things
    ArrayList<Person> people = new ArrayList<>();

    //Instantiate the BufferedReader
    BufferedReader buffRead = new BufferedReader(new InputStreamReader(System.in));

    //get methods
    public String getPrefix() {
        return prefix;
    }
    public String getName() {
        return name;
    }
    public String getSuffix() {
        return suffix;
    }
    public String getAge() {
        return age;
    }
    public String getAgeDifference() {
        return ageDifference;
    }

    //set methods
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public void setAgeDifference(String ageDifference) {
        this.ageDifference = ageDifference;
    }


    //constructors
    public Person() {

    }

    public Person(String prefix, String name, String suffix, String age, String ageDifference) {
        this.prefix = prefix;
        this.name = name;
        this.suffix = suffix;
        this.age = age;
        this.ageDifference = ageDifference;
    }


    //heyo, this guy runs everything
    public void run() {
        prefixMethod();
        nameMethod();
        suffixMethod();
        greeting();
        ageMethod();
        addingToDatabase();
        menu();
    }


    public void prefixMethod() {
        //Prompt the user for the suffix
        System.out.print("Hey there! What is your preferred prefix (Mr., Mrs., Ms., etc)?\n");
        try {
            setPrefix(buffRead.readLine());
        } catch (IOException ioe) {
            System.out.println(error);
        }
    }


    public void nameMethod() {
        //Prompt the user for the name
        do {
            System.out.println("\nWhat is your name? You may enter your first, last, or your full name.");
            try {
                setName(buffRead.readLine());
                isInvalid = name == null || name.isBlank();
                //boolean flag = Character.isDigit(name.charAt(1000));

                if (isInvalid) {
                    System.out.println("Oof. Yikes. That really didn't work and it was 100% your fault. Let's try that again. ");
                }

            } catch (IOException ioe) {
                System.out.println(error);
            }
        } while (isInvalid);
    }


    public void suffixMethod() {
        //Prompt the user for the suffix
        System.out.println("\nWhat is your preferred suffix, if any (Sr., Jr., Esq., III., etc)?");
        try {
            setSuffix(buffRead.readLine());
        } catch (IOException ioe) {
            System.out.println(error);
        }
    }


    public void greeting() {
        //Greet the user
        System.out.println(getCompleteName());
    }


    public void ageMethod() {
        //Prompt for the user's age and declare if they are younger, older, or the same age as the developer
        int devAge = 19;
        int userAge = 0;
        boolean ageCheck = true;
        do {
            System.out.println("How old are you?");
            try {
                setAge(buffRead.readLine());
                userAge = Integer.parseInt(getAge());
                ageCheck = userAge > 120 || userAge < 0;
            } catch (NumberFormatException nfe) {
                System.out.println(error);
            } catch (IOException ioe) {
                System.out.println(error);
            }

            if (userAge > devAge) {
                setAgeDifference("Older");
            } else if (userAge < devAge) {
                setAgeDifference("Younger");
            } else {
                setAgeDifference("Same Age");
            }
        } while (ageCheck);
    }


    public void addingToDatabase() {
        //add the person's profile to the 'database'
        people.add(new Person(getPrefix(), getName(), getSuffix(), getAge(), getAgeDifference()));
        System.out.println("\nThe following has been added to the database: " + toString());
    }


    public void menu() {
        //Prompt the user to print names, add a new one, or exit the application
        String userSelection = null;
        boolean isInvalid = true;
        do {
            System.out.println("\nWould you like to: \n1) Add another name \n2) View the saved names \n3) Quit the application");
            try {
                userSelection = buffRead.readLine();
                isInvalid = userSelection == null || userSelection.isBlank();

                if (isInvalid) {
                    System.out.println("Oof. You really screwed that one up. Let's try that again.");
                }
            } catch (IOException ioe) {
                System.out.println("Wow. Not sure how you managed to get here, but ya did. Go ahead and try something else, or maybe that same thing again.");
            }
        } while (isInvalid);


        switch (userSelection) {
            case "1":
                System.out.println("Oh look, here we go again!");
                run();
                break;
            case "2":
                System.out.println("There are currently " + people.size() + " people in the database. Here's the list: " + people.toString());
                menu();
                break;
            case "3":
                System.out.println("Goodbye.");
                break;
        }
    }


    public String getCompleteName() {
        String greeting = "\nWelcome " + getPrefix() + " " + getName() + " " + getSuffix() + "! It's nice to meet you.";
        return greeting;
    }

    @Override
    public String toString() {
        return getPrefix() + " " + getName() + " " + getSuffix() + "(" + getAge() + ") - " + getAgeDifference();
    }
}

//TODO KNOWN ERRORS
//The ability to submit a number as a name