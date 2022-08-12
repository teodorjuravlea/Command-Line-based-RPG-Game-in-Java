public class Credentials {
    private String username, password;

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean checkCredentials(String username, String password){
        return this.username.equals(username) && this.password.equals(password);
    }
}
