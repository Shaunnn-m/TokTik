import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * The toktik program implements an application that manages user accounts and videos they have posted
 *
 * @author Thabang Mokoena
 * 14 April 2023
 */
public class TokTik {

    /**
     * default constructor
     */
    public TokTik(){}
    /**
     * Reads the content of a file.
     * @param file - name of the file
     * @return returns an Arraylist of type string of the lines contained inside the file
     */
    public static ArrayList<String> read(String file) {
        ArrayList<String> actions = new ArrayList<>();


        while(!file.isEmpty() && !file.equals("quit")) {
            try {
                File f = new File(file);
                Scanner reader = new Scanner(f);

                while (reader.hasNext()) {
                    actions.add(reader.nextLine());
                }
                System.out.println("File successfully loaded");
                reader.close();
                return actions;
            } catch (FileNotFoundException e) {
                System.out.println("File you Entered is not Found please enter a valid file name or quit");
                Scanner reader = new Scanner(System.in);
                file = reader.nextLine();

            }



        }return actions;
    }

    /**
     * prints out the layout of the application and allows user interaction
     *
     */
    public static void TokTikRun(){

        Scanner user = new Scanner(System.in);
        BST tree = new BST();

        int num=0;
        while (true){
            System.out.println("Choose an action from the menu:");
            System.out.println("1. Find the profile description for a given account");
            System.out.println("2. List all accounts");
            System.out.println("3. Create an account");
            System.out.println("4. Delete an account");
            System.out.println("5. Display all posts for a single account");
            System.out.println("6. Add a new post for an account");
            System.out.println("7. Load a file of actions from disk and process this");
            System.out.println("8. Quit");
            System.out.println("Enter your choice");


            try{
                num = user.nextInt();

                if (num == 1) {
                    user.nextLine();
                    System.out.println("Enter the account name:");
                    String name = user.nextLine();
                    AccountNode acc = tree.search(name);

                    if (acc == null) {
                        boolean exit = true;
                        while (exit) {
                            System.out.println("User not found Please re-Enter a valid accountName! or quit.");
                            name = user.nextLine();
                            acc = tree.search(name);
                            if (name.equalsIgnoreCase("quit") ) {
                                break;
                            } else if (acc != null) {

                                System.out.println("The profile description is: " + acc.description);
                                exit = false;
                            }
                        }
                        continue;
                    }
                    if (!acc.accountName.isEmpty()) {
                        System.out.println("The profile description is: " + acc.description);
                        System.out.println();
                        continue;
                    } else {
                        System.out.println("Invalid account");
                    }
                }
                if (num == 2) {
                    if (tree.root == null) {
                        System.out.println("There are no accounts to display.");
                        System.out.println();
                        continue;
                    }
                    tree.inOrder(tree.root);
                    System.out.println();
                    continue;
                }
                if (num == 3) {
                    user.nextLine();
                    System.out.println("Enter the account name:");
                    String name = user.nextLine();
                    AccountNode acc = tree.search(name);
                    System.out.println("Enter the account description:");
                    String accountDesc = user.nextLine();
                    if (name.isEmpty() || accountDesc.isBlank()) {
                        boolean exit = true;
                        System.out.println("Name or description cannot be empty Please re-Enter details or quit");
                        while (exit) {
                            System.out.println("Enter a Valid accName or quit:");
                            name = user.nextLine();
                            if (name.equalsIgnoreCase("quit")) {
                                break;
                            }
                            System.out.println("Enter the account description:");
                            accountDesc = user.nextLine();
                            if (!name.isEmpty() && !accountDesc.isEmpty()) {

                                tree.insert(name, accountDesc);
                                System.out.println("Your new account has been successfully created.");
                                exit = false;
                                System.out.println();
                            }
                        }
                        continue;
                    } else if (acc != null) {
                        if (name.equals(acc.accountName)) {
                            boolean exit = true;
                            while (exit) {
                                System.out.println("The user name already exists please try something different or quit");
                                name = user.nextLine();
                                if (name.equals("quit")) {
                                    break;
                                } else {
                                    tree.insert(name, accountDesc);
                                    System.out.println("Your new account has been successfully created.");
                                    exit = false;

                                }
                            }
                        }
                        continue;
                    }
                    tree.insert(name, accountDesc);
                    System.out.println("Your new account has been successfully created.");
                    System.out.println();
                    continue;
                }
                if (num == 4) {
                    user.nextLine();
                    System.out.println("Enter the account name:");
                    String name = user.nextLine();
                    AccountNode acc = tree.search(name);
                    if (acc == null) {
                        boolean exit = true;
                        while (exit) {
                            System.out.println("Incorrect user. User does not exist please re-Enter the name or quit.");
                            name = user.nextLine();
                            acc = tree.search(name);
                            if (name.equalsIgnoreCase("quit")) break;
                            else if (acc != null) {
                                tree.delete(name);
                                System.out.println("User has been successfully deleted");
                                exit = false;
                            }

                        }
                        continue;
                    }
                    tree.delete(name);
                    System.out.println("User has been successfully deleted");
                    System.out.println();
                    continue;
                }
                if (num == 5) {
                    user.nextLine();
                    System.out.println("Enter the account name:");
                    String name = user.nextLine();
                    AccountNode acc = tree.search(name);

                    if (acc == null) {
                        boolean exit = true;
                        while (exit) {
                            System.out.println("User not found. Please try again! or quit");
                            name = user.nextLine();
                            acc = tree.search(name);
                            if (name.equalsIgnoreCase("quit")) break;
                            else if (acc != null) {
                                if (acc.Videos.isEmpty()) {
                                    System.out.println("This account doesn't have any videos");
                                    break;
                                }

                                acc.printVideos(acc.Videos);
                                exit = false;
                                System.out.println();
                            }
                        }
                        continue;
                    } else if (acc.Videos.isEmpty()) {

                        System.out.println("This account doesn't have any videos yet.");
                        continue;
                    }

                    acc.printVideos(acc.Videos);
                    System.out.println();
                    continue;
                }
                if (num == 6) {
                    System.out.println("Enter the account name:");
                    String name = user.next();
                    AccountNode acc = tree.search(name);
                    if (acc == null) {
                        System.out.println("User not found");
                        continue;
                    }
                    System.out.println("Enter name of Video:");
                    String vidName = user.next();
                    user.nextLine();
                    System.out.println("Enter video description:");
                    String vidDesc = user.nextLine();
                    acc.insert(String.format("%s.mpg",vidName), 0, vidDesc);
                    System.out.println("Video has been successfully added.");
                    continue;
                }
                if (num == 7) {
                    user.nextLine();
                    System.out.println("Enter name of File:");
                    String file = user.nextLine();
                    ArrayList<String> actions = read(file);

                    for (String i : actions) {
                        Scanner reader = new Scanner(i);
                        String action = reader.next();

                        if (action.equals("Add")) {
                            String name = reader.next();
                            String video = reader.next();
                            int likes = reader.nextInt();
                            String description = reader.nextLine();
                            AccountNode acc = tree.search(name);
                            acc.insert(video, likes, description);

                        }
                        if (action.equals("Create")) {
                            String name = reader.next();
                            String description = reader.nextLine();
                            AccountNode acc = tree.search(name);
                            if (acc==null)
                            tree.insert(name, description.replaceFirst("//s",""));
                            else{
                                System.out.println(acc.accountName +"exits as an account already");
                            }


                        }
                    }

                    System.out.println();

                }
                else if(num==8) {
                    System.out.println("Bye!");
                    break;

                }else {
                    System.out.println("Incorrect input try again refer to the menu and pick from the available options");
                }

            }catch (InputMismatchException e){
                System.out.println("Invalid input please enter a integer from 1 to 8!");
                user.nextLine();
                System.out.println();

            }

        }

        
    }

}
