package proje_02;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.util.HashSet;
import java.util.UUID;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class User extends Human {

    private final String job = "User";
    private final Logger logger = Logger.getInstance(System.getProperty("user.dir") + "/src/User_Log.txt");
    Scanner scan = new Scanner(System.in);
    private String Log_File = "C:\\Users\\Berk\\Documents\\NetBeansProjects\\Proje_02\\src\\User_Log.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    final String IMAGE_DIRECTORY = "C:\\Users\\Berk\\Documents\\NetBeansProjects\\Proje_02\\src\\Images\\";
    final String ENCRYPTION_IMAGE_DIRECTORY = "C:\\Users\\Berk\\Documents\\NetBeansProjects\\Proje_02\\src\\Encrypted_Images\\";

    boolean is_recieved_message = false;
    private BufferedImage user_image;
    public String message;
    private String hidden_message;
    int [][][] pixel_array;
    public int target_blue_value;
    ArrayList<User> friends = new ArrayList<>();

    public User() {

    }

    public User(String name, String surname, int age) {
        super(name, surname, "User", age);
        logger.logCreatedOn(getId(), name, surname, this.job);
    }

    public void addFriend(User user) {
        if (friends.contains(user) == false) {
            System.out.println(user.getId() + " Added To Your Friends List!");
            friends.add(user);

        } else {
            System.out.println("You Already Added This User!");
        }
    }

    public void deleteFriend(User user) {
        if (friends.contains(user) == false) {
            System.out.println("You Cannot Delete Users Who Aren't In Your Friends List! ");
        } else {
            System.out.println(user.getId() + " Deleted From Your Friends List!");
            friends.remove(user);

        }
    }

    public void sendHiddenMessage() {
        changePixelsWithTargetValue();
        HidingTheMessage();

        logger.logAction(getId(), getName(), getSurname(), this.job, "sent a hidden message");

    }

    public void doDecryption() {
        try {
            System.out.println();
            System.out.println("Enter Image Name For Decryption: ");
            String file_name = scan.next();
            File file = new File(ENCRYPTION_IMAGE_DIRECTORY + file_name + ".jpg");
            BufferedImage image = ImageIO.read(file);

            //Getting the message length and target blue value
            int pixel = image.getRGB(0, 0);
            Color color = new Color(pixel, true);

            int message_length = color.getRed();
            int target_value = color.getGreen();

            int[] randomArray = new int[message_length];
            int[] asciiOfCharacters = new int[message_length];
            char[] hiddenCharacters = new char[message_length];
            //Looking for pixels that same value with target_blue_value
            int width = image.getWidth();
            int height = image.getHeight();
            int i = 0;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    // Color values from the pixel at x,y
                    pixel = image.getRGB(x, y);
                    color = new Color(pixel, true);

                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    if (blue == target_value) {

                        randomArray[i] = red;
                        asciiOfCharacters[i] = green;
                        i++;
                    }
                }
            }
            //Sorting
            for (int j = 0; j < message_length; j++) {
                for (int k = 0; k < message_length - 1 - j; k++) {
                    if (randomArray[k] > randomArray[k + 1]) {
                        // Swap the elements
                        int temp1 = randomArray[k];
                        randomArray[k] = randomArray[k + 1];
                        randomArray[k + 1] = temp1;

                        int temp2 = asciiOfCharacters[k];
                        asciiOfCharacters[k] = asciiOfCharacters[k + 1];
                        asciiOfCharacters[k + 1] = temp2;

                    }
                }
            }
            for (int j = 0; j < asciiOfCharacters.length; j++) {
                hiddenCharacters[j] = (char) asciiOfCharacters[j];
            }
            hidden_message = new String(hiddenCharacters);
            System.out.println();
            System.out.println("Hidden message is: " + hidden_message);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        logger.logAction(getId(), getName(), getSurname(), this.job, "performed decryption");
    }

    public void doDecryptionFX() {

        BufferedImage image = this.getImage();
        //Getting the message length and target blue value
        int pixel = image.getRGB(0, 0);
        Color color = new Color(pixel, true);
        int message_length = color.getRed();
        int target_value = color.getGreen();

        int[] randomArray = new int[message_length];
        int[] asciiOfCharacters = new int[message_length];
        char[] hiddenCharacters = new char[message_length];
        //Looking for pixels that same value with target_blue_value
        int width = image.getWidth();
        int height = image.getHeight();
        int i = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Color values from the pixel at x,y
                pixel = image.getRGB(x, y);
                color = new Color(pixel, true);

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                if (blue == target_value) {

                    randomArray[i] = red;
                    asciiOfCharacters[i] = green;
                    i++;
                }
            }
        }
        //Sorting
        for (int j = 0; j < message_length; j++) {
            for (int k = 0; k < message_length - 1 - j; k++) {
                if (randomArray[k] > randomArray[k + 1]) {
                    // Swap the elements
                    int temp1 = randomArray[k];
                    randomArray[k] = randomArray[k + 1];
                    randomArray[k + 1] = temp1;

                    int temp2 = asciiOfCharacters[k];
                    asciiOfCharacters[k] = asciiOfCharacters[k + 1];
                    asciiOfCharacters[k + 1] = temp2;

                }
            }
        }
        for (int j = 0; j < asciiOfCharacters.length; j++) {
            hiddenCharacters[j] = (char) asciiOfCharacters[j];
        }
        String hidden_message1 = new String(hiddenCharacters);
        this.setHidden_message(hidden_message1);

        logger.logAction(getId(), getName(), getSurname(), this.job, "performed decryption");
    }

    public void setMessage() {
        // ---------------------------------------- Entering Message to hide ------------------------------------------------------
        System.out.println("Enter the message you want to hide (Must be 1-255 characters long): ");
        String message_input = scan.nextLine();
        System.out.println("Message Length: " + message_input.length());
        while (true) {
            if (message_input.length() <= 0 || message_input.length() > 250) {
                System.out.println("Enter a String with length 1-255: ");
                message_input = scan.nextLine();
            } else {
                this.message = message_input;
                break;
            }
        }
    }

    public String getMessage() {
        return message;
    }

    public void setTargetBlue_Value(int a) {

        System.out.println("Enter the target blue value you want to hide: ");
        int target_blue_input = scan.nextInt();
        while (true) {
            if (target_blue_input == this.message.length() || (target_blue_input <= 0 || target_blue_input > 255)) {
                System.out.println("Invalid Entry! Enter a new target blue value: ");
                target_blue_input = scan.nextInt();
            } else {
                this.target_blue_value = target_blue_input;
                break;
            }
        }

    }

    public int getTarget_blue_value() {
        return target_blue_value;
    }

    public void chooseOrUploadImage() {
        System.out.println("-If You Want Choose From The Default Image List Please Enter 0");
        System.out.println("-If You Want Upload An Image Please Enter 1:");
        int n = scan.nextInt();
        System.out.println();
        while (true) {
            if (n == 0) {
                System.out.println("1-For Minecraft Brick Block Image Please Enter 1");
                System.out.println("2-For Minecraft Lava Block Image Please Enter 2");
                System.out.println("3-For Bicycle Image Please Enter 3");
                System.out.println("4-For Car Image Please Enter 4");
                System.out.println("5-For Marmara University Logo Image Please Enter 5");
                n = scan.nextInt();
                System.out.println();
                if (n >= 1 && n <= 5) {
                    switch (n) {
                        case 1:
                            this.setImage(IMAGE_DIRECTORY + "image1.jpg");
                            System.out.println("Minecraft Brick Block Image Chosen For Encryption!");
                            break;
                        case 2:
                            this.setImage(IMAGE_DIRECTORY + "image2.jpg");
                            System.out.println("Minecraft Lava Block Image Chosen For Encryption!");
                            break;
                        case 3:
                            this.setImage(IMAGE_DIRECTORY + "image3.jpg");
                            System.out.println("Bicycle Image Chosen For Encryption!");
                            break;
                        case 4:
                            this.setImage(IMAGE_DIRECTORY + "image4.jpg");
                            System.out.println("Car Image Chosen For Encryption!");
                            break;
                        case 5:
                            this.setImage(IMAGE_DIRECTORY + "image5.jpg");
                            System.out.println("Marmara University Logo Image Chosen For Encryption!");
                            break;
                        default:
                            System.out.println("-Invalid Entry! Please Enter Appropriate Number: ");
                            n = scan.nextInt();
                    }
                    break;
                } else {
                    System.out.println("-Invalid Entry! Please Enter Appropriate Number: ");
                    n = scan.nextInt();
                }
            } else if (n == 1) {
                System.out.println("Please Enter Your Image Path: ");
                String path = scan.next();
                this.setImage(path);
                //   user_to_send.setImage(path);
                break;
            } else {
                System.out.println("-Invalid Entry! Please Enter Appropriate Number: ");
                n = scan.nextInt();
            }
        }
    }

    public void changePixelsWithTargetValue() {
        // Getting width and height values
        pixel_array = new int[this.getImage().getWidth()][this.getImage().getHeight()][3];
        int width = this.getImage().getWidth();
        int height = this.getImage().getHeight();
        int red;
        int green;
        int blue;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Color values from the pixel at x,y
                int pixel = this.getImage().getRGB(x, y);
                Color color = new Color(pixel, true);
                red = color.getRed();
                green = color.getGreen();
                blue = color.getBlue();
                
                pixel_array[y][x][0] = red;
                pixel_array[y][x][1] = green;
                pixel_array[y][x][2] = blue;
                //---------------------------------------- Changing the Pixels with Target Blue Value Part ----------------------------------------
                if (blue == this.getTarget_blue_value()) {
                    blue += 1;
                    Color newColor = new Color(red, green, blue);
                    int newPixel = newColor.getRGB();
                    this.getImage().setRGB(x, y, newPixel);
                }
            }
        }
    }

    public void HidingTheMessage() {
        int[] randomArray = createRandomArray(this.getMessage().length());
        HashSet<String> usedPixels = new HashSet<>();

        //  ---------------------------------------- Hiding the Message Length and Target blue Value in Pixel[0,0] ----------------------------------------
        BufferedImage new_image = this.getImage();
        Color color = new Color(new_image.getRGB(0, 0), true);
        int red = this.getMessage().length();
        int green = this.getTarget_blue_value();
        int blue = color.getBlue();

        Color newColor = new Color(red, green, blue);
        int newPixel = newColor.getRGB();
        new_image.setRGB(0, 0, newPixel);

        // ---------------------------------------- Choosing Random Pixels and Hiding Information on them ----------------------------------------
        for (int i = 0; i < this.getMessage().length(); i++) {
            int random_pixel1, random_pixel2;
            String pixelKey;

            // Find an unused random pixel
            do {
                random_pixel1 = (int) (Math.random() * this.getImage().getWidth());
                random_pixel2 = (int) (Math.random() * this.getImage().getHeight());
                pixelKey = random_pixel1 + "," + random_pixel2;
            } while ((random_pixel1 == 0 && random_pixel2 == 0) || usedPixels.contains(pixelKey));

            // Mark this pixel as used
            usedPixels.add(pixelKey);

            // Set the RGB values
            red = randomArray[i];
            green = ((int) this.getMessage().charAt(i));
            blue = this.getTarget_blue_value();
            newColor = new Color(red, green, blue);
            newPixel = newColor.getRGB();
            this.getImage().setRGB(random_pixel1, random_pixel2, newPixel);
        }

    }

    private int[] createRandomArray(int length) {
        ArrayList<Integer> randomArrayList = new ArrayList<>();
        int[] randomArray = new int[length];
        int random_value;

        while (true) {
            random_value = (int) (Math.random() * 256);
            if (!randomArrayList.contains(random_value)) {
                randomArrayList.add(random_value);
                if (randomArrayList.size() == length) {
                    break;
                }
            }
        }
        Collections.sort(randomArrayList);
        for (int i = 0; i < length; i++) {
            randomArray[i] = randomArrayList.get(i);
        }

        return randomArray;
    }

    public String getHidden_message() {
        return hidden_message;
    }

    public void setHidden_message(String hidden_message1) {
        this.hidden_message = hidden_message1;
    }

    public BufferedImage getImage() {
        return this.user_image;
    }

    public boolean is_RecievedMessage() {
        return is_recieved_message;
    }

    public void setRecieved(boolean is_recieved_message) {
        this.is_recieved_message = is_recieved_message;
    }

    public void setImage(String image_path) {
        try {
            // Reading Image from file path
            File file = new File(image_path);
            BufferedImage our_image = ImageIO.read(file);
            this.user_image = our_image;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    //Overloading
    public void setImage(File file) {
        try {

            BufferedImage our_image = ImageIO.read(file);
            this.user_image = our_image;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    @Override
    public void setAge(int age) {
        super.setAge(age); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public int getAge() {
        return super.getAge(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void setJob_description(String job_description) {
        super.setJob_description(job_description); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String getJob_description() {
        return super.getJob_description(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void setSurname(String surname) {
        super.setSurname(surname); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String getSurname() {
        return super.getSurname(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void setName(String name) {
        super.setName(name); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String getName() {
        return super.getName(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
