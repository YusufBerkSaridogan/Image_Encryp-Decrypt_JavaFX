package proje_02;

import java.awt.image.BufferedImage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;

public class Screen extends Application {

    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Administrator> admins = new ArrayList<>();

    private TextField messageField;
    private TextField blueValueField;
    private Button encryptBtn;
    private Button decryptBtn;
    private TextArea cryptedTextArea;
    ImageView imageView;
    private boolean is_image_chosen = false;

    User user;
    Administrator admin;

    @Override
    public void start(Stage primaryStage) {
        mainStage();

    }

    void mainStage() {

        Text maintxt = new Text("Welcome to the Encryption Program. Please log in.");
        maintxt.setFont(Font.font("Lucida Calligraphy", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Text ptext = new Text("For User Log In.");
        ptext.setFont(Font.font("Time New Roman", FontWeight.BOLD, FontPosture.REGULAR, 13));

        Button pbuton = new Button("User");
        pbuton.setMaxWidth(200);

        VBox pgiris = new VBox();
        pgiris.setSpacing(10);
        pgiris.getChildren().addAll(ptext, pbuton);
        pgiris.setAlignment(Pos.CENTER_RIGHT);

        HBox py = new HBox();
        py.setSpacing(80);
        py.setAlignment(Pos.CENTER);
        py.getChildren().addAll(pgiris);

        Image image = new Image("file:C:\\Users\\Berk\\Pictures\\marmara.jpg");
        ImageView marmara = new ImageView(image);
        marmara.setFitHeight(275);
        marmara.setFitWidth(400);
        StackPane sp = new StackPane();
        sp.getChildren().add(marmara);

        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setVgap(100);
        gp.add(maintxt, 0, 0);
        gp.add(sp, 0, 1);
        gp.add(py, 0, 2);

        // Actions
        pbuton.setOnAction((event) -> {

            GridPane g = new GridPane();
            VBox uyari = new VBox();
            Text uyaritxt = new Text("You Selected User Operations. Do you want to continue?");
            uyaritxt.setFont(Font.font("Time New Roman", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 13));
            Button be = new Button("Yes");
            Button bh = new Button("No");
            be.setStyle("-fx-border-color: black; -fx-font-weight: bold;");

            bh.setStyle("-fx-border-color: black; -fx-font-weight: bold;");

            uyari.setAlignment(Pos.CENTER);

            uyari.setSpacing(10);

            uyari.getChildren().addAll(uyaritxt, be, bh);

            g.add(uyari, 0, 0);
            g.add(be, 0, 1);
            g.add(bh, 1, 1);

            Stage s = new Stage();
            Scene suyari = new Scene(g, 400, 60);
            s.setScene(suyari);
            uyaritxt.setStyle("-fx-font-weight: bold;");

            s.show();
            be.setOnAction((event2) -> {
                userLoginStage();
                s.close();
            });
            bh.setOnAction((event2) -> {
                System.out.println("Please choose appropriate option!");
                s.close();
            });
        });

        Stage openstage = new Stage();
        Scene s = new Scene(gp, 1000, 600);
        openstage.setScene(s);
        pbuton.setStyle("-fx-font-size: 16px; -fx-border-color: navy; -fx-border-width: 2px;");
        maintxt.setStyle("-fx-font-weight: bold;");
        ptext.setStyle("-fx-font-weight: bold;");
        openstage.setTitle("Main Stage");

        openstage.show();
    }

    void userLoginStage() {
        Stage pgirisStage = new Stage();

        Label label = new Label("Please Log In");
        label.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
        GridPane gps = new GridPane();
        gps.setAlignment(Pos.CENTER);
        gps.setHgap(10);
        gps.setVgap(10);
        gps.setPadding(new Insets(20));

        Label adsoyadlabel = new Label("Name Surname: ");
        TextField tf = new TextField("");
        Label mesajlabel = new Label("");
        VBox vb = new VBox();
        vb.setPadding(new Insets(10, 0, 0, 10));
        vb.setSpacing(10);
        HBox hb = new HBox();
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER_LEFT);
        Label sifrelabel = new Label("Password: ");
        PasswordField pf = new PasswordField();

        //Password Action
        pf.setOnAction((e) -> {
            boolean b = false;
            String fx = tf.getText();
            for (int i = 0; i < users.size(); i++) {
                String ana = (users.get(i).getName() + " " + users.get(i).getSurname());
                if (fx.equals(ana)) {
                    user = users.get(i);
                    b = true;
                    break;
                }
            }
            if (!pf.getText().equals("1234") && b == true) {
                mesajlabel.setText("Password or username entered incorrectly.");
                mesajlabel.setTextFill(Color.rgb(210, 39, 30));
            } else if (!pf.getText().equals("1234") && b == false) {
                mesajlabel.setText("Password or username entered incorrectly.");
                mesajlabel.setTextFill(Color.rgb(210, 39, 30));
            } else if (pf.getText().equals("1234") && b == false) {
                mesajlabel.setText("Password or username entered incorrectly.");
                mesajlabel.setTextFill(Color.rgb(210, 39, 30));
            } else {
                mesajlabel.setText("Login successful.");
                mesajlabel.setTextFill(Color.rgb(21, 117, 84));
                userMainStage();
                pgirisStage.close();

            }
            pf.clear();
        });

        gps.add(label, 0, 0);
        gps.add(adsoyadlabel, 0, 1);
        gps.add(tf, 1, 1);
        gps.add(sifrelabel, 0, 2);
        gps.add(pf, 1, 2);
        gps.add(mesajlabel, 1, 3);

        // Set styles
        gps.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        label.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16; -fx-font-weight: bold;");
        gps.setStyle("-fx-background-color: lightgrey;");

        Scene pgirisScene = new Scene(gps, 800, 500);
        pgirisStage.setTitle("User");
        pgirisStage.setScene(pgirisScene);
        pgirisStage.show();

    }

    void userMainStage() {
        // Personal Informations
        Label tlabel = new Label("Personal Informations");
        tlabel.setFont(Font.font("Hamburg", FontWeight.BOLD, FontPosture.REGULAR, 16));
        tlabel.setTextFill(Color.RED);

        Text txtfield1 = new Text((user.getName() + " " + user.getSurname()));
        Text txtfield2 = new Text(Integer.toString(user.getAge()));
        Text txtfield3 = new Text(user.getJob_description());

        Label adsoyad = new Label("Name Surname:");
        Label yas = new Label("Age:");
        Label alan = new Label("User Type:");

        GridPane gp = new GridPane();
        gp.setHgap(20);
        gp.setAlignment(Pos.CENTER);
        gp.add(adsoyad, 0, 0);
        gp.add(txtfield1, 1, 0);
        gp.add(yas, 0, 1);
        gp.add(txtfield2, 1, 1);
        gp.add(alan, 0, 2);
        gp.add(txtfield3, 1, 2);

        gp.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        // User Operations
        Label label = new Label("User Operations");
        label.setFont(Font.font("Hamburg", FontWeight.BOLD, FontPosture.REGULAR, 16));
        label.setTextFill(Color.RED);

        Button bt1 = new Button("Encryption");
        Button bt2 = new Button("Decryption");

        bt1.setStyle("-fx-font-size: 14px; -fx-border-color: navy; -fx-border-width: 2px; -fx-text-fill: navy; -fx-font-weight: bold;");
        bt2.setStyle("-fx-font-size: 14px; -fx-border-color: navy; -fx-border-width: 2px; -fx-text-fill: navy; -fx-font-weight: bold;");

        HBox hbox = new HBox();
        hbox.setSpacing(25);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(bt1, bt2);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(tlabel, gp, label, hbox);
        vbox.setSpacing(30);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        BackgroundImage b = new BackgroundImage(new Image("https://img.freepik.com/free-vector/white-gray-background-with-diagonal-lines-pattern_1017-25100.jpg?w=2000"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background bg = new Background(b);
        vbox.setBackground(bg);

        Stage stage = new Stage();
        Scene s = new Scene(vbox, 400, 400);
        stage.setTitle("User");
        stage.setScene(s);
        stage.show();

        //Actions
        bt1.setOnAction((event) -> {
            encryptionStage();
        });
        bt2.setOnAction((event) -> {
            decryptionStage();
        });
    }

    void encryptionStage() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Encryption Screen");

        // Message Input
        Label messageLabel = new Label("Enter the message you want to hide (Must be 1-255 characters long):");
        messageLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        messageLabel.setTextFill(Color.BLACK);
        messageField = new TextField(); // Burada messageField'ı yeniden tanımlıyorum
        messageField.setPromptText("Message");
        messageField.setMaxWidth(300);
        messageField.setStyle("-fx-border-color: navy; -fx-border-width: 2px;");

        // Blue Value Input
        Label blueValueLabel = new Label("Enter the target blue value you want to hide:");
        blueValueLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        blueValueLabel.setTextFill(Color.BLACK);
        blueValueField = new TextField();
        blueValueField.setPromptText("Blue value");
        blueValueField.setMaxWidth(100);
        blueValueField.setStyle("-fx-border-color: navy; -fx-border-width: 2px;");

        // Image Selection
        Label imageLabel = new Label("Select an image:");
        imageLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        imageLabel.setTextFill(Color.BLACK);
        Button selectImageButton = new Button("Select Image");
        selectImageButton.setStyle("-fx-font-weight: bold; -fx-border-color: navy; -fx-border-width: 2px;");
        selectImageButton.setOnAction(e -> selectImage(primaryStage));

        // Encrypt Button
        encryptBtn = new Button("Encrypt");
        encryptBtn.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 14px; -fx-border-color: navy; -fx-border-width: 2px;");

        // Encrypted Text Display
        cryptedTextArea = new TextArea();
        cryptedTextArea.setWrapText(true);
        cryptedTextArea.setEditable(true);
        cryptedTextArea.setStyle("-fx-font-weight: bold; -fx-border-color: navy; -fx-border-width: 2px;");
        cryptedTextArea.prefHeight(300);

        // Image View
        imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setStyle("-fx-border-color: black; -fx-border-width: 4px;");

        // Layout Setup
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        // Add components to layout
        gridPane.add(messageLabel, 0, 2);
        gridPane.add(blueValueLabel, 0, 1);
        gridPane.add(blueValueField, 1, 1);
        gridPane.add(imageLabel, 0, 0);
        gridPane.add(selectImageButton, 1, 0, 2, 1);
        gridPane.add(encryptBtn, 0, 4, 2, 1);
        gridPane.add(cryptedTextArea, 0, 3, 2, 1);
        gridPane.add(imageView, 3, 1, 2, 5);

        // Encrypt Button Handler
        encryptBtn.setOnAction(e -> encryptMessage(primaryStage));

        Scene scene = new Scene(gridPane, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    void decryptionStage() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Decryption Screen");

        // Message Input
        Label messageLabel = new Label("Please Choose The Image You Wanted to Decrypt:");
        messageLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        messageLabel.setTextFill(Color.BLACK);

        // Image Selection
        Button selectImageButton = new Button("Select Image");
        selectImageButton.setStyle("-fx-font-weight: bold; -fx-border-color: navy; -fx-border-width: 2px;");
        selectImageButton.setOnAction(e -> selectImage(primaryStage));

        // Decrypt Button
        decryptBtn = new Button("Decrypt");
        decryptBtn.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-border-color: navy; -fx-border-width: 2px;");

        // Encrypted Text Display
        cryptedTextArea = new TextArea();
        cryptedTextArea.setWrapText(true);
        cryptedTextArea.setEditable(false);
        cryptedTextArea.prefHeight(300);
        cryptedTextArea.setStyle("-fx-border-color: navy; -fx-border-width: 2px;");

        // Image View
        imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setStyle("-fx-border-color: black; -fx-border-width: 4px;");

        // Layout Setup
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        // Add components to layout
        gridPane.add(messageLabel, 0, 0);
        gridPane.add(selectImageButton, 1, 0, 2, 1);
        gridPane.add(decryptBtn, 0, 3, 2, 1);
        gridPane.add(cryptedTextArea, 0, 4, 2, 1);

        // Decrypt Button Handler
        decryptBtn.setOnAction(e -> decryptMessage());

        Scene scene = new Scene(gridPane, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void selectImage(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
            user.setImage(file);
            is_image_chosen = true;
        }
    }

    private void saveImage(Stage primaryStage) {

        if (!is_image_chosen) {
            displayError("Please choose an image first.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"));
        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            try {
                // Save the image to the selected file
                BufferedImage bufferedImage = user.getImage();
                ImageIO.write(bufferedImage, "png", file);

            } catch (IOException e) {
                displayError("Failed to save the image.");
            }
        }

    }

    private void encryptMessage(Stage primaryStage) {
        // Message validation
        try {
            String message = cryptedTextArea.getText();
            if (message.isEmpty() || message.length() > 255) {
                displayError("Message must be 1-255 characters long.");
                return;
            } else if (message.contains("Ç") || message.contains("Ğ") || message.contains("İ") || message.contains("Ö") || message.contains("Ş") || message.contains("Ü") || message.contains("ç") || message.contains("ğ") || message.contains("ı") || message.contains("ö") || message.contains("ş") || message.contains("ü")) {
                displayError("Message must not contain any Turkish letter");
                return;
            }

            // Blue value validation
            String blueValue = blueValueField.getText();
            try {
                int blue = Integer.parseInt(blueValue);
                if (blue < 0 || blue > 255) {
                    displayError("Blue value must be between 0 and 255.");
                    return;
                } else if (message.length() == blue) {
                    displayError("Blue value must be different than message length.");
                    return;
                }
            } catch (NumberFormatException e) {
                displayError("Blue value must be an integer.");
                return;
            }
            if (is_image_chosen == false) {
                displayError("Please Choose An Image");

            } else {
                if (user.getImage().getWidth() > 50 || user.getImage().getHeight() > 50) {
                    displayError("Image size must be 50x50!");
                } else {
                    user.message = message;
                    user.sendHiddenMessage();

                    cryptedTextArea.setText(user.getMessage());
                    saveImage(primaryStage);
                }

            }
        } catch (Exception e) {
            displayError("An Error Occurred!");
        }
    }

    private void decryptMessage() {
        try {
            if (is_image_chosen == false) {
                displayError("There is no image chosen for decryption!");
            } else {
                if (user.getImage().getWidth() > 50 || user.getImage().getHeight() > 50) {
                    displayError("Image size must be 50x50!");
                } else {
                    user.doDecryptionFX();
                    if ((user.getHidden_message()).isEmpty()) {
                        displayError("There is no hidden message!");
                    } else {
                        cryptedTextArea.setText(user.getHidden_message());
                    }
                }

            }
        } catch (Exception e) {
            displayError("There is no hidden message!");
        }

    }

    private void displayError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    static void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
        }
    }

    static void addAdmin(Administrator admin) {
        if (!admins.contains(admin)) {
            admins.add(admin);
        }
    }

    public static void main(String[] args) {
       // You can add user and administrator down below
        User user1 = new User("Berk", "Sarıdoğan", 24);
        User user2 = new User("Livanur", "Erdem", 62);
        User user3 = new User("Halil", "Karaoğlu", 26);
        User user4 = new User("Nilsu", "Üçkan", 21);
        User user5 = new User("Deneme", "Deneme", 81);

        Administrator admin = new Administrator("Oguzhan", "Ciftci", 31);
        Administrator admin2 = new Administrator("Liva", "Liv", 2);

        System.out.println("************************");
        addUser(user1);
        addUser(user2);
        addUser(user3);
        addUser(user4);
        addUser(user5);
        addAdmin(admin);
        addAdmin(admin2);

   
        System.out.println("************************");
        admin.seeLogHistory();

        launch(args);
    }
}
