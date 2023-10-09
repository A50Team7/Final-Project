package api;

import com.testframework.models.User;

import java.util.ArrayList;
import java.util.List;

public class ApiUser {

    public ApiUser(String authority, User user) {
        authorities = new ArrayList<String>();
        addAuthority(authority);
        setCategory(new Category(user.getCategory()));
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setUsername(user.getUsername());
    }

    private List<String> authorities;
    private Category category;
    private String email;
    private String password;
    private String username;

    public List<String> getAuthorities() {
        return authorities;
    }

    public Category getCategory() {
        return category;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    private void addAuthority(String string) {
        authorities.add(string);
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
