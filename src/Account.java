import java.util.ArrayList;
import java.util.Collections;

public class Account {
    public static class Information {
        Credentials userCred;
        ArrayList<String> favoriteGames;
        String name, country;

        private Information(InformationBuilder builder) {
            this.userCred = builder.userCred;
            this.favoriteGames = builder.favoriteGames;
            this.name = builder.name;
            this.country = builder.country;
        }

        public static class InformationBuilder {
            private final Credentials userCred;
            private ArrayList<String> favoriteGames;
            private String name, country;

            public InformationBuilder(Credentials userCred) {
                this.userCred = userCred;
            }

            public InformationBuilder favoriteGames(ArrayList<String> favoriteGames) {
                Collections.sort(favoriteGames);
                this.favoriteGames = favoriteGames;
                return this;
            }

            public InformationBuilder name(String name) {
                this.name = name;
                return this;
            }

            public InformationBuilder country(String country) {
                this.country = country;
                return this;
            }

            public Information build() {
                return new Information(this);
            }
        }
    }

    Information playerInfo;
    ArrayList<Character> characterList;
    int gameCount;

    public Account(Credentials userCred, ArrayList<String> favoriteGames, String name, String country, ArrayList<Character> characterList, int gameCount) {
        playerInfo = new Information.InformationBuilder(userCred)
                .favoriteGames(favoriteGames)
                .name(name)
                .country(country)
                .build();

        this.characterList = characterList;
        this.gameCount = gameCount;
    }
}