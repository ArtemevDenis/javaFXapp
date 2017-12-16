package EnterFormOtherClass;

public class User {
    private String name;
    private String surname;
    private String mobPhone;
    private String workPhone;
    private String email;
    private String password;
    private String tolLevel;
    private String bio;
    private String dateOfBirth;
    private String dateOfReg;
    private String ID;

    public User(String name, String surname,
                String email, String mobPhone,
                String workPhone, String password,
                String tolLevel, String bio,
                String dateOfBirth, String dateOfReg, String ID)
    {
        this.name = name;
        this.surname = surname;
        this.bio = bio;
        this.email = email;
        this.ID = ID;
        this.mobPhone = mobPhone;
        this.workPhone = workPhone;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.dateOfReg = dateOfReg;
        this.tolLevel = tolLevel;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getBio() {
        return bio;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getMobPhone() {
        return mobPhone;
    }

    public String getDateOfReg() {
        return dateOfReg;
    }

    public String getSurname() {
        return surname;
    }

    public String getTolLevel() {
        return tolLevel;
    }

    public String getID() {
        return ID;
    }

    public String getWorkPhone() {
        return workPhone;
    }

}

