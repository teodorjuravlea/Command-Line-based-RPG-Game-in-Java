import org.json.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    ArrayList<Account> accountList;
    HashMap<Cell.cellType, ArrayList<String>> storyList;
    private static Game myGame = null;

    public static Game getInstance() {
        if(myGame != null) {
            return myGame;
        }
        myGame = new Game();
        return myGame;
    }

    public void run(String jsonString) throws FileNotFoundException {
        JSONObject myJsonObj = new JSONObject(jsonString);

        accountList = new ArrayList<>();
        JSONArray accountsJsonArray = myJsonObj.getJSONArray("accounts");
        int i, j;
        for(i = 0; i < accountsJsonArray.length(); ++i) {
            String name = accountsJsonArray.getJSONObject(i).getString("name");
            String country = accountsJsonArray.getJSONObject(i).getString("country");
            int gameCount = accountsJsonArray.getJSONObject(i).getInt("maps_completed");

            JSONObject credentials = accountsJsonArray.getJSONObject(i).getJSONObject("credentials");
            String username = credentials.getString("email");
            String password = credentials.getString("password");
            Credentials userCred = new Credentials(username, password);

            JSONArray favoriteGamesArray = accountsJsonArray.getJSONObject(i).getJSONArray("favorite_games");
            ArrayList<String> favoriteGames = new ArrayList<>();
            for(j = 0; j < favoriteGamesArray.length(); ++j) {
                String favoriteGame = favoriteGamesArray.getString(j);
                favoriteGames.add(favoriteGame);
            }

            JSONArray characters = accountsJsonArray.getJSONObject(i).getJSONArray("characters");
            ArrayList<Character> characterList = new ArrayList<>();
            for(j = 0; j < characters.length(); ++j) {
                String characterName = characters.getJSONObject(j).getString("name");
                String characterClass = characters.getJSONObject(j).getString("profession");
                int characterLevel = characters.getJSONObject(j).getInt("level");
                int characterExperience = characters.getJSONObject(j).getInt("experience");
                characterList.add(CharacterFactory.factory(characterClass, characterName, characterExperience, characterLevel));
            }

            Account newAccount = new Account(userCred, favoriteGames, name, country, characterList, gameCount);
            accountList.add(newAccount);
        }

        File input = new File("input.txt");
        Scanner myScanner = new Scanner(input);

        Account currentAccount = null;
        boolean loggedIn = false;
        System.out.println("Type the email and the password on separate lines:");
        String username = myScanner.nextLine();
        String password = myScanner.nextLine();
        for(i = 0; i < accountList.size(); ++i) {
            if(accountList.get(i).playerInfo.userCred.checkCredentials(username, password)) {
                currentAccount = accountList.get(i);
                loggedIn = true;
            }
        }
        if(!loggedIn) {
            System.out.println("Credentials not found!");
            return;
        }

        System.out.println();
        System.out.println("Welcome, " + currentAccount.playerInfo.name + "!");
        System.out.println("Account info:");
        System.out.println("    Country: " + currentAccount.playerInfo.country);
        System.out.println("    Favorite Games: " + currentAccount.playerInfo.favoriteGames);
        System.out.println("    Completed maps: " + currentAccount.gameCount);

        System.out.println("Character List:");
        for(i = 0; i < currentAccount.characterList.size(); ++i) {
            System.out.println("    " + (i + 1) + ") " + currentAccount.characterList.get(i).name
                    + " - " + currentAccount.characterList.get(i).charClass
                    + " - " + "Level " + currentAccount.characterList.get(i).currLevel
                    + " - " + currentAccount.characterList.get(i).currXP + " XP");
        }

        System.out.println("Select character number:");
        i = myScanner.nextInt();
        System.out.println();
        if(i > currentAccount.characterList.size()) {
            System.out.println("Invalid character number!");
            return;
        }
        Character currentCharacter = currentAccount.characterList.get(i - 1);
        System.out.println("Character selected: " + currentCharacter.name);
        System.out.println("Stats:");
        System.out.println("    Strength: " + currentCharacter.strength);
        System.out.println("    Charisma: " + currentCharacter.charisma);
        System.out.println("    Dexterity: " + currentCharacter.dexterity);
        System.out.println();

        Grid myGrid = Grid.getHardcodedMap(currentCharacter);
        while(myGrid.get(myGrid.currentX).get(myGrid.currentY).element.getElementType() != 'F') {
            String move = myScanner.nextLine();
            switch (move) {
                case "N" -> myGrid.goNorth();
                case "S" -> myGrid.goSouth();
                case "E" -> myGrid.goEast();
                case "W" -> myGrid.goWest();
            }

            if(myGrid.get(myGrid.currentX).get(myGrid.currentY).element.getElementType() == 'S') {
                Shop currentShop = (Shop) myGrid.get(myGrid.currentX).get(myGrid.currentY).element;
                while(currentShop.getPotionNumber() > 0) {
                    System.out.println();
                    System.out.println("This shop sells:");
                    currentShop.listPotions();
                    System.out.println();
                    System.out.println("You have: " + currentCharacter.charInventory.coins + " coins");
                    System.out.println("Options: ");
                    System.out.println("    Type the number of the potion you want to buy.");
                    System.out.println("    Type 0 to leave the shop");
                    int option = myScanner.nextInt();
                    myScanner.nextLine();
                    if(option == 0){
                        break;
                    }
                    if(option > currentShop.getPotionNumber()) {
                        System.out.println("Potion number invalid!");
                    }
                    else {
                        currentCharacter.buyPotion(option - 1, currentShop);
                    }
                }
            }
            System.out.println();
            myGrid.displayMap(false);
        }
        System.out.println();
        System.out.println("Game finished!");
    }
}
