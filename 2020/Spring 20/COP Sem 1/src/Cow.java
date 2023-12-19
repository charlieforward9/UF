public class Cow {

    private String name;
    private String image;

    public Cow(String name) {           //Default constructor
        this.name = name;
    }
    public String getName() {           //Returns name of cow
        return name;

    }
    public String getImage()   {
        //Returns image used to display the cow (after the message)
        return image;

    }
    public void setImage(String _image) {
        //Sets the image used to display the cow (after the message)
        this.image = _image;
    }
}